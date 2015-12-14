package com.coe.follow;

import android.graphics.Bitmap;

/**
 * Created by Shadilan on 12.12.2015.
 */
public class Crate implements GameObject {
    private double x;
    public int getX(){return (int)x;}
    private double y;
    public int getY(){return (int)y;}
    World world;
    @Override
    public boolean move() {
        return true;
    }

    @Override
    public Bitmap getImage() {
        return ImageLoader.getImage("crate1");
    }

    @Override
    public String getType() {
        return "Crate";
    }

    public Crate(double Width,double Height,World world){
        x=Math.round(Math.random()*(Width-160)+80);
        y=Math.round(Math.random()*(Height-240)+80);
        this.world=world;
    }


}
