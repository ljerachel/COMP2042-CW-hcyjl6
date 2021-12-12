/*
package test.View;

import test.Model.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DebugPanelView extends JPanel {
    private static final Color DEF_BKG = Color.WHITE;


    private JButton skipLevel;
    private JButton resetBalls;

    private JSlider ballXSpeed;
    private JSlider ballYSpeed;

    private Wall wall;

    public DebugPanel(Wall wall){

        this.wall = wall;

        initialize();

        skipLevel = makeButton("Skip Level",e -> wall.nextLevel());
        resetBalls = makeButton("Reset Balls",e -> wall.resetBallCount());

        ballXSpeed = makeSlider(-4,4,e -> wall.setBallXSpeed(ballXSpeed.getValue()));
        ballYSpeed = makeSlider(-4,4,e -> wall.setBallYSpeed(ballYSpeed.getValue()));

        this.add(skipLevel);
        this.add(resetBalls);

        this.add(ballXSpeed);
        this.add(ballYSpeed);

    }

    private void initialize(){
        this.setBackground(DEF_BKG);
        this.setLayout(new GridLayout(2,2));
    }

    private JButton makeButton(String title, ActionListener e){
        JButton out = new JButton(title);
        out.addActionListener(e);
        return out;
    }

    public JButton getSkipLevel() {
        return skipLevel;
    }

    public JButton getResetBalls() {
        return resetBalls;
    }

    public JSlider getBallXSpeed() {
        return ballXSpeed;
    }

    public JSlider getBallYSpeed() {
        return ballYSpeed;
    }
}
*/
