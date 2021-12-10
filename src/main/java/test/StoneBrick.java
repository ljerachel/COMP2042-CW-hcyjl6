package test;

import test.Controller.BrickController;

import java.awt.*;
import java.awt.Point;



public class StoneBrick extends BrickController {
    private static final String NAME = "Stone Brick";
    private static final Color DEF_INNER = new Color(0, 34, 34).darker();
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int STONE_STRENGTH = 0;



    public StoneBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,STONE_STRENGTH);
    }

    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    @Override
    public Shape getBrick() {
        return super.brickFace;
    }

}