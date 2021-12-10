package test.View;

import test.Ball;
import test.Controller.BallController;

import java.awt.*;

public class BallView {

    public void drawBall(BallController ball, Graphics2D g2d) {

     Shape s = ball.getBallFace4View();
     Color tmp = g2d.getColor();

     g2d.setColor(ball.getInnerColor());
     g2d.fill(s);

     g2d.setColor(ball.getBorderColor());
     g2d.draw(s);

     g2d.setColor(tmp);

        //ball.changeView(g2d);
    }
}