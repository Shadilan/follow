package com.coe.follow;

import android.graphics.Bitmap;

/**
 * Created by Shadilan on 12.12.2015.
 */
public interface GameObject {
    public int getX();
    public int getY();
    public boolean move();
    public Bitmap getImage();
    public String getType();


}
