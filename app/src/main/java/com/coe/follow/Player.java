package com.coe.follow;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.MediaActionSound;
import android.util.Log;

/**
 * Created by Shadilan on 10.12.2015.
 */
public class Player implements GameObject {
    /*
    Объект управляется игроком. Есть текущие координаты (Как у любого объекта) есть целевая точка.
    В случае гибели объект перемещается на базу.
    Объект может получать разнообразные эффекты:
    Лечение (получается на базе действует 10 тиков) восстанавливает 5 хп
    Ускорение Увеличивает базовую скорость на 1 100 тиков
    Замедление Уменьшает базовую скорость на 1 100 тиков
    Переносит тяжесть уменьшает базовую скорость на 1 при достижении базы увеличивает запас груза на 1
    Ловушка останавливает игрока на 10 тиков.

    Функции
      Применить эффекты
      Переместиться
    Параметры
      Позиция (х у)
      Цель (х у)
      Эффекты

     */
    private double x;
    public int getX(){return (int)x;}
    private double y;
    public int getY(){return (int)y;}
    private boolean isMoving=false;
    private int stepNum=1;
    private Paint paint;
    private Bitmap image;
    double angle=0;
    @Override
    public Bitmap getImage() {

        if (paint==null) paint=new Paint();

        Bitmap result=Bitmap.createBitmap(80,80, Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(result);


        Matrix matrix=new Matrix();
        //matrix.postTranslate(image.getWidth()/2, image.getHeight()/2);
        matrix.preRotate((float) (angle),image.getWidth()/2, image.getHeight()/2);
        //matrix.postTranslate(canvas.getWidth() / 2, canvas.getHeight() / 2);

        Log.d("PlayerTest", "Angle:" + angle);

        canvas.drawBitmap(image, matrix, paint);

        return result;
    }

    @Override
    public String getType() {
        return "Player";
    }

    private double targetx;
    private double targety;
    private double startX;
    private double startY;
    private World world;
    private boolean carryCrate=false;
    public boolean isCarryCrate() {return  carryCrate;}


    public Player(double x,double y,World world){
        this.x=x;
        this.y=y;
        targetx=x;
        targety=y;
        startX=x;
        startY=y;
        this.world=world;
        image = ImageLoader.getImage("hero1");
    }
    public boolean move(){


        if (x!=targetx || y!=targety)
        {
            stepNum++;

            if (stepNum<10) {
                if (this.isCarryCrate()) image = ImageLoader.getImage("herocargo1");
                    else image = ImageLoader.getImage("hero1");
            }
            else {
                if (this.isCarryCrate()) image = ImageLoader.getImage("herocargo2");
                else image = ImageLoader.getImage("hero2");
            }
            if (stepNum>19) stepNum=1;
            if (targety!=y) angle=Math.atan((targetx-x)/(targety-y))/Math.PI*180;
            int speed=3;
            if (carryCrate) speed=speed-1;

            double dx=targetx-x;
            double dy=targety-y;
            double g=Math.sqrt(Math.pow(dx,2)+Math.pow(dy,2));
            double k=g/speed;
            double mx=dx/k;
            double my=dy/k;
            double angle1=Math.asin(dx/g)/Math.PI*180;
            double angle2=Math.acos(dy/g)/Math.PI*180;
            if (angle1==angle2){
                angle=-1*angle1;
            } else
            if (angle1==-1*angle2){
                angle=-1*angle1;
            } else
            if (angle1<0){
                angle=angle2;
            } else
            if (angle1>0){
                angle=-1*angle2;
            };

            if (Math.abs(mx)>Math.abs(dx)) mx=dx;
            if (Math.abs(my)>Math.abs(dy)) my=dy;
            x+=mx;
            y+=my;
            for (GameObject o:world.Objs){
                double distance=Math.sqrt(Math.pow(x-o.getX(),2)+Math.pow(y-o.getY(),2));
                if (o.getType().equalsIgnoreCase("Crate") && distance<20 && !carryCrate){
                    world.removeObject(o);
                    carryCrate=true;
                }
                if (o.getType().equalsIgnoreCase("Field") && distance<10 && carryCrate){
                    carryCrate=false;
                    ((Field)o).addCrate();
                    world.addObject(new Crate(world.getWidth(),world.getHeight(),world));
                }
            }
            return true;
        } else return false;
    }
    public void removeCargo(){
        carryCrate=false;
    }
    public void setTarget(double x,double y){
        targetx=x;
        targety=y;
    }
}
