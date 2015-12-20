package com.coe.follow.Structures;

import android.graphics.Bitmap;
import android.util.Log;

import com.coe.follow.GameBase.GameEffect;
import com.coe.follow.GameBase.GameObject;
import com.coe.follow.GameBase.GameStructure;
import com.coe.follow.World;
import com.coe.follow.utils.ImageLoader;

/**
 * Created by Shadilan on 13.12.2015.
 */
public class Wall extends GameStructure{


    @Override
    public boolean move() {
        return false;
    }
    Bitmap image;
    @Override
    public Bitmap getImage() {
        return image;
    }

    public Wall(int x,int y,World world){
        super(x,y,world);
        hp=16;
        genImage();
    }
    private void genImage(){
        if (hp>15) image= ImageLoader.getImage("wall1");
        else if (hp>10) image= ImageLoader.getImage("wall2");
        else if (hp>5) image= ImageLoader.getImage("wall3");
        else image= ImageLoader.getImage("wall4");
    }
    public boolean doDamage(GameEffect effect){
        hp-=effect.getPower();
        if (hp<0){
            world.removeObject(this);
            return true;
        }
        genImage();
        return false;
    }
}
