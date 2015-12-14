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
        this.Width=960;
        this.Height=1600;
        back= ImageLoader.getImage("back");
        paint=new Paint();

        Objs=new ArrayList<>();
        Wall w;
        for (int i=0;i<Width/80;i++){
            w=new Wall(80/2+80*i,Height-40,this);
            Objs.add(w);
        }
        Hunter h=new Hunter(Width/2,Height/4*3,this);
        Objs.add(h);
        Crate c=new Crate(Width,Height,this);
        Objs.add(c);
        player= new Player(Width/2,Height-120,this);
        Objs.add(player);
        handler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                move();          }

        };
    }
    private Bitmap img;
    public Bitmap getImage(){
        if (img==null) img=Bitmap.createBitmap(Width,Height, Bitmap.Config.ARGB_8888);
        return img;
    }
    public void genImage(){
        Bitmap result=Bitmap.createBitmap(Width,Height, Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(result);
        //canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(back, new Rect(0, 0, back.getWidth(), back.getHeight()), new Rect(0, 0, canvas.getWidth(), canvas.getHeight()), paint);
        for (GameObject o:Objs){
            Bitmap pic=o.getImage();
            int x=o.getX()-pic.getWidth()/2;
            int y=o.getY()-pic.getHeight()/2;
            canvas.drawBitmap(pic, x, y, paint);
        }
        img=result;
    }
    private ArrayList<GameObject> objToRemove;
    public void removeObject(GameObject obj){
        if (objToRemove==null) objToRemove=new ArrayList<>();
        objToRemove.add(obj);
    }
    public void move(){
        if (objToRemove!=null) {
            Objs.removeAll(objToRemove);
            objToRemove.clear();
        }
        if (objToAdd!=null) {
            Objs.addAll(objToAdd);
            objToAdd.clear();
        }
        for (GameObject o:Objs){
            o.move();
        }
        genImage();
    }
    private ArrayList<GameObject> objToAdd;
    public void addObject(GameObject o){
        if(objToAdd==null) objToAdd=new ArrayList<>();
        objToAdd.add(o);
    }
}
