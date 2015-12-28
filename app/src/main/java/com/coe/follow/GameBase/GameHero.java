package com.coe.follow.GameBase;

import com.coe.follow.World;

/**
 * Main Person of game
 */
public class GameHero extends GameObject {
    //Целевая точка
    protected double targetx;
    protected double targety;
    protected double angle=0;

    public GameHero(int x, int y, World world) {
        super(x, y, world);
        targetx=x;
        targety=y;
    }
    public void setTarget(double x,double y){
        targetx=x;
        targety=y;
    }
}
