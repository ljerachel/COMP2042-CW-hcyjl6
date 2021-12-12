/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package test;

import test.Controller.BrickController;
import test.Controller.PlayerController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;



public class GameBoard extends JComponent implements KeyListener,MouseListener,MouseMotionListener {

    private static final String CONTINUE = "Continue";
    private static final String RESTART = "Restart";
    private static final String EXIT = "Exit";
    private static final String PAUSE = "Pause Menu";
    private static final int TEXT_SIZE = 30;
    private static final Color MENU_COLOR = new Color(0, 255, 0);


    private Point2D p;
    private boolean showScoreboard;

    private final boolean LifeIconDrawn = false ;
    private BufferedImage bi ;
    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    private static final Color BG_COLOR = Color.WHITE;


    private Timer gameTimer;

    private final Wall wall;
    public int t;


    private final Image LifeIcon;
    private String message;


    private GameFrame owner;

    private boolean showPauseMenu;

    private final Font menuFont;
    private Font ScoreboardFont;
    private static String name;

    private Rectangle continueButtonRect;
    private Rectangle exitButtonRect;
    private Rectangle restartButtonRect;
    private int strLen;

    private final DebugConsole debugConsole;


    /**
     * instantiante debug console and wall object
     * call the first level to be built
     * gametimer that starts when the game starts and refreshes every 10 miliseconds
     * if ball is lost, user will be prompted to enter their name and to choose if they want to see the leaderboard
     * name will be written to the file along with the high score
     * then the readFile method is called to read data from the csv file into an arraylist to be sorted
     *
     */
    public GameBoard(JFrame owner) {
        super();

        strLen = 0;
        showPauseMenu = false;


        menuFont = new Font("Monospaced", Font.PLAIN, TEXT_SIZE);


        this.initialize();
        message = "";
        wall = new Wall(new Rectangle(0, 0, DEF_WIDTH, DEF_HEIGHT), 30, 3, 6 / 2, new Point(300, 430));

        debugConsole = new DebugConsole(owner, wall, this);
        //initialize the first level
        wall.nextLevel();

        gameTimer = new Timer(10, e -> {  // for every 10 milliseconds , check for updates in the game
            wall.move();
            wall.findImpacts();


            if (!wall.isLifeCollected()) {
                wall.touchIcon(p);
            }
            if (wall.isShowWinningMsg()){
                t++;
                if (t==200)
                {
                    wall.setShowWinningMsg(false);
                }
            }

            message = String.format("Bricks: %d Balls %d \n Current Score : %d", wall.getBrickCount(), wall.getBallCount(), wall.getCurrenthighscore());


            if (wall.isBallLost() ) {
                if (wall.ballEnd()) {
                    wall.wallReset();
                    input(); // the input name function
                    viewLeaderboard();


                    try {
                        WriteIntoFile modfile = new WriteIntoFile();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }


                    try {
                        ReadFile read = new ReadFile();
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }


                    message = String.format("Game over\n Score: %d ", Wall.getFinalhighscore());


                }
                wall.ballReset();
                gameTimer.stop();


            } else if (wall.isDone()) {
                if (wall.hasLevel()) {
                    message = "Go to Next Level";

                    gameTimer.stop();
                    wall.ballReset();
                    wall.wallReset();
                    wall.nextLevel();
                } else {
                    message = String.format("ALL WALLS DESTROYED\n Score : %d is recorded in the system", wall.getCurrenthighscore()); // total score
                    gameTimer.stop();

                }
            }

            repaint();
        });
        LifeIcon = new ImageLoader().getImage() ;

    }

    public static String getname() {
        return name;
    }


