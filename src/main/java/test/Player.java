/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *//*

package test;

import java.awt.*;


public class Player {


    public static final Color BORDER_COLOR = Color.GREEN.darker().darker();
    public static final Color INNER_COLOR = Color.GREEN;

    private static final int DEF_MOVE_AMOUNT = 5;

    private Rectangle playerFace;



    private Point ballPoint;
    private int moveAmount;
    private int min;
    private int max;
    private static Player playerInstance  ;

    */
/**
     * @param ballPoint starting position of the ball
     * @param width width of the player's rectangle
     * @param height height of the player's rectangle
     * @param container the area that can allow movement of the player's rectangle
     *//*

    private Player(Point ballPoint,int width,int height,Rectangle container) {
        this.ballPoint = ballPoint;
        moveAmount = 0;
        playerFace = makeRectangle(width, height);
        min = container.x + (width / 2);
        max = min + container.width - width;

    }

    public static Player getInstance() {
        if (playerInstance == null) {
            getInstance();
        }
        return playerInstance;

    }
    public static Player getInstance(Point ballpos,int width, int height, Rectangle container)
    {
        if (playerInstance == null)
        {
            playerInstance = new Player(ballpos,width,height,container);
        }
        return playerInstance;
    }


    */
/**
     * @param width width of the player's rectangle
     * @param height height of the player's rectangle
     * @return a rectangle with the width and height in the parameter
     *//*

    private Rectangle makeRectangle(int width,int height){
        Point p = new Point((int)(ballPoint.getX() - (width / 2)),(int)ballPoint.getY());
        return  new Rectangle(p,new Dimension(width,height));
    }

    */
/**
     * @param b ball
     * @return the position of the ball when it impacts with the player's rectangle
     *//*

    public boolean impact(Ball b){
        return playerFace.contains(b.getPosition()) && playerFace.contains(b.down) ;
    }

    */
/**
     * controls the movement of the player's rectangle
     *//*

    public void move(){
        double x = ballPoint.getX() + moveAmount;
        if(x < min || x > max)
            return;
        ballPoint.setLocation(x,ballPoint.getY());
        playerFace.setLocation(ballPoint.x - (int)playerFace.getWidth()/2,ballPoint.y);
    }

    public int getMoveAmount() {
        return moveAmount;
    }

    */
/**
     * the player rectangle moves left by 5 units
     *//*

    public void moveLeft(){
        moveAmount = -DEF_MOVE_AMOUNT;
    }

    public void movRight(){
        moveAmount = DEF_MOVE_AMOUNT;
    }

    public void stop(){
        moveAmount = 0;
    }


    public Shape getPlayerFace(){
        return  playerFace;
    }

    */
/**
     * @param p the middle position (starting position)
     * resets the position of the player's rectangle during start of each round / lose the ball
     *//*

    public void moveTo(Point p){
        ballPoint.setLocation(p);
        playerFace.setLocation(ballPoint.x - (int)playerFace.getWidth()/2,ballPoint.y);
    }
}
*/
