package com.coe.follow;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.Image;
import android.os.Handler;
import android.view.Window;

import java.util.ArrayList;

/**
 * Created by Shadilan on 10.12.2015.
 */
public class World {
    public Handler handler;
    public Player player;
    public ArrayList<GameObject> Objs;
    private Bitmap back;
    private int Width;
    private int Height;
    private Paint paint;
    public int getWidth(){
        return Width;
    }
    public int getHeight(){
        return Height;
    }
    public World(){
        this.Width=240;
        this.Height=400;
        back= ImageLoader.getImage("back");
        paint=new Paint();
        player= new Player(Width/2,Height/13,this);
        Objs=new ArrayList<>();
        Objs.add(player);
        Hunter h=new Hunter(Width/2,Height/4*3,this);
        Objs.add(h);
        handler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                for (GameObject o:Objs){
                    o.move();
                }
            }

        };
    }
    public Bitmap getImage(){
        Bitmap result=Bitmap.createBitmap(Width,Height, Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(result);
        canvas.drawColor(Color.BLACK);
        //canvas.drawBitmap(back,new Rect(0,0,back.getWidth(),back.getHeight()),new Rect(0,0,canvas.getWidth(),canvas.getHeight()),paint);
        for (GameObject o:Objs){
            Bitmap pic=o.getImage();
            int x=o.getX()-pic.getWidth()/2;
            int y=o.getY()-pic.getHeight()/2;
            canvas.drawBitmap(pic,x,y,paint);
        }
        return result;
    }
}
