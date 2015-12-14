package com.coe.follow;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * Created by Shadilan on 13.12.2015.
 */
public class Wall implements GameObject{


    private double x;
    private double y;
    private int HP;
    private World world;
    public int getHP(){
        return HP;
    }

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
    Bitmap image;
    @Override
    public Bitmap getImage() {
        return image;
    }

    @Override
    public String getType() {
        return "Wall";
    }
    public void doDamage(){
        HP--;
        setImage();
    }
    public Wall(int x,int y,World world){
        this.x=x;
        this.y=y;
        this.world=world;
        HP=10;
        setImage();
        Log.d("Wall width","Width"+image.getWidth());
    }
    private void setImage(){
        if (HP>15) image= ImageLoader.getImage("wall1");
        else if (HP>10) image= ImageLoader.getImage("wall2");
        else if (HP>5) image= ImageLoader.getImage("wall3");
        else image= ImageLoader.getImage("wall4");
    }
    public void doRepair(int Rate){
        HP+=Rate;
        setImage();
    }
}
