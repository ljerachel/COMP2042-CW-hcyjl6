package test.Controller;

import test.GraphicsMain;
import test.Model.BallModel;
import test.View.BallView;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

abstract public class BallController {
    public BallModel ballModel ;
    public BallView ballView ;


    Point2D center;
    Shape ballFace ;//= ballModel.getBallFace();
    int speedX ;
    int speedY;

    public BallController(Point2D center,int radius,Color inner, Color border  )
    {
        ballModel = new BallModel(center, radius, inner, border);
        ballView = new BallView();
        ballFace = makeBall(center,radius);

    }

    /**
     * @param p position of the ball during start of game
     * moves the position of the ball to the centre of the frame
     */
    public void moveTo(Point p){

        center = ballModel.getCenter();
        center.setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        ballFace = tmp;
    }

    public void setSpeed(int x,int y){
        speedX = x;
        speedY = y;
    }


    public void setXSpeed(int s){
        speedX = s;
    }

    public void setYSpeed(int s){
        speedY = s;
    }


    public void move(){
        center = ballModel.getCenter();
        RectangularShape tmp = (RectangularShape) ballFace;
        center.setLocation((center.getX() + speedX),(center.getY() + speedY));
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        setPoints(w,h);


        ballFace = tmp;
    }


    public void changeView(Graphics2D g2d)
    {
      /*  Color tmp = g2d.getColor();
        Shape s = getBallFace();

        g2d.setColor(getInnerColor());
        g2d.fill(s);

        g2d.setColor(getBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);*/


    }

    public Shape getBallFace4View()
    {
        Shape s  = getBallFace();
        return s ;
    }


    protected abstract Shape makeBall(Point2D center,int radius);


    public void reverseX(){
        speedX *= -1;
    }

    /**
     * set the direction of the ball movement to the opposite (vertically)
     */
    public void reverseY(){
        speedY *= -1;}

   public Point2D getPosition() {
       return ballModel.getCenter();
   }

    public Color getBorderColor(){
        return ballModel.getBorderColor();
    }

    public Color getInnerColor(){
        return ballModel.getInnerColor();
    }

    public Shape getBallFace(){
        return ballFace;
    }

    private void setPoints(double width,double height){
        ballModel.getUp().setLocation(center.getX(),center.getY()-(height / 2));
        ballModel.getDown().setLocation(center.getX(),center.getY()+(height / 2));

        ballModel.getLeft().setLocation(center.getX()-(width / 2),center.getY());
        ballModel.getRight().setLocation(center.getX()+(width / 2),center.getY());
    }

    public Point2D getDown() {
        return ballModel.getDown();
    }


    public Point2D getUp() {return ballModel.getUp();}

    public Point2D getRight() {return ballModel.getRight();}

    public Point2D getLeft(){return ballModel.getLeft();}

    public int getSpeedX(){
        return speedX;
    }

    public int getSpeedY(){
        return speedY;
    }


}
