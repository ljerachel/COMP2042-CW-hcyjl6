package test.View;

import test.Controller.PlayerController;


import java.awt.*;

public class PlayerView {

    private void drawPlayer(PlayerController p, Graphics2D g2d) {
        Color tmp = g2d.getColor();

        Shape s = p.getPlayerFace();
        g2d.setColor(p.getInnerColor());
        g2d.fill(s);

        g2d.setColor(p.getBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);
    }



}
