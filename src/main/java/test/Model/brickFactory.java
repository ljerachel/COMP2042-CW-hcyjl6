package test.Model;

import test.Controller.BrickController;

import java.awt.*;

/**
 * brick Factory is a factory design
 */
public class brickFactory {
    /**
     * @param brickType type of brick
     * @param pos position of brick
     * @param size size of brick
     * @return brick instance which has they type BrickController
     */
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
