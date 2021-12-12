package test.View;
import test.Controller.BrickController;
import test.Controller.GameBoardController;
import test.Controller.PlayerController;
import test.Model.ImageLoader;
import test.Model.Wall;
import test.Model.ReadFile;
import test.Model.WriteIntoFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;


public class GameBoardView extends JComponent{


    private static final String CONTINUE = "Continue";
    private static final String RESTART = "Restart";
    private static final String EXIT = "Exit";
    private static final String PAUSE = "Pause Menu";
    private static final int TEXT_SIZE = 30;
    private static final Color MENU_COLOR = new Color(0, 255, 0);


    private Point2D p;
    private Point2D q;
    private boolean showScoreboard;

    private final boolean LifeIconDrawn = false ;
    private BufferedImage bi ;
    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    private static final Color BG_COLOR = Color.WHITE;


    private Timer gameTimer;

    private Wall wall;
    public int t;


    private final Image LifeIcon;
    private String message;


    private boolean showPauseMenu;

    private final Font menuFont;
    private Font ScoreboardFont;
    private static String name;

    private Rectangle continueButtonRect;
    private Rectangle exitButtonRect;
    private Rectangle restartButtonRect;
    private int strLen;

    private final DebugConsole debugConsole;
    private GameBoardController gameBoardController;



    /**
     * instantiante debug console and wall object
     * call the first level to be built
     * gametimer that starts when the game starts and refreshes every 10 miliseconds
     * if ball is lost, user will be prompted to enter their name and to choose if they want to see the leaderboard
     * name will be written to the file along with the high score
     * then the readFile method is called to read data from the csv file into an arraylist to be sorted
     *
     */
    public GameBoardView(JFrame owner) {
        super();

        strLen = 0;
        showPauseMenu = false;
        menuFont = new Font("Monospaced", Font.PLAIN, TEXT_SIZE);

        this.initialize();

        message = "";
        wall = new Wall(new Rectangle(0, 0, DEF_WIDTH, DEF_HEIGHT), 30, 3, 6 / 2, new Point(300, 430));

        gameBoardController = new GameBoardController(wall , this);
        debugConsole = new DebugConsole(owner, wall, this);
        //initialize the first level
        wall.nextLevel();

        gameTimer = new Timer(10, e -> {  // for every 10 milliseconds , check for updates in the game

            wall.move();
            wall.findImpacts();



            if(!wall.isSpeedCollected()){

                wall.touchSpeedIcon(q);

            }


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


    /**
     * draws ball which is called from ballview
     * draws the power ups(life and speed) when they have not been collected
     * displays the power up image for life when life is collected
     * draw bricks if they have not been broken
     * draw player by calling the player instance from player controller
     * draws scoreboard when showscoreboard boolean is set to true
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

        if(!wall.isSpeedCollected()){
            drawIncreaseSpeedIcon(g2d, wall);
        }

        if (wall.isShowWinningMsg())
        {
            g2d.drawImage(LifeIcon, 100,50 , 80   , 80  , null) ;
        }

        for (BrickController b : wall.getBricks())
            if (!b.isBroken())

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
    public void clear(Graphics2D g2d) {
        Color tmp = new Color(0, 0, 0);
        g2d.setColor(tmp);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(tmp);
    }


    private void initialize() {
        this.setPreferredSize(new Dimension(DEF_WIDTH, DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();

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


    /**
     * @param wall passes wall to draw speed power up icon onto it behind brick at position q which has coordinates of (200,50)
     */
    private void drawIncreaseSpeedIcon( Graphics2D g2d ,Wall wall )
    {

        int x = 200 ;
        int y = 50 ;
        q = new Point2D.Double(x, y);
        Ellipse2D.Double circle1 = new Ellipse2D.Double(x, y, 10, 10);
        q.setLocation(x,y);

        g2d.setColor(Color.green);
        g2d.fill(circle1);

    }

    /**
     * @param g2d draw pause menu
     */
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
        GameBoardView.name = name;


        ScoreboardFont = new Font("TimesRoman", Font.PLAIN, TEXT_SIZE);


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


        for(int i = 0; i < 5  ; i ++) {

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

        g2d.drawString("press F key to restart game",60,400);
    }

    /**
     * draws pause menu with pause, continue, restart and exit
     */
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


    /**
     * when the application is minimized, a message Focus Lost message would be displayed
     */
    public void onLostFocus(){
        gameTimer.stop();
        message = "Focus Lost";
        repaint();
    }


    public Wall getWall() {
        return wall;
    }

    /**
     * a Jdialog pops up to prompt for user name
     */
    private void input() {
        name = JOptionPane.showInputDialog(this, "Game over! please input your name");
        name = name;
    }


    /**
     * jdialogPane pops up to prompt user to choose if they want to view the leaderboard, if yes, scoreboard is displayed,
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


    public boolean isShowPauseMenu() {
        return showPauseMenu;
    }

    public void setShowPauseMenu(boolean showPauseMenu) {
        this.showPauseMenu = showPauseMenu;
    }


    public Timer getGameTimer() {
        return gameTimer;
    }


    public DebugConsole getDebugConsole() {
        return debugConsole;
    }

    public static String getname() {
        return name;
    }


    public Rectangle getContinueButtonRect() {
        return continueButtonRect;
    }

    public Rectangle getExitButtonRect() {
        return exitButtonRect;
    }

    public Rectangle getRestartButtonRect() {
        return restartButtonRect;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isShowScoreboard() {
        return showScoreboard;
    }

    public void setShowScoreboard(boolean showScoreboard) {
        this.showScoreboard = showScoreboard;
    }

    /**
     * add key listener from controller(gamebaord) into view
     */
    public void addKeyListenerfromGameboard(KeyListener keyEvent){
        this.addKeyListener(keyEvent);
    }


    /**
     * adds mouselistener form controller(gameboard) into view
     */
    public void addMouseListenerfromGameboard(MouseListener mouseEvent){
        this.addMouseListener(mouseEvent);
    }


    /**
     * adds mouse motion listener from controller into view
     */
    public void addMouseMotionListenerfromGameboard(MouseMotionListener mouseEvent){
        this.addMouseMotionListener(mouseEvent);
    }

}
