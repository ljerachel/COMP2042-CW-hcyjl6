/*
package test.Model;

import test.BallFactory;
import test.Controller.BallController;
import test.Controller.BrickController;
import test.Controller.PlayerController;
import test.brickFactory;

import java.awt.*;
import java.util.Random;

public class WallModel {
    public static final int LEVELS_COUNT = 5;

    public static final int CLAY = 1;
    public static final int STEEL = 2;
    public static final int CEMENT = 3;
    public static final int STONE = 4;

    private Random rnd;
    private Rectangle area;
    BrickController[] bricks;

    BallFactory BallFactory ;
    BallController ball;
    PlayerController player;
    brickFactory brickFactory;


    private boolean lifeCollected = false ;
    public BrickController[][] levels;
    public int level;

    private boolean showWinningMsg  = false  ;



    private Point startPoint;


    private int brickCount;



    private int ballCount;



    private boolean ballLost;

    private int currenthighscore ;


    private static int finalhighscore;


    */
/**
     * @param drawArea area of the entire wall
     * @param brickCount number of bricks
     * @param lineCount number of lines to be filled with bricks
     * @param brickDimensionRatio
     * @param ballPos position of the ball
     *//*

    public WallModel(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos){

        this.startPoint = new Point(ballPos);

        //levels = makeLevels(drawArea,brickCount,lineCount,brickDimensionRatio);
        level = 0;


        this.ball = getBall();
        ballCount = 3;
        ballLost = false;

        rnd = new Random();  // random number

        //makeBall(ballPos);
        int speedX,speedY;
        speedX = 8;
        speedY = -3;

        //ball.setSpeed(speedX,speedY);
        //player = PlayerController.getInstance((Point)ballPos.clone(),150,10,drawArea) ;
        area = drawArea;




    }


    public Rectangle getArea() {
        return area;
    }

    public int getBrickCount(){
        return brickCount;
    }

    public int getBallCount(){
        return ballCount;
    }

    */
/**
     * @return check if the ball is lost within the frame
     *//*

    public boolean isBallLost(){
        return ballLost;
    }

    public boolean isLifeCollected() {
        return lifeCollected;
    }

    public boolean ballEnd(){
        return ballCount == 0;
    }

    public void setLifeCollected(boolean lifeCollected) {
        this.lifeCollected = lifeCollected;
    }

    */
/**
     * @return if the level is passed (if all the bricks are broken)
     *//*

    public boolean isDone(){
        return brickCount == 0;
    }


    public BrickController[][] getLevels() {
        return levels;
    }

  */
/*  public void setLevelsNextLevel(BrickController[] levels)
    {
        this.levels[level++] = levels;
    }
*//*

    public void setLevels(BrickController[][] levels)
    {
        this.levels = levels;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    */
/**
     * @return true if the level is within that of predefined levels
     *//*

    public boolean hasLevel(){
        return level < levels.length;
    }

    */
/**
     * @param s speed of ball
     *//*

    public void setBallXSpeed(int s){
        ball.setXSpeed(s);
    }

    */
/**
     * @param s speed of ball
     *//*

    public void setBallYSpeed(int s){
        ball.setYSpeed(s);
    }



    public int getCurrenthighscore() {
        return currenthighscore;
    }


    public static int getFinalhighscore() {
        return finalhighscore;
    }

    public void setBallCount(int ballCount) {
        this.ballCount = ballCount;
    }



    public BallController getBall() {
        return ball;
    }


    public void setArea(Rectangle area) {
        this.area = area;
    }

   */
/* public Rectangle getArea() {
        return area;
    }*//*


    public void setBrickCount(int brickCount) {
        this.brickCount = brickCount;
    }


    public BrickController[] getBricks() {
        return bricks;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setBricks(BrickController[] bricks) {
        this.bricks = bricks;
    }


    public boolean isShowWinningMsg() {
        return showWinningMsg;
    }

    public void setShowWinningMsg(boolean showWinningMsg) {
        this.showWinningMsg = showWinningMsg;
    }

    public void setBallLost(boolean ballLost) {
        this.ballLost = ballLost;
    }

    public static void setFinalhighscore(int finalhighscore) {
        WallModel.finalhighscore = finalhighscore;
    }

    public void setCurrenthighscore(int currenthighscore) {
        this.currenthighscore = currenthighscore;
    }



}
*/
