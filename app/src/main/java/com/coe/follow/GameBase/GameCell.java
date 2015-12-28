package com.coe.follow.GameBase;

import android.graphics.Bitmap;

import com.coe.follow.World;

/**
 * Background of gamefield
 */
public class GameCell extends GameObject{
    protected Bitmap image;
    protected int Width=1;
    protected int Height=1;
    protected int x;
    protected int y;

    public GameCell(int x, int y, World world) {
        super(x, y, world);
    }

    public Bitmap getImage(){
        if (image==null) {image=Bitmap.createBitmap(Width,Height, Bitmap.Config.RGB_565);}
        return image;
    }
    public int getWidth(){
        return Width;
    }
    public int getHeight(){
        return Height;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }


}
