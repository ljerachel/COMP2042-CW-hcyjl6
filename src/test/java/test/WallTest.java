package test;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class WallTest {

    public Wall wall ;
    public Player player;
    public Ball ball ;

    WallTest() {
        wall = new Wall(new Rectangle(20, 10, 10, 4), 30, 3, 6 / 2, new Point(10, 40));
        Rectangle container = new Rectangle(10,20,30 , 10);
        Point a = new Point(20,40 );
        player = new Player(a,10,2,container);
        ball = new RubberBall(a);  // a is center
    }

// halo how to test
        @Test
        void move () {

        wall.move() ;

        }

        //@Test
        //idk how to do
        /*void findImpacts () {
        wall.setBallCount(3);
        wall.setArea(new Rectangle(50,0 , 20 ,4));
        wall.findImpacts();  // ball's Y = 20 , area'y + area height = 14
        //assertEquals(14,wall.getArea().getY() + wall.getArea().getHeight() );
        assertEquals(2,wall.getBallCount());*/

        //}


        @Test
        void ballReset () {
            wall.ballReset();
            assertEquals(new Point(10,40),wall.getBall().getPosition());

        }

       // @Test
       /* void wallReset () {
            wall.bricks = {};
            wall.wallReset();
            assertEquals(0,wall.getCurrenthighscore());


        }*/

        @Test
        void touchIcon () {
            wall.touchIcon(new Point2D.Double(12,42));
            assertTrue(wall.isLifeCollected());
        }

        @Test
        void ballEnd () {
            wall.setBallCount(3);
            assertFalse(wall.ballEnd());
        }

        @Test
        void isDone () {
            wall.setBrickCount(30);
            assertFalse(wall.isDone());
        }

    //@Test
        /*void nextLevel () {
            wall.bricks =

        }*/

        @Test
        void hasLevel () {
        }

        @Test
        void setBallXSpeed () {

            wall.setBallXSpeed(8);
            assertEquals(8, wall.getBall().getSpeedX());

        }

        @Test
        void resetBallCount () {
        }



}