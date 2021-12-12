package test.Controller;

import test.Model.PlayerModel;
import test.Model.RubberBall;
import test.View.PlayerView;

import java.awt.*;

public class PlayerController {
    public PlayerView playerView;
    public PlayerModel playerModel;
    public static PlayerController playerInstance;
    public Point ballPoint ;
    public Rectangle playerFace ;
    public BallController ballController ;


    private PlayerController(){}

    /**
     * @param ballPoint position of the ball
     * @param width width of the player
     * @param height height of the player
     * @param container rectangle container for player
     */
    private PlayerController(Point ballPoint,int width,int height,Rectangle container)
    {
        playerView = new PlayerView();
        playerModel = new PlayerModel(ballPoint, width, height, container);
        ballPoint = playerModel.getBallPoint();
        playerFace = playerModel.getPlayerFace();
        ballController = new RubberBall(ballPoint);  // not sure
    }

    /**
     * @return a playercontroller instance
     */
    public static PlayerController getInstance() {
        if (playerInstance == null) {
            playerInstance = new PlayerController();
        }
        return playerInstance;

    }


    /**
     * singleton design
     * @return a playercontroller instance
     */
    public static PlayerController getInstance(Point ballpos,int width, int height, Rectangle container)
    {
        if (playerInstance == null)
        {
            playerInstance = new PlayerController(ballpos,width,height,container);
        }
        return playerInstance;
    }

    /**
     * controls the movement of the player's rectangle
     */
    public void move(){
        double x = playerModel.getBallPoint().getX() + playerModel.getMoveAmount();

        if(x < playerModel.getMin() || x > playerModel.getMax())
            return;
        playerModel.getBallPoint().setLocation(x,playerModel.getBallPoint().getY());
        playerFace.setLocation(playerModel.getBallPoint().x - (int)playerFace.getWidth()/2,playerModel.getBallPoint().y);
    }


    /**
     * the player rectangle moves left by 5 units
     */
    public void moveLeft(){
        playerModel.setMoveAmount(-PlayerModel.DEF_MOVE_AMOUNT) ;

    }

    /**
     * the player moves right by 5 units
     */
    public void movRight(){
        playerModel.setMoveAmount(PlayerModel.DEF_MOVE_AMOUNT) ;
    }

    /**
     * player stays stagnant
     */
    public void stop(){
        playerModel.setMoveAmount(0) ;
    }

    /**
     * @param p the middle position (starting position)
     * resets the position of the player's rectangle during start of each round / lose the ball
     */
    public void moveTo(Point p){
        ballPoint = playerModel.getBallPoint();
        ballPoint.setLocation(p);
        playerFace.setLocation(ballPoint.x - (int)playerFace.getWidth()/2,ballPoint.y);
    }

    /**
     * @param b ball
     * @return the position of the ball when it impacts with the player's rectangle
     */
    public boolean impact(BallController b){
        return playerFace.contains(b.getPosition()) && playerFace.contains(b.getDown()) ;
    }

    public Color getInnerColor()
    {
        return PlayerModel.INNER_COLOR;
    }

    public Color getBorderColor()
    {
        return PlayerModel.BORDER_COLOR;
    }

    public Rectangle getPlayerFace()
    {
        return playerModel.getPlayerFace();
    }
}
