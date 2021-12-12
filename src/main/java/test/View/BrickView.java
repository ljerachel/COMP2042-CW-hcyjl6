package test.View;


import test.Controller.BrickController;

import java.awt.*;

public class BrickView {

    /**
     * draws the brick with graphics2D
     */
    public void drawBrick(BrickController brick, Graphics2D g2d) {
        Color tmp = g2d.getColor();


        g2d.setColor(brick.getInnerColor());
        g2d.fill(brick.getBrick());

        g2d.setColor(brick.getBorderColor());
        g2d.draw(brick.getBrick());


        g2d.setColor(tmp);
    }
}
