package test;

import org.junit.jupiter.api.Test;
import test.Controller.BrickController;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class BrickTest {
    public boolean broken = true ;


    @Test
    void setImpact() {

        Point p = new Point(20,20);
        BrickController brick = new CementBrick(p,new Dimension(2,1));
        brick.isBroken();
        boolean a = brick.setImpact(p,3);

        assertEquals(false,a);
    }


    @Test
    void findImpact() {
        Point p = new Point(10,20);
        Dimension size = new Dimension(10,5);
        BrickController brick = new CementBrick(p,size);
        RubberBall ball = new RubberBall(p) ;
        Shape brickFace = ((CementBrick) brick).makeBrickFace(p,size);
        int impact = brick.findImpact(ball); // 15,20 < brickface(10-20,20-25) -- left_impact
        assertEquals(300,impact);

    }


    @Test
    void isBroken() {
        Point p = new Point(20,20);
        BrickController brick = new CementBrick(p,new Dimension(2,1));
        assertEquals(false, brick.isBroken());
    }

    @Test
    void repair()
    {

        boolean broken = true;
        Point p = new Point(20,20);
        BrickController brick = new CementBrick(p,new Dimension(2,1));

        brick.setStrength(1) ;
        brick.setFullStrength(3);
        brick.repair();
        assertEquals(2,brick.getStrength());
    }

    @Test
    void impact() {
        Point p = new Point(20,20);
        BrickController brick = new CementBrick(p,new Dimension(2,1));
        brick.setStrength(3);
        brick.impact();
        assertEquals(2,brick.getStrength());
        assertTrue(!brick.isBroken());

    }
}