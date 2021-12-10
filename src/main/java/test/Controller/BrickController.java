package test.Controller;

import test.Ball;
import test.Brick;
import test.Model.BallModel;
import test.Model.BrickModel;
import test.View.BrickView;

import java.awt.*;
import java.awt.geom.Point2D;

abstract public class BrickController {


    public static final int MIN_CRACK = 1;
    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;

    public BrickView brickView ;
    public BrickModel brickModel ;
    public Shape brickFace ;


    public BrickController(String name, Point pos, Dimension size, Color border, Color inner, int strength)
    {
        brickView = new BrickView();
        brickModel = new BrickModel(name, pos, size, border, inner, strength );
        //brickFace = brickModel.getBrickFace();

        brickFace = makeBrickFace(pos,size);

    }



    public void setFullStrength(int fullStrength) {
        brickModel.setStrength(fullStrength);
    }


    public void setStrength(int strength) {
        brickModel.setStrength(strength);
    }


    protected abstract Shape makeBrickFace(Point pos,Dimension size);

    public abstract Shape getBrick();
    /**
     * the strength of the brick decreases when hit on impact , broken when strength = 0
     */
    public void impact(){
        setStrength(getStrength()-1);
        setBroken(getStrength()== 0);// broken = true (when broken)
    }


    /**
     * reset the strength of bricks
     */
    public void repair() {

         setBroken(false);
        setStrength(getFullStrength());
    }


    public void setBroken(boolean broken)
    {
        brickModel.setBroken(broken);
    }

    public int getFullStrength()
    {
        return brickModel.getFullStrength();
    }

    public  boolean setImpact(Point2D point , int dir){
        if(isBroken())    // true
            return false;
        impact();
        return isBroken();
    }


    /**
     * @param b the ball
     * @return the side of impact on the brick
     */
    public final int findImpact(BallController b){
        if(isBroken())
            return 0;
        int out  = 0;
        if(brickFace.contains(b.getRight()))
            out = BrickModel.LEFT_IMPACT;
        else if(brickFace.contains(b.getLeft()))
            out = BrickModel.RIGHT_IMPACT;
        else if(brickFace.contains(b.getUp()))
            out = BrickModel.DOWN_IMPACT;
        else if(brickFace.contains(b.getDown()))
            out = BrickModel.UP_IMPACT ;
        return out;
    }

    public final boolean isBroken()
    {
        return brickModel.isBroken();
    }


    public int getStrength(){
        return brickModel.getStrength();
    }
    public Color getBorderColor(){
        return brickModel.getBorderColor();
    }

    public Color getInnerColor(){
        return brickModel.getInnerColor();
    }




}
