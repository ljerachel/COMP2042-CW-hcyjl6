package test.Model;

import test.Controller.BrickController;

import java.awt.*;

public class brickFactory {
    public BrickController getBrickType(String brickType, Point pos, Dimension size)
    {
        if (brickType.equalsIgnoreCase("CEMENT"))
        {
            return new CementBrick(pos,size);
        }
        else if (brickType=="CLAY")
        {
            return new ClayBrick(pos, size);
        }
        else if (brickType=="STONE")
        {
            return new StoneBrick(pos,size);
        }

        else if (brickType=="STEEL")
        {
            return new SteelBrick(pos,size);
        }

        return null ;
    }
}