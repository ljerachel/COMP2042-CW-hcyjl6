package test.Controller;

import java.awt.*;
import java.awt.event.KeyEvent;
import test.Model.Wall;
import test.View.GameBoardView;
import test.View.HomeMenu;


import javax.swing.*;
import java.awt.event.*;



public class GameBoardController extends JComponent implements KeyListener,MouseListener,MouseMotionListener {


    private GameBoardView gameBoardView;
    private Wall wall ;


    public GameBoardController(Wall wall, GameBoardView gameBoardView)
    {
        this.gameBoardView = gameBoardView;
        this.wall = wall ;
        initialize();
    }


private void initialize()
{
    this.gameBoardView.addKeyListenerfromGameboard(this);
    this.gameBoardView.addMouseListenerfromGameboard(this);
    this.gameBoardView.addMouseMotionListenerfromGameboard(this);
}


    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * @param keyEvent A and D is to move the player left and right, Esc is to show pause menu, SPACE is to pause game, f1 is to show debug console , F restarts the game after scoreboard
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
                gameBoardView.setShowPauseMenu(!gameBoardView.isShowPauseMenu());
                repaint();
                gameBoardView.getGameTimer().stop();
                break;
            case KeyEvent.VK_SPACE:
                if(!gameBoardView.isShowPauseMenu())
                    if (gameBoardView.getGameTimer().isRunning())
                        gameBoardView.getGameTimer().stop();
                    else
                        gameBoardView.getGameTimer().start();

                break;
            case KeyEvent.VK_F1:
                if(keyEvent.isAltDown() && keyEvent.isShiftDown())
                    gameBoardView.getDebugConsole().setVisible(true);
                break;

            case KeyEvent.VK_F:
                if(gameBoardView.isShowScoreboard()) {
                    gameBoardView.setShowScoreboard(false);
                    wall.setLifeCollected(false);
                    wall.setShowWinningMsg(false);
                    gameBoardView.getGameTimer().start();

                    repaint();
                }
            default:
                PlayerController.getInstance().stop();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        PlayerController.getInstance().stop();
    }

    /**
     * when pause menu is displayed
     * when restart button is clicked, game is restarted
     * when exit button is clicked, window is closed, system is exited
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(!gameBoardView.isShowPauseMenu())
            return;
        if(gameBoardView.getContinueButtonRect().contains(p)){
            gameBoardView.setShowPauseMenu(false);
            repaint();
        }
        else if(gameBoardView.getRestartButtonRect().contains(p)){
            gameBoardView.setMessage("Restarting Game...");
            wall.ballReset();
            wall.wallReset();
            gameBoardView.setShowPauseMenu(false);
            repaint();
        }
        else if(gameBoardView.getExitButtonRect().contains(p)){
            System.exit(0);
        }


    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(gameBoardView.getExitButtonRect() != null && gameBoardView.isShowPauseMenu()) {
            if (gameBoardView.getExitButtonRect().contains(p) || gameBoardView.getContinueButtonRect().contains(p) || gameBoardView.getRestartButtonRect().contains(p))
                this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                this.setCursor(Cursor.getDefaultCursor());
        }
        else{
            this.setCursor(Cursor.getDefaultCursor());
        }
    }



    public Wall getWall() {
        return gameBoardView.getWall();
    }
}
