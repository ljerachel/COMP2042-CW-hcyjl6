package test;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

import org.junit.Assert ;
import org.junit.jupiter.api.Test;


import static org.junit.Assert.assertEquals;


class BallTest {

    private Point2D center ;
    Ball ball ;


    @org.junit.jupiter.api.Test
    public void testMove() {
        center = new Point2D.Double(100, 20);

        Ball ball = new RubberBall(center);
        ball.setSpeed(5,5);
        ball.move();
        assertEquals(new Point(105,25),ball.getPosition() );
    }

    @org.junit.jupiter.api.Test
    public void testSetSpeed() {
        center = new Point2D.Double(100, 2);
        Ball ball = new RubberBall(center);
        ball.setSpeed(2,2);
        assertEquals(2,ball.getSpeedX());
        assertEquals(2,ball.getSpeedY());
    }


    @Test
    void TestGetBallFace() {
        center = new Point2D.Double(100, 2);
        Ball ball = new RubberBall(center);
        assertEquals(center, ball.getPosition() ); ;
    }

    @Test
    void TestReverse() {

        center = new Point2D.Double(100, 2);
        Ball ball = new RubberBall(center);

        ball.setSpeed(3,4);
        ball.reverseX();
        ball.reverseY();
        assertEquals(-3,ball.getSpeedX());
        assertEquals(-4,ball.getSpeedY());

    }



   @Test
   void MoveTo() {
        center = new Point2D.Double(10, 20);
        Ball ball = new RubberBall(center);
        RectangularShape tmp = (RectangularShape) ball.getBallFace();
        ball.moveTo(new Point(20,40));
        assertEquals(15,(int) tmp.getX()) ;
        assertEquals(35,(int) tmp.getY());
    }



}