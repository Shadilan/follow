package com.coe.follow;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.util.Log;

/**
 * Created by Shadilan on 13.12.2015.
 */
public class Field implements GameObject{
    private int stepNum=1;
    private Paint paint;
    private Bitmap image;
    private World world;
    private double x;
    public int getX(){
        return (int)x;
    }
    private double y;
    public int getY(){
        return (int)y;}

    @Override
    public boolean move() {
        stepNum++;

        if (stepNum==1) {
            image = ImageLoader.getImage("field1");
        }
        else if (stepNum==9) {
            image=ImageLoader.getImage("field2");
        }
        if (stepNum>9) stepNum=0;
        return true;
    }

    @Override
    public Bitmap getImage() {
        return image;
    }

    @Override
    public String getType() {
        return "Field";
    }

    public Field(int x,int y,World world){
        this.x=x;
        this.y=y;
        this.world=world;
        image=ImageLoader.getImage("field1");
    }
}
