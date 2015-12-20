package com.coe.follow.utils;

/**
 * Created by Shadilan on 20.12.2015.
 */
public class MyMath {
    public static double getDistance(double x,double y,double x1,double y1){
        return Math.sqrt(Math.pow(x1-x,2)+Math.pow(y1-y,2));
    }
}
