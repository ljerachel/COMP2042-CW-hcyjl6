/*
package test.Controller;
// add variable levels,
import test.*;
import test.Model.WallModel;


import java.awt.*;
import java.awt.geom.Point2D;

import static test.Model.WallModel.*;

public class WallController {

    BallFactory BallFactory ;
    BallController ballController;
    public PlayerController player;
    brickFactory brickFactory;
    public BrickController[][] levels ;
    public WallModel wallModel;
    BrickController[] bricks ;




    public WallController(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos)
    {


        player = PlayerController.getInstance((Point)ballPos.clone(),150,10,drawArea) ;
        //wallModel.setLevels(makeLevels(drawArea,brickCount,lineCount,brickDimensionRatio));
        levels = makeLevels(drawArea,brickCount,lineCount,brickDimensionRatio);
        makeBall(ballPos);
        getBall().setSpeed(ballController.getSpeedX(),ballController.getSpeedY());
        wallModel = new WallModel(drawArea, brickCount, lineCount, brickDimensionRatio, ballPos);
    }

    */
/**
     * @param drawArea the area
     * @param brickCnt total number of bricks in the wall
     * @param lineCnt lines of bricks on the wall
     * @param brickSizeRatio the ratio of the length to height of the brick
     * @param type type of brick
     * @return a list of only clay bricks ( the wall )
     *//*

    public BrickController[] makeSingleTypeLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int type){
        */
/*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         *//*

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




    */
/**
     * @param drawArea area occupied by the total number of bricks
     * @param brickCnt total number of bricks
     * @param lineCnt line occupied by bricks
     * @param brickSizeRatio  the ratio of the length to height of the brick
     * @param typeA first type of brick in alternating types of brick
     * @param typeB 2nd type of brick in alternating types of brick
     * @return the chessboard layout of bricks
     *//*

    private BrickController[] makeChessboardLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB){
        */
/*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         *//*

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
        */
/*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         *//*

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


    */
/**
     * @param drawArea the entire area for the wall of bricks
     * @param brickCount number of bricks in the wall
     * @param lineCount number of lines of bricks
     * @param brickDimensionRatio
     * @return levels with different brick wall layouts
     *//*

    private BrickController[][] makeLevels(Rectangle drawArea,int brickCount,int lineCount,double brickDimensionRatio){
        BrickController[][] tmp = new BrickController[LEVELS_COUNT][];
        tmp[0] = makeSingleTypeLevel(drawArea,brickCount,lineCount,brickDimensionRatio, CLAY);
        tmp[1] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio, CLAY,CEMENT);
        tmp[2] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,STEEL);
        tmp[3] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,STEEL,CEMENT);
        tmp[4] = makeLevelHard(drawArea,brickCount,lineCount,brickDimensionRatio,STONE,CEMENT);
        return tmp;
    }


    */
/**
     * consists of the movements of both player rectangle and ball
     *//*

    public void move(){
        //player.move();
        getPlayer().move();
        ballController.move();
    }


    public void findImpacts(){

        //if(player.getInstance().impact(ballController))
        if(getPlayer().impact(ballController)){
            ballController.reverseY();
        }

        else if(impactWall()){
            */
/*for efficiency reverse is done into method impactWall
             * because for every brick program checks for horizontal and vertical impacts
             *//*

            wallModel.setCurrenthighscore(wallModel.getCurrenthighscore()+1);
            wallModel.setBrickCount(getBrickCount()-1);

        }
        else if(impactBorder()) {   // hit the border of the wall
            ballController.reverseX();
        }
        else if(ballController.getPosition().getY() < wallModel.getArea().getY()){
            ballController.reverseY();
        }
        else if(ballController.getPosition().getY() > wallModel.getArea().getY() +  wallModel.getArea().getHeight()){
            //ballCount--;
            wallModel.setBallCount(getBallCount()-1);
            wallModel.setBallLost(true);
            //ballLost = true;
        }

    }


    private void makeBall(Point2D ballPos){
        BallFactory = new BallFactory();
        ballController = BallFactory.getBallType("RUBBER",ballPos);
    }

    */
