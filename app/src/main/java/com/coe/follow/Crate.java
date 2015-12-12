package com.coe.follow;

/**
 * Created by Shadilan on 12.12.2015.
 */
public class Crate {
    private double x;
    public double getX(){return x;}
    private double y;
    public double getY(){return y;}

    public Crate(double Width,double Height){
        x=Math.round(Math.random()*Width);
        y=Math.round(Math.random()*Height);
    }


}
