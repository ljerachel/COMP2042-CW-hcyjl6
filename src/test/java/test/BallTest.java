package test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;


import org.junit.jupiter.api.Test;
import test.Controller.BallController;
import test.Model.RubberBall;


import static org.junit.Assert.assertEquals;


class BallTest {

    private Point2D center ;
    BallController ball ;


    public BallTest()
    {

        center = new Point2D.Double(100, 20);
        BallController ball = new RubberBall(center);
    }
    @Test
    public void testMove() {
        ball.setSpeed(5,5);  //
        ball.move();
        assertEquals(new Point(105,25),ball.ballModel.getPosition() );
    }

    @Test
    public void testSetSpeed() {

        ball.setSpeed(2,2);
        assertEquals(2,ball.ballModel.getSpeedX());
        assertEquals(2,ball.ballModel.getSpeedY());
    }


    @Test
    void TestGetBallFace() {
        /*center = new Point2D.Double(100, 2);
        Ball ball = new RubberBall(center);*/
        assertEquals(center, ball.ballModel.getPosition() ); ;
    }

    @Test
    void TestReverse() {

       /* center = new Point2D.Double(100, 2);
        Ball ball = new RubberBall(center);*/

        ball.setSpeed(3,4);
        ball.reverseX();
        ball.reverseY();
        assertEquals(-3,ball.ballModel.getSpeedX());
        assertEquals(-4,ball.ballModel.getSpeedY());

    }



   @Test
   void MoveTo() {
        center = new Point2D.Double(10, 20);
        BallController ball = new RubberBall(center);
        RectangularShape tmp = (RectangularShape) ball.ballModel.getBallFace();
        ball.moveTo(new Point(20,40));
        assertEquals(15,(int) tmp.getX()) ;
        assertEquals(35,(int) tmp.getY());
    }



}