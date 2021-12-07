package test;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void impact()
    {
        Rectangle container = new Rectangle(10,20,30 , 10);
        Point a = new Point(20,20 );
        Player player = new Player(a,10,2,container);
        Ball b = new RubberBall(a);  // a is center

        player.impact(b);  // player face positioned at a too
        assertTrue(player.getPlayerFace().contains(a));

    }

    @Test
    void move() {
        int min = 25 ;
        int max = 45 ;

        Point ballpoint = new Point(20,20 );
        Rectangle container = new Rectangle(10,20,30 , 10);
        Player player = new Player(ballpoint,10,5,container);


        player.move();
        assertEquals(new Point(15,20 ), player.getPlayerFace().getBounds().getLocation());



    }

    @Test
    void moveLeft() {
        Point a = new Point(20,20 );
        Rectangle container = new Rectangle(10,20,30 , 10);
        Player player = new Player(a,10,2,container);
        player.moveLeft();
        assertEquals(-5,player.getMoveAmount());

    }

    @Test
    void movRight() {

        Point a = new Point(20,20 );
        Rectangle container = new Rectangle(10,20,30 , 10);
        Player player = new Player(a,10,2,container);
        player.movRight();
        assertEquals(5,player.getMoveAmount());

    }

    @Test
    void stop() {
        Point a = new Point(20,20 );
        Rectangle container = new Rectangle(10,20,30 , 10);
        Player player = new Player(a,10,2,container);
        assertEquals(0,player.getMoveAmount());
    }


    @Test
    void moveTo() {
        Point a = new Point(20,20 );
        Rectangle container = new Rectangle(10,20,30 , 10);
        Player player = new Player(a,10,2,container);
        Point p = new Point(50,50);
        player.moveTo(p);
        int x = 45; // ballPoint.x - (int)playerFace.getWidth()/2,ballPoint.y // ask emily!!!!!!!!!!!!!!!
        int y = 50 ;
        Point ans = new Point(45, 50);
        assertEquals(ans,player.getPlayerFace().getBounds().getLocation());



    }
}