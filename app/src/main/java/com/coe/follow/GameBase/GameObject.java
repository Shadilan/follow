package com.coe.follow.GameBase;

import android.graphics.Bitmap;

import com.coe.follow.World;

/**
 * Базовый объект
 */
public class GameObject {
    protected double x;
    protected double y;
    protected Bitmap image;
    protected World world;
    protected boolean Active=true;
    public boolean getActive(){return Active;}
    public GameObject(int x,int y,World world){
        this.x=x;
        this.y=y;
        this.world=world;
    }
    public int getX() {return (int)x;}
    public int getY(){return (int)y;}
    public boolean move(){
        return true;
    };
    public Bitmap getImage(){
        return image;
    }
}
