package com.coe.follow.GameBase;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;

import com.coe.follow.Player;
import com.coe.follow.utils.ImageLoader;

import java.util.ArrayList;

/**
 * GameWorld
 */
public class GameWorld {
    protected int visWidth;
    protected int visHeight;
    protected int Width;
    protected int Height;
    protected Bitmap img;

    public Handler handler;

    /*
    Public for needs of  object interaction
     */
    public ArrayList<GameObject> Objs;
    protected GameHero player;
    protected int playerPosX;
    protected int playerPosY;
    protected Paint paint;
    protected boolean runFlag=true;
    protected Runnable tick=new Runnable() {
        @Override
        public void run() {
            move();
            if (runFlag){
                handler.postDelayed(tick,30);
            }
        }
    };
    public GameWorld(int Width,int Height, int visWidth, int visHeight) {
        this.Width = Width;
        this.Height = Height;
        this.visHeight = visHeight;
        this.visWidth = visWidth;
        this.playerPosX=visWidth/2;
        this.playerPosY=visHeight/2;
        Objs=new ArrayList<>();
        paint=new Paint();
        handler = new Handler();
        handler.postDelayed(tick,30);
    }
    public void pause(){
        if (runFlag){runFlag=false;} else {runFlag=true;handler.postDelayed(tick,30);}
    }
    public Bitmap getImage(){
        if (img==null) img=Bitmap.createBitmap(Width,Height, Bitmap.Config.ARGB_8888);
        return img;
    }
    public void genImage(){
        Bitmap result=Bitmap.createBitmap(visWidth,visHeight+200, Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(result);
        Bitmap Structure=Bitmap.createBitmap(visWidth,visHeight+200, Bitmap.Config.ARGB_8888);
        Canvas cStr=new Canvas(Structure);
        Bitmap bMob=Bitmap.createBitmap(visWidth,visHeight+200, Bitmap.Config.ARGB_8888);
        Canvas cMob=new Canvas(bMob);
        Bitmap bEffect=Bitmap.createBitmap(visWidth,visHeight+200, Bitmap.Config.ARGB_8888);
        Canvas cEffect=new Canvas(bEffect);
        for (GameObject o:Objs){
            if (Math.abs(player.getX()-o.getX())<visWidth && Math.abs(player.getY()-o.getY())<visHeight) {
                Bitmap pic = o.getImage();
                int x = o.getX() - pic.getWidth() / 2-player.getX()+playerPosX;
                int y = o.getY() - pic.getHeight() / 2-player.getY()+playerPosY;

                if (o instanceof GameEffect) {cEffect.drawBitmap(pic, x, y, paint);
                } else if (o instanceof GameCell) {
                    canvas.drawBitmap(pic, x, y, paint);
                } else if (o instanceof GameMob || o instanceof Player) {
                    cMob.drawBitmap(pic, x, y, paint);
                } else if (o instanceof GameStructure) {
                    cStr.drawBitmap(pic, x, y, paint);
                } else if (o instanceof GameItem) {
                    cStr.drawBitmap(pic, x, y, paint);
                } else {
                    canvas.drawBitmap(pic, x, y, paint);
                }
            }

        }
        canvas.drawBitmap(Structure, 0, 0, paint);
        canvas.drawBitmap(bMob, 0, 0, paint);
        canvas.drawBitmap(bEffect, 0, 0, paint);
        img=result;

    }
    protected ArrayList<GameObject> objToRemove;
    public void removeObject(GameObject obj){
        if (objToRemove==null) objToRemove=new ArrayList<>();
        objToRemove.add(obj);
    }

    private ArrayList<GameObject> objToAdd;
    public void addObject(GameObject o){
        if(objToAdd==null) objToAdd=new ArrayList<>();
        objToAdd.add(o);
    }
    protected boolean needRedraw;
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
        if (needRedraw) {
            needRedraw=false;
            genImage();
        }
    }
    public int getWidth(){
        return Width;
    }
    public int getHeight(){
        return Height;
    }
    public int getVisWidth(){
        return visWidth;
    }
    public int getVisHeight(){
        return visHeight;
    }
}
