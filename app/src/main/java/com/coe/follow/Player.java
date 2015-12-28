package com.coe.follow;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;

import com.coe.follow.Effects.Explosion;
import com.coe.follow.GameBase.GameHero;
import com.coe.follow.GameBase.GameObject;
import com.coe.follow.Items.Bag;
import com.coe.follow.Items.Crate;
import com.coe.follow.Structures.Cannon;
import com.coe.follow.Structures.Mine;
import com.coe.follow.Structures.Wall;
import com.coe.follow.utils.ImageLoader;

/**
 * Player of the game
 */
public class Player extends GameHero {
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

    private int stone=10;
    private int crystal=10;

    private int stepNum=1;
    private Paint paint;
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
    boolean novaReady=true;
    public boolean setNova(){
        if (novaReady) {
            world.addObject(new Explosion((int) x, (int) y, world,this));
            novaReady=false;
            return true;
        } else return false;
    }

    public boolean setWall(){
        if (stone>1) {
            world.addObject(new Wall((int) x, (int) y, world));
            stone--;
            return true;
        } else return false;
    }
    public boolean setMine(){
        if (crystal>1) {
            world.addObject(new Mine((int) x, (int) y, world));
            crystal--;
            return true;
        } else return false;
    }
    public boolean setCannon(){
        if (stone>4 && crystal>4) {
            world.addObject(new Cannon((int) x, (int) y, world));
            crystal-=4;
            stone-=4;
            return true;
        } else return false;

    }
    public Player(int x,int y,World world){
        super(x,y,world);
        image = ImageLoader.getImage("hero1");
    }
    public boolean move(){


        if (x!=targetx || y!=targety)
        {
            stepNum++;

            if (stepNum==4) {
                image=ImageLoader.getImage("hero1");
            }
            else if (stepNum==1){
                image=ImageLoader.getImage("hero2");
            } else if (stepNum>6) {
                stepNum=0;
                novaReady=true;
            }
            if (targety!=y) angle=Math.atan((targetx-x)/(targety-y))/Math.PI*180;
            int speed=10;

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
            }

            if (Math.abs(mx)>Math.abs(dx)) mx=dx;
            if (Math.abs(my)>Math.abs(dy)) my=dy;

            for (GameObject o:world.Objs){
                double distance=Math.sqrt(Math.pow(x-o.getX(),2)+Math.pow(y-o.getY(),2));
                if (o instanceof Crate){
                    world.removeObject(o);
                    this.stone++;
                } else if (o instanceof Bag){
                    world.removeObject(o);
                    this.crystal++;
                }
            }
            x+=mx;
            y+=my;
            return true;
        } else return false;
    }


    public int getStone() {
        return stone;
    }

    public int getCrystal() {
        return crystal;
    }
}
