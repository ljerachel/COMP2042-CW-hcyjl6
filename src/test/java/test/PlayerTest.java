package test;

import org.junit.jupiter.api.Test;
import test.Controller.BallController;
import test.Controller.PlayerController;
import test.Model.RubberBall;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    public PlayerController player ;
    public Point a ;
    public Rectangle container ;


    PlayerTest()
    {
        a = new Point(20,20 );
        container = new Rectangle(10,20,30 , 10);
        player = PlayerController.getInstance(a,10,5,container);


    }

    @Test
    void impact()
    {

        Point a = new Point(20,20 );
        BallController b = new RubberBall(a);  // a is center

        player.impact(b);  // player face positioned at a too
        assertTrue(player.getPlayerFace().contains((Point2D) a));

    }

    @Test
    void move() {
        int min = 25 ;
        int max = 45 ;

        Point ballpoint = new Point(20,20 );
        Rectangle container = new Rectangle(10,20,30 , 10);
        player.move();
        assertEquals(new Point(15,20 ), player.getPlayerFace().getBounds().getLocation());



    }

    @Test
    void moveLeftAndRight() {
        player.moveLeft();
        assertEquals(-5,player.playerModel.getMoveAmount());
        player.movRight();
        assertEquals(5,player.playerModel.getMoveAmount());
    }


    @Test
    void stop() {
        assertEquals(0,player.playerModel.getMoveAmount());
    }


    @Test
    void moveTo() {
        Point p = new Point(50,50);
        player.moveTo(p);
        int x = 45; // ballPoint.x - (int)playerFace.getWidth()/2,ballPoint.y // ask emily!!!!!!!!!!!!!!!
        int y = 50 ;
        Point ans = new Point(45, 50);
        assertEquals(ans,player.getPlayerFace().getBounds().getLocation());
    }
}
