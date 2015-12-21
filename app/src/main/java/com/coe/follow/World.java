package com.coe.follow;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.effect.Effect;
import android.os.Handler;
import android.util.Log;

import com.coe.follow.GameBase.GameEffect;
import com.coe.follow.GameBase.GameItem;
import com.coe.follow.GameBase.GameMob;
import com.coe.follow.GameBase.GameObject;
import com.coe.follow.GameBase.GameStructure;
import com.coe.follow.Items.Crate;
import com.coe.follow.Monsters.Hunter;
import com.coe.follow.Structures.Home;
import com.coe.follow.Structures.HunterPortal;
import com.coe.follow.Structures.StoneMine;
import com.coe.follow.Structures.Wall;
import com.coe.follow.utils.ImageLoader;
import com.coe.follow.utils.MyMath;

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
    private int visWidth=960;
    private int visHeight=1400;
    public int getVisWidth(){
        return visWidth;
    }
    public int getVisHeight(){
        return visHeight;
    }
    public World(){
        this.Width=10000;
        this.Height=10000;
        back= ImageLoader.getImage("back");
        paint=new Paint();

        Objs=new ArrayList<>();
        //Стартовая локация
            //База
        Objs.add(new Home(Width / 2, Height / 2, this));
            //Игрок
        player= new Player(Width/2,Height/2,this);
        Objs.add(player);
        //Шахта
        Objs.add(new StoneMine(Width/4,Height/4*3,this));
        //Портал охотников
        Objs.add(new HunterPortal(Width / 4 * 3, Height / 4, this));
        genImage();
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
        Bitmap result=Bitmap.createBitmap(visWidth,visHeight+200, Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(result);
        Bitmap Structure=Bitmap.createBitmap(visWidth,visHeight+200, Bitmap.Config.ARGB_8888);
        Canvas cStr=new Canvas(Structure);
        Bitmap bMob=Bitmap.createBitmap(visWidth,visHeight+200, Bitmap.Config.ARGB_8888);
        Canvas cMob=new Canvas(bMob);
        Bitmap bEffect=Bitmap.createBitmap(visWidth,visHeight+200, Bitmap.Config.ARGB_8888);
        Canvas cEffect=new Canvas(bEffect);
        int smX=player.getX()%80;
        int smY=player.getY()%80;
        for (int i=0;i<visWidth/80+1;i++)
            for (int j=0;j<visHeight/80+1;j++)
            {
                canvas.drawBitmap(back,i*80-smX,j*80-smY,paint);
            }
        for (GameObject o:Objs){
            if (Math.abs(player.getX()-o.getX())<visWidth/2 && Math.abs(player.getY()-o.getY())<visHeight/2) {
                Bitmap pic = o.getImage();
                int x = o.getX() - pic.getWidth() / 2-player.getX()+visWidth/2;
                int y = o.getY() - pic.getHeight() / 2-player.getY()+visHeight/2;

                if (o instanceof GameEffect) {cEffect.drawBitmap(pic, x, y, paint);
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
        canvas.drawBitmap(Structure,0,0,paint);
        canvas.drawBitmap(bMob, 0, 0, paint);
        canvas.drawBitmap(bEffect, 0, 0, paint);
        paint.setTextSize(40);
        paint.setColor(Color.WHITE);
        canvas.drawText("Stone:" + player.getStone(), 10, 40, paint);
        canvas.drawText("Crystal:" + player.getCrystal(), 10, 80, paint);
        Bitmap btn=ImageLoader.getImage("novab");
        canvas.drawBitmap(btn, new Rect(0, 0, btn.getWidth(), btn.getHeight()), new Rect(0, visHeight, 200, visHeight + 200), paint);
        btn=ImageLoader.getImage("wallb");
        canvas.drawBitmap(btn, new Rect(0, 0, btn.getWidth(), btn.getHeight()), new Rect(200, visHeight, 400, visHeight + 200), paint);
        btn=ImageLoader.getImage("explosionb");
        canvas.drawBitmap(btn,new Rect(0,0,btn.getWidth(),btn.getHeight()),new Rect(400,visHeight,600,visHeight+200), paint);
        btn=ImageLoader.getImage("cannonb");
        canvas.drawBitmap(btn,new Rect(0,0,btn.getWidth(),btn.getHeight()),new Rect(600,visHeight,800,visHeight+200), paint);
        img=result;
    }
    public void Touch(int x,int y){
        Log.d("touchTest","!"+x+" "+y+" "+visHeight);
        if (y > visHeight) {
            if (x < 200) {
                player.setNova();

            } else if (x < 400) {
                player.setWall();

            }else if (x < 600) {
                player.setMine();

            }else if (x < 800) {
                player.setCannon();

            }
        } else player.setTarget(x+player.getX()-visWidth/2,y+player.getY()-visHeight/2);

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