    private void initialize() {
        this.setPreferredSize(new Dimension(DEF_WIDTH, DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }


    /**
     * paint all the graphics in the game
     * if the ball does not collect the extra life , the life icon will remain in the position
     * if the ball collects the extra life, an image will pop up at the particular location
     * bricks are drawn if they are not broken by impact
     *
     */
    public void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        clear(g2d);

        g2d.setColor(Color.BLUE);
        g2d.drawString(message, 210, 225);

        wall.ball.ballView.drawBall(wall.ball, g2d);

       if (!wall.isLifeCollected() ) {
           drawLifeIcon(g2d, wall);

       }


        if (wall.isShowWinningMsg())
       {
           g2d.drawImage(LifeIcon, 100,50 , 80   , 80  , null) ;
       }



        for (BrickController b : wall.getBricks())
            if (!b.isBroken())   // false (not broken draw the brick)

            {
                b.brickView.drawBrick(b, g2d);
            }


        PlayerController.getInstance().playerView.drawPlayer(PlayerController.getInstance(),g2d);

        if (showPauseMenu)
            drawMenu(g2d);

        if (showScoreboard) {

            try {
                drawScoreboard(g2d);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }




        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * @param g2d change bg colour
     */
    private void clear(Graphics2D g2d) {
        //Color tmp = g2d.getColor();
        Color tmp = new Color(0, 0, 0);
        g2d.setColor(tmp);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(tmp);
    }


    /**
     * draw the extra life icon behind brick
     *
     */
    private void drawLifeIcon( Graphics2D g2d ,Wall wall ) // add this parameter
    {

        int x = 100 ;
        int y = 50 ;
        p = new Point2D.Double(x, y);
        Ellipse2D.Double circle = new Ellipse2D.Double(x, y, 10, 10);
        p.setLocation(x,y);

        g2d.setColor(Color.red);
        g2d.fill(circle);



    }

    private void drawMenu(Graphics2D g2d) {
        obscureGameBoard(g2d);
        drawPauseMenu(g2d);
    }

    private void obscureGameBoard(Graphics2D g2d) {

        Composite tmp = g2d.getComposite();
        Color tmpColor = g2d.getColor();

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.55f);
        g2d.setComposite(ac);
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, DEF_WIDTH, DEF_HEIGHT);

        g2d.setComposite(tmp);
        g2d.setColor(tmpColor);
    }


    /**
     * reads the highscore.csv file into the GUI to be displayed
     */
    public void drawScoreboard(Graphics2D g2d) throws IOException {


        String row = " ";
        String name = " ";
        int ranking = 0 ;
        int highscore ;
        GameBoard.name = name;



        ScoreboardFont = new Font("Monospaced", Font.PLAIN, TEXT_SIZE);


        obscureGameBoard(g2d);
        FontRenderContext frc = g2d.getFontRenderContext();
        g2d.setFont(ScoreboardFont);

        g2d.setColor(new Color(198, 109, 243)); // background color

        Rectangle2D ScoreRect= ScoreboardFont.getStringBounds("hi",frc);
        Rectangle ScoreboardFace = new Rectangle(new Point(0, 0), new Dimension(600, 450));
        g2d.fill(ScoreboardFace);
        int y;
        g2d.setColor(Color.black);





        y=  70;

        g2d.drawString("rank",100,y);

        g2d.drawString("name",200,y);

        g2d.drawString("highscore",350,y);


        File a = new File("src/main/resources/Misc/Highscore.csv");

        BufferedReader csvread ;

        csvread = new BufferedReader(new FileReader(a));


        for(int i = 0; i < 10  ; i ++) {

                y +=50 ;
                row = csvread.readLine();
            if (row != null) {
                String[] data = row.split(",");
                ranking = Integer.parseInt(data[2]);
                name = data[0];
                highscore = Integer.parseInt(data[1]);


                g2d.drawString(String.valueOf(ranking), 100, y);
                g2d.drawString(name, 200 , y );
                g2d.drawString(String.valueOf(highscore),350 , y );



    }
}

    }

    private void drawPauseMenu(Graphics2D g2d){
        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();


        g2d.setFont(menuFont);
        g2d.setColor(MENU_COLOR);

        if(strLen == 0){
            FontRenderContext frc = g2d.getFontRenderContext();
            strLen = menuFont.getStringBounds(PAUSE,frc).getBounds().width;
        }

        int x = (this.getWidth() - strLen) / 2;
        int y = this.getHeight() / 10;

        g2d.drawString(PAUSE,x,y);

        x = this.getWidth() / 8;
        y = this.getHeight() / 4;


        if(continueButtonRect == null){
            FontRenderContext frc = g2d.getFontRenderContext();
            continueButtonRect = menuFont.getStringBounds(CONTINUE,frc).getBounds();
            continueButtonRect.setLocation(x,y-continueButtonRect.height);
        }

        g2d.drawString(CONTINUE,x,y);

        y *= 2;

        if(restartButtonRect == null){
            restartButtonRect = (Rectangle) continueButtonRect.clone();
            restartButtonRect.setLocation(x,y-restartButtonRect.height);
        }

        g2d.drawString(RESTART,x,y);

        y *= 3.0/2;

        if(exitButtonRect == null){
            exitButtonRect = (Rectangle) continueButtonRect.clone();
            exitButtonRect.setLocation(x,y-exitButtonRect.height);
        }

        g2d.drawString(EXIT,x,y);



        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    /**
     * @param keyEvent A and D is to move the player left and right, Esc is to show pause menu, SPACE is to pause game, f1 is to show debug console
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch(keyEvent.getKeyCode()){
            case KeyEvent.VK_A:
                PlayerController.getInstance().moveLeft();
                break;
            case KeyEvent.VK_D:
                PlayerController.getInstance().movRight();
                break;
            case KeyEvent.VK_ESCAPE:
                showPauseMenu = !showPauseMenu;
                repaint();
                gameTimer.stop();
                break;
            case KeyEvent.VK_SPACE:
                if(!showPauseMenu)
                    if(gameTimer.isRunning())
                        gameTimer.stop();
                    else
                        gameTimer.start();
                break;
            case KeyEvent.VK_F1:
                if(keyEvent.isAltDown() && keyEvent.isShiftDown())
                    debugConsole.setVisible(true);
            default:
                PlayerController.getInstance().stop();
        }
    }

    /**
     * @param keyEvent when no key is pressed, player does not move
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {
        PlayerController.getInstance().stop();
    }

    /**
     * @param mouseEvent if mouse clicks on continue, menu is not repainted
     *                   if restart is clicked on, everything is reset
     *                   if exit is clicked on, game closes
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(!showPauseMenu)
            return;
        if(continueButtonRect.contains(p)){
            showPauseMenu = false;
            repaint();
        }
        else if(restartButtonRect.contains(p)){
            message = "Restarting Game...";
            wall.ballReset();
            wall.wallReset();
            showPauseMenu = false;
            repaint();
        }
        else if(exitButtonRect.contains(p)){
            System.exit(0);
        }


    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(exitButtonRect != null && showPauseMenu) {
            if (exitButtonRect.contains(p) || continueButtonRect.contains(p) || restartButtonRect.contains(p))
                this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                this.setCursor(Cursor.getDefaultCursor());
        }
        else{
            this.setCursor(Cursor.getDefaultCursor());
        }
    }

    /**
     * when the application is minimized, a message Focul Lost would be displayed
     */
    public void onLostFocus(){
        gameTimer.stop();
        message = "Focus Lost";
        repaint();
    }


    /**
     * a Jdialog pops up to prompt for user name
     */
    private void input() {
        name = JOptionPane.showInputDialog(this, "Game over! please input your name");
        name = name;
    }


    /**
     * jdialog pops up to prompt user to choose if they want to view the leaderboard, if yes, scoreboard is displayed,
     */
    private void viewLeaderboard()
    {
        int view =JOptionPane.showConfirmDialog(null,"View leaderboard ? ");
        switch (view)
        {
            case JOptionPane.YES_OPTION:
                showScoreboard=true;
                repaint();
                break;
            case JOptionPane.NO_OPTION:
                showScoreboard = false ;
                repaint();
                break;
        }
    }

}
