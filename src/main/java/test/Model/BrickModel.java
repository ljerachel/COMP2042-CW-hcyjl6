package test.Model;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

public class BrickModel {
    public static final int MIN_CRACK = 1;
    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;


    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;

    private static Random rnd;


    private String name;
    Shape brickFace;

    private Color border;
    private Color inner;



    private int fullStrength;

    private int strength;

    private boolean broken;

    /**
     * @param name type of brick
     * @param pos position of brick
     * @param size size of brick
     * @param border border colour of brick
     * @param inner the inner brick colour
     * @param strength how hard it is to break the brick
     */
    public BrickModel(String name, Point pos,Dimension size,Color border,Color inner,int strength){
        rnd = new Random();
        broken= false;   //the brick broken or not
        this.name = name;
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;

    }


    /**
     * @return true if broken false if not broken
     */
    public final boolean isBroken()
    {
        return broken;
    }

    public Shape getBrickFace() {
        return brickFace;
    }

    public static Random getRnd() {
        return rnd;
    }


    public void setBroken(boolean broken) {
        this.broken = broken;
    }


    public Color getBorderColor(){
        return border;
    }

    public Color getInnerColor(){
        return inner;
    }

    public int getFullStrength() {
        return fullStrength;
    }


    public int getStrength() {
        return strength;
    }

    public void setFullStrength(int fullStrength)
    {
        this.fullStrength = fullStrength;
    }

    public void setStrength(int strength)
    {
        this.strength = strength;
    }

}
