package test.Model;

import test.BallFactory;
import test.Controller.BallController;
import test.Controller.BrickController;
import test.Controller.PlayerController;
import test.brickFactory;

import java.awt.*;
import java.util.Random;

public class WallModel {
    private static final int LEVELS_COUNT = 5;

    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;
    private static final int STONE = 4;

    private Random rnd;
    private Rectangle area;


    BrickController[] bricks;


    test.BallFactory BallFactory ;
    BallController ball;
    PlayerController player;
    test.brickFactory brickFactory;


    private boolean lifeCollected = false  ;
    private BrickController[][] levels;
    private int level;

    private boolean showWinningMsg  = false  ;


    private Point startPoint;


    private int brickCount;



    private int ballCount;
    private boolean ballLost;
    private int currenthighscore ;
    private static int finalhighscore;


    /**
     * @param drawArea area of the entire wall
     * @param brickCount number of bricks
     * @param lineCount number of lines to be filled with bricks
     * @param brickDimensionRatio
     * @param ballPos position of the ball
     */
    public Wall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos){

        this.startPoint = new Point(ballPos);

        levels = makeLevels(drawArea,brickCount,lineCount,brickDimensionRatio);
        level = 0;


        this.ball = getBall();
        ballCount = 3;
        ballLost = false;

        rnd = new Random();  // random number

        makeBall(ballPos);
        int speedX,speedY;
        speedX = 8;
        speedY = -3;

        ball.setSpeed(speedX,speedY);
        player = PlayerController.getInstance((Point)ballPos.clone(),150,10,drawArea) ;
        area = drawArea;




    }


}
