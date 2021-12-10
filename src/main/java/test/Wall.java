/*
 *  Brick Destroy - A simple Arcade video game
 *
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

import test.Controller.BallController;
import test.Controller.BrickController;
import test.Controller.PlayerController;
import test.Model.PlayerModel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.Random;


/**
 * the wall of bricks
 */
public class Wall {

    private static final int LEVELS_COUNT = 5;

    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;
    private static final int STONE = 4;

    private Random rnd;
    private Rectangle area;

    public BrickController[] getBricks() {
        return bricks;
    }

    public void setBricks(BrickController[] bricks) {
        this.bricks = bricks;
    }

    BrickController[] bricks;


    BallFactory BallFactory ;
    BallController ball;
    PlayerController player;
    brickFactory brickFactory;


    private boolean lifeCollected = false  ;
    private BrickController[][] levels;
    private int level;

    public boolean isShowWinningMsg() {
        return showWinningMsg;
    }

    public void setShowWinningMsg(boolean showWinningMsg) {
        this.showWinningMsg = showWinningMsg;
    }

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

    /**
     * @param drawArea the area
     * @param brickCnt total number of bricks in the wall
     * @param lineCnt lines of bricks on the wall
     * @param brickSizeRatio the ratio of the length to height of the brick
     * @param type type of brick
     * @return a list of only clay bricks ( the wall )
     */
    public BrickController[] makeSingleTypeLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int type){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        double brickLen = drawArea.getWidth() / brickOnLine;  // length of an individual brick
        double brickHgt = brickLen / brickSizeRatio; // height of an individual brick

        brickCnt += lineCnt / 2;

        BrickController[] tmp  = new BrickController[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;

        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            double x = (i % brickOnLine) * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);
            tmp[i] = makeBrick(p,brickSize,type);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = new ClayBrick(p,brickSize);
        }
        return tmp;

    }





    /**
     * @param drawArea area occupied by the total number of bricks
     * @param brickCnt total number of bricks
     * @param lineCnt line occupied by bricks
     * @param brickSizeRatio  the ratio of the length to height of the brick
     * @param typeA first type of brick in alternating types of brick
     * @param typeB 2nd type of brick in alternating types of brick
     * @return the chessboard layout of bricks
     */
    private BrickController[] makeChessboardLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

        double brickLen = drawArea.getWidth() / brickOnLine;  // length of an individual brick
        double brickHgt = brickLen / brickSizeRatio; //

        brickCnt += lineCnt / 2;

        BrickController[] tmp  = new BrickController[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            int posX = i % brickOnLine;
            double x = posX * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);

            boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
            tmp[i] = b ?  makeBrick(p,brickSize,typeA) : makeBrick(p,brickSize,typeB);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = makeBrick(p,brickSize,typeA);
        }
        return tmp;
    }

    private BrickController[] makeLevelHard(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

        double brickLen = drawArea.getWidth() / brickOnLine;  // length of an individual brick
        double brickHgt = brickLen / brickSizeRatio; //

        brickCnt += lineCnt / 2;

        BrickController[] tmp  = new BrickController[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            int posX = i % brickOnLine;
            double x = posX * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);

            boolean b = ((line % 10 == 0 && i % 10 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
            tmp[i] = b ?  makeBrick(p,brickSize,typeA) : makeBrick(p,brickSize,typeB);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = makeBrick(p,brickSize,typeA);
        }
        return tmp;
    }




    /**
     * @param ballPos position of the ball
     */
    private void makeBall(Point2D ballPos){
        BallFactory = new BallFactory();
        ball = BallFactory.getBallType("RUBBER",ballPos);
    }

    /**
     * @param drawArea the entire area for the wall of bricks
     * @param brickCount number of bricks in the wall
     * @param lineCount number of lines of bricks
     * @param brickDimensionRatio
     * @return levels with different brick wall layouts
     */
    private BrickController[][] makeLevels(Rectangle drawArea,int brickCount,int lineCount,double brickDimensionRatio){
        BrickController[][] tmp = new BrickController[LEVELS_COUNT][];
        tmp[0] = makeSingleTypeLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY);
        tmp[1] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,CEMENT);
        tmp[2] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,STEEL);
        tmp[3] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,STEEL,CEMENT);
        tmp[4] = makeLevelHard(drawArea,brickCount,lineCount,brickDimensionRatio,STONE,CEMENT);
        return tmp;
    }

    /**
     * consists of the movements of both player rectangle and ball
     */
    public void move(){
        //player.move();
        player.getInstance().move();
        ball.move();
    }



    /**
     *
     */
    public void findImpacts(){

        if(player.getInstance().impact(ball)){
            ball.reverseY();
        }

        else if(impactWall()){
            /*for efficiency reverse is done into method impactWall
            * because for every brick program checks for horizontal and vertical impacts
            */
                currenthighscore += 1;
                brickCount--;

        }
        else if(impactBorder()) {   // hit the border of the wall
            ball.reverseX();
        }
        else if(ball.getPosition().getY() < area.getY()){
            ball.reverseY();
        }
        else if(ball.getPosition().getY() > area.getY() + area.getHeight()){
            ballCount--;
            ballLost = true;
        }

    }

    /**
     * @return if the brick can have an impact
     */
    private boolean impactWall(){
        //ball = getBall();
        for(BrickController b : bricks){

            switch(b.findImpact(ball))
            {
                //Vertical Impact
                case Brick.UP_IMPACT:
                    ball.reverseY();
                    return b.setImpact(ball.getDown(), Crack.UP);

                case Brick.DOWN_IMPACT:
                    ball.reverseY();
                    return b.setImpact(ball.getUp(),Crack.DOWN);

                //Horizontal Impact
                case Brick.LEFT_IMPACT:
                    ball.reverseX();
                    return b.setImpact(ball.getRight(),Crack.RIGHT);

                case Brick.RIGHT_IMPACT:
                    ball.reverseX();

                    return b.setImpact(ball.getLeft(),Crack.LEFT);  // return true if impact is hit
            }


        }
        return false;
    }

    /**
     * @return check if the position of the ball is within the area of the wall (area containing the player space and the bricks)
     */
    private boolean impactBorder(){
        Point2D p = ball.getPosition();// position of the ball
        return ((p.getX() < area.getX()) ||(p.getX() > (area.getX() + area.getWidth())));
    }

    public int getBrickCount(){
        return brickCount;
    }

    public int getBallCount(){
        return ballCount;
    }

    /**
     * @return check if the ball is lost within the frame
     */
    public boolean isBallLost(){
        return ballLost;
    }

    /**
     * reset the ball to first position
     */
    public void ballReset(){
        player.getInstance().moveTo(startPoint);
        ball.moveTo(startPoint);
        int speedX,speedY;
            speedX = 8;
            speedY = -3;

        ball.setSpeed(speedX,speedY);
        ballLost = false;
    }

    /**
     * reset the entire wall ( giving 3 ball chances and original brick layout)
     */
    public void wallReset(){
        for(BrickController b : bricks)
            b.repair();
        brickCount = bricks.length;
        //System.out.println(brickCount);
        ballCount = 3;
        finalhighscore = currenthighscore;
        currenthighscore = 0 ;

    }

    public boolean isLifeCollected() {
        return lifeCollected;
    }

    public void touchIcon(Point2D p)
    {
        if (!lifeCollected ) {
            if (ball.getPosition().getX() < p.getX() + 4 && ball.getPosition().getX() > p.getX() - 4) {

                if (ball.getPosition().getY() < p.getY() + 4 && ball.getPosition().getY() > p.getY() - 4) {
                    ballCount++;
                    System.out.println("ball touch icon");

                    showWinningMsg = true ;
                    lifeCollected = true;

                }
            }

        }
    }

    public boolean ballEnd(){
        return ballCount == 0;
    }

    /**
     * @return if the level is passed (if all the bricks are broken)
     */
    public boolean isDone(){
        return brickCount == 0;
    }

    /**
     * go to the next level
     */
    public void nextLevel(){
        bricks = levels[level++];
        this.brickCount = bricks.length;
    }

    /**
     * @return true if the level is within that of predefined levels
     */
    public boolean hasLevel(){
        return level < levels.length;
    }

    /**
     * @param s speed of ball
     */
    public void setBallXSpeed(int s){
        ball.setXSpeed(s);
    }

    /**
     * @param s speed of ball
     */
    public void setBallYSpeed(int s){
        ball.setYSpeed(s);
    }

    /**
     * reset ball count to full (3)
     */
    public void resetBallCount(){
        ballCount = 3;
    }

    /**
     * @param point position of the brick
     * @param size size of the brick
     * @param type bricktype
     * @return brick chosen
     */
    private BrickController makeBrick(Point point, Dimension size, int type){
        brickFactory = new brickFactory() ;
        BrickController out;

        switch(type){
            case CLAY:
                out = brickFactory.getBrickType("CLAY",point,size);
                break;
            case STEEL:
                out = brickFactory.getBrickType("STEEL",point,size);
                break;
            case CEMENT:
                out = brickFactory.getBrickType("CEMENT",point,size);
                break;
            case STONE :
                out = brickFactory.getBrickType("STONE",point,size);
                break;
            default:
                throw  new IllegalArgumentException(String.format("Unknown Type:%d\n",type));
        }
        return out;
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

   /* public Rectangle getArea() {
        return area;
    }*/

    public void setBrickCount(int brickCount) {
        this.brickCount = brickCount;
    }

}

