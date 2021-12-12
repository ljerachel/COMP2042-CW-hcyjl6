package test.Model;

import test.Controller.BallController;

import java.awt.geom.Point2D;


/**
 * ball factory is a factory design pattern which produces different ball types
 */
public class BallFactory {

    /**
     * @param ballType type of ball
     * @param ballPos position of ball
     * @return instance of ball which has the type ballController
     */
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
