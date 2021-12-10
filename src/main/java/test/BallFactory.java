package test;

import test.Controller.BallController;

import java.awt.*;
import java.awt.geom.Point2D;

public class BallFactory {

    public BallController getBallType(String ballType, Point2D ballPos)
    {
        if(ballType == null)
            return null;

        if (ballType.equalsIgnoreCase("RUBBER"))
        {
            return new RubberBall(ballPos);
        }


        return null ;
    }
}
