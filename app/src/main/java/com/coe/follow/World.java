package com.coe.follow;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;

import com.coe.follow.GameBase.GameObject;
import com.coe.follow.Items.Crate;
import com.coe.follow.Monsters.Hunter;
import com.coe.follow.Structures.Home;
import com.coe.follow.Structures.HunterPortal;
import com.coe.follow.Structures.StoneMine;
import com.coe.follow.Structures.Wall;
import com.coe.follow.utils.ImageLoader;

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
    private int visWidth=480;
    private int visHeight=700;
    public World(){
        this.Width=10000;
        this.Height=10000;
        back= ImageLoader.getImage("back");
        paint=new Paint();

        Objs=new ArrayList<>();
        //Стартовая локация
            //База
        Objs.add(new Home(Width/2,Height/2,this));
            //Игрок
        player= new Player(Width/2,Height-120,this);
        Objs.add(player);
        //Шахта
        Objs.add(new StoneMine(Width/4,Height/4*3,this));
        //Портал охотников
        Objs.add(new HunterPortal(Width/4*3,Height/4,this));

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
        Bitmap result=Bitmap.createBitmap(Width,Height+200, Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(result);
        //canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(back, new Rect(0, 0, back.getWidth(), back.getHeight()), new Rect(0, 0, canvas.getWidth(), canvas.getHeight()-200), paint);
        for (GameObject o:Objs){
            Bitmap pic=o.getImage();
            int x=o.getX()-pic.getWidth()/2;
            int y=o.getY()-pic.getHeight()/2;
            canvas.drawBitmap(pic, x, y, paint);
        }
        paint.setTextSize(40);
        paint.setColor(Color.WHITE);
        canvas.drawText("Score:" + player.getScore(), 10, 40, paint);
        Bitmap btn=ImageLoader.getImage("explosionb");
        canvas.drawBitmap(btn,new Rect(0,0,btn.getWidth(),btn.getHeight()),new Rect(0,Height,200,Height+200), paint);
        btn=ImageLoader.getImage("cannonb");
        canvas.drawBitmap(btn,new Rect(0,0,btn.getWidth(),btn.getHeight()),new Rect(200,Height,400,Height+200), paint);
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
