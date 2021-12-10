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
    Shape ballFace ;

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
        ballModel.setSpeed(x,y);
    }


    public void setXSpeed(int s){
        ballModel.setXSpeed(s);
    }

    public void setYSpeed(int s){
        ballModel.setYSpeed(s);
    }


    public void move(){
        center = ballModel.getCenter();
        RectangularShape tmp = (RectangularShape) ballFace;
        center.setLocation((center.getX() + ballModel.getSpeedX()),(center.getY() + ballModel.getSpeedY()));
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
        Shape s  = ballFace;
        return s ;
    }


    protected abstract Shape makeBall(Point2D center,int radius);


    public void reverseX(){

        setXSpeed(-getSpeedX());
    }

    /**
     * set the direction of the ball movement to the opposite (vertically)
     */
    public void reverseY(){
        setYSpeed(-getSpeedY());
    }

   public Point2D getPosition() {
       return ballModel.getCenter();
   }

    public Color getBorderColor(){
        return ballModel.getBorderColor();
    }

    public Color getInnerColor(){
        return ballModel.getInnerColor();
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
        return ballModel.getSpeedX();
    }

    public int getSpeedY(){
        return ballModel.getSpeedY();
    }


}
