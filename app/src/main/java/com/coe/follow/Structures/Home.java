package com.coe.follow.Structures;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

import com.coe.follow.GameBase.GameEffect;
import com.coe.follow.GameBase.GameStructure;
import com.coe.follow.World;
import com.coe.follow.utils.ImageLoader;

/**
 * Created by Shadilan on 20.12.2015.
 */
public class Home extends GameStructure {
    Paint paint;
    public Home(int x, int y, World world) {
        super(x, y, world);
        hp=1000;
        paint=new Paint();
        genImage();
    }

    private int step=0;
    public boolean move(){
        step++;
        if (step==40){
            if (hp<1000) hp++;
            step=0;
        }
        if (step%10==0) genImage();

        return false;
    }
    public void genImage(){
        image=Bitmap.createBitmap(160,160, Bitmap.Config.ARGB_8888);
        Canvas c=new Canvas(image);
        if (step<10){
            c.drawBitmap(ImageLoader.getImage("base1"),0,0,paint);
        } else if (step<20){
            c.drawBitmap(ImageLoader.getImage("base2"),0,0,paint);

        } else if (step<30){
            c.drawBitmap(ImageLoader.getImage("base3"),0,0,paint);
        }else {
            c.drawBitmap(ImageLoader.getImage("base4"),0,0,paint);
        }
        paint.setColor(Color.RED);
        double size=160/1000*hp;
        //TODO: Разобраться с заливкой полоски хп.
        c.drawRect(0,0, (float) size,20,paint);

    }
    public boolean doDamage(GameEffect effect){
        hp-=effect.getPower();
        if (hp<0){
            world.removeObject(this);
            Active=false;
            return true;
        }
        genImage();
        return false;
    }
}
