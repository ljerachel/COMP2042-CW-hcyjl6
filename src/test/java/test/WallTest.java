package test;

import org.junit.jupiter.api.Test;
import test.Controller.BallController;
import test.Controller.BrickController;
import test.Controller.PlayerController;
import test.Model.ClayBrick;
import test.Model.RubberBall;
import test.Model.Wall;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class WallTest {

    public Wall wall ;
    public PlayerController player;
    public BallController ball ;
    public BrickController[] bricks ;

    WallTest() {
        wall = new Wall(new Rectangle(20, 10, 10, 4), 30, 3, 6 / 2, new Point(10, 40));
        Rectangle container = new Rectangle(10,20,30 , 10);
        Point a = new Point(20,40 );
        ball = new RubberBall(a);  // a is center
        bricks = wall.getBricks();
    }

        @Test
        void move () {
            wall.ballReset();
            wall.move();
            assertNotEquals(new Point(300, 430), wall.getBall().getPosition());

        }

       @Test
        void findImpacts () {
      /*  wall.setBallCount(3);
        wall.setArea(new Rectangle(50,0 , 20 ,4));
        wall.findImpacts();
        assertEquals(2,wall.getBallCount());
*/


           int ball1= wall.getBall().getSpeedY();
           int ball2;
           int ballX = wall. getBall().getSpeedX() ;
           wall.setBricks(wall.makeSingleTypeLevel(new Rectangle(0,0,600,450) , 30, 3, 6/2,1));
           //Testing if ball y speed reverses if it impoct with player
           wall.findImpacts();
           assertEquals(-ball1,wall.getBall().getSpeedY());
           ball2 = wall.getBall().getSpeedY();


            //Testing if ball x speed reverses if it impact with border

           wall.getBall (). moveTo (new Point (602,238)) ;
           wall.findImpacts();
           assertEquals(-ballX,wall.getBall().getSpeedX());

           //Testing if ball y speed reverses if it impact with top borderline
           wall.getBall().moveTo(new Point(442, -2)) ;
           wall. findImpacts();
           assertEquals(-ball2, wall.getBall().getSpeedY());
           wall.getBall().moveTo (new Point (314,454)) ;
        }


        @Test
        void ballReset() {
            wall.setBricks(wall.makeSingleTypeLevel(new Rectangle(0,0,600,450) , 30, 3, 6/2,1));
            wall.ballReset();
            assertEquals(new Point(10,40),wall.getBall().getPosition());
        }

        @Test
     void wallReset () {

        BrickController b = new ClayBrick(new Point(20,20),new Dimension(10,10));
                if(b.isBroken())
                {
                    wall.wallReset();
                }

        assertEquals(3,wall.getBallCount());
        }



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

        @Test
        void testLevels ()
        {

            wall.nextLevel();
            assertEquals(1,wall.getLevel());
            wall.nextLevel();
            assertEquals(2,wall.getLevel());
            wall.nextLevel();
            assertTrue(wall.hasLevel());

        }



        @Test
        void setBallXSpeed () {

            wall.setBallXSpeed(8);
            assertEquals(8, wall.getBall().getSpeedX());

        }

        @Test
        void resetBallCount () {
        assertEquals(3, wall.getBallCount());
        }



}
