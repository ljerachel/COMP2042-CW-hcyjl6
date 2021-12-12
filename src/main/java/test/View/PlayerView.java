package test.View;

import test.Controller.PlayerController;


import java.awt.*;

public class PlayerView {

    /**
     * @param p player controller which controls player model and view
     * draws player graphics
     */
    public void drawPlayer(PlayerController p, Graphics2D g2d) {
        Color tmp = g2d.getColor();

        Shape s = p.getPlayerFace();
        g2d.setColor(p.getInnerColor());
        g2d.fill(s);

        g2d.setColor(p.getBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);
    }



}
