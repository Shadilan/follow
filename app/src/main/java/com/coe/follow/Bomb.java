package com.coe.follow;

import android.graphics.Bitmap;

import com.coe.follow.GameBase.GameObject;

/**
 * Created by Shadilan on 14.12.2015.
 */
public class Bomb implements GameObject {
    private double x;
    private double y;
    private double power;
    private World world;
    @Override
    public int getX() {
        return (int) x;
    }

    @Override
    public int getY() {
        return (int) y;
    }

    @Override
    public boolean move() {
        return false;
    }

    @Override
    public Bitmap getImage() {
        return null;
    }

    @Override
    public String getType() {
        return null;
    }
}