/**
     * @return if the brick can have an impact
     *//*

    private boolean impactWall(){
        //ball = getBall();
        for(BrickController b : getBricks()){

            switch(b.findImpact(ballController))
            {
                //Vertical Impact
                case Brick.UP_IMPACT:
                    ballController.reverseY();
                    return b.setImpact(ballController.getDown(), Crack.UP);

                case Brick.DOWN_IMPACT:
                    ballController.reverseY();
                    return b.setImpact(ballController.getUp(),Crack.DOWN);

                //Horizontal Impact
                case Brick.LEFT_IMPACT:
                    ballController.reverseX();
                    return b.setImpact(ballController.getRight(),Crack.RIGHT);

                case Brick.RIGHT_IMPACT:
                    ballController.reverseX();
                    return b.setImpact(ballController.getLeft(),Crack.LEFT);  // return true if impact is hit
            }


        }
        return false;
    }


    private boolean impactBorder(){
        Point2D p = ballController.getPosition();// position of the ball
        return ((p.getX() < wallModel.getArea().getX()) ||(p.getX() > (wallModel.getArea().getX() + wallModel.getArea().getWidth())));
    }



    */
/**
     * reset the ball to first position
     *//*

    public void ballReset(){
        player.getInstance().moveTo(wallModel.getStartPoint());
        ballController.moveTo(wallModel.getStartPoint());
        int speedX,speedY;
        speedX = 8;
        speedY = -3;

        ballController.setSpeed(speedX,speedY);
        wallModel.setBallLost(false);

    }


    */
/**
     * reset the entire wall ( giving 3 ball chances and original brick layout)
     *//*

    public void wallReset(){
        for(BrickController b : getBricks())
            b.repair();
        wallModel.setBallCount(getBricks().length);  //
        wallModel.setBallCount(3);
        //ballCount = 3;
        wallModel.setFinalhighscore(getCurrenthighscore());
        //finalhighscore = currenthighscore;
        wallModel.setCurrenthighscore(0);

    }


    public boolean isLifeCollected()
    {
        return wallModel.isLifeCollected();
    }

    public void touchIcon(Point2D p)
    {
        if (!isLifeCollected() ) {
            if (ballController.getPosition().getX() < p.getX() + 4 && ballController.getPosition().getX() > p.getX() - 4) {

                if (ballController.getPosition().getY() < p.getY() + 4 && ballController.getPosition().getY() > p.getY() - 4) {
                    //ballCount++;
                    wallModel.setBallCount(getBallCount()+1);
                    //System.out.println("ball touch icon");

                    setShowWinningMsg(true);
                    wallModel.setLifeCollected(true);

                }
            }

        }
    }


    */
/**
     * go to the next level
     *//*

    public void nextLevel(){
        //setLevelsNextLevel(wallModel.getBricks());
        bricks = levels[wallModel.getLevel()+1];
        wallModel.setBrickCount(bricks.length);
        //this.brickCount = bricks.length;
    }


  */
/*  public void setLevelsNextLevel(BrickController[] level)
    {
        levels[wallModel.getLevel()] = levels ;
        wallModel.getLevels()[wallModel.getLevel()] = levels;
    }*//*


    */
/**
     * reset ball count to full (3)
     *//*

    public void resetBallCount(){
        wallModel.setBallCount(3);
    }

    */
/**
     * @param point position of the brick
     * @param size size of the brick
     * @param type bricktype
     * @return brick chosen
     *//*

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


    public boolean isDone(){
        return wallModel.isDone();
    }

    public boolean hasLevel(){
        return wallModel.hasLevel();
    }



    public void setBallXSpeed(int s){
        wallModel.setBallXSpeed(s);
    }


    public void setBallYSpeed(int s){
        wallModel.setBallYSpeed(s);
    }

    public void setShowWinningMsg(boolean showWinningMsg) {
        wallModel.setShowWinningMsg(showWinningMsg);
    }

    public boolean isBallLost(){
        return wallModel.isBallLost();

}


    public boolean ballEnd(){
        return wallModel.ballEnd();
    }


    public boolean isShowWinningMsg() {
        return wallModel.isShowWinningMsg();
    }



    public static int getFinalhighscore() {
        return WallModel.getFinalhighscore();
    }

    public int getCurrenthighscore() {
        return wallModel.getCurrenthighscore();
    }

   */
/* public BrickController[] getBricks() {
        return wallModel.getBricks();
    }*//*


    public BrickController[] getBricks() {
        return bricks;
    }



    public int getBallCount(){
        return wallModel.getBallCount();
    }

    public int getBrickCount(){
        return wallModel.getBrickCount();
    }


    public BallController getBall()
    {
        return ballController;
    }



    public void setBall(BallController ballController)
    {
        this.ballController = ballController ;
    }

    public PlayerController getPlayer()
    {
        return player;
    }

    public void setPlayer(PlayerController player)
    {
        this.player = player;
    }


}








*/
