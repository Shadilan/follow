package com.coe.follow.Monsters;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.coe.follow.Effects.Bite;
import com.coe.follow.GameBase.GameEffect;
import com.coe.follow.GameBase.GameMob;
import com.coe.follow.GameBase.GameObject;
import com.coe.follow.GameBase.GameStructure;
import com.coe.follow.Items.Bag;
import com.coe.follow.Structures.MonsterPortal;
import com.coe.follow.Structures.StoneMine;
import com.coe.follow.World;
import com.coe.follow.utils.ImageLoader;

/**
 * Created by Shadilan on 10.12.2015.
 */
public class Hunter extends GameMob {
    /*
    Появляется в рандомной точке по х и 0 по у
    Определяет цель рандомную точку по х и высота по у
    Движется с равномерной скоростью по прямой к точке
    При встрече с объектом Wall взрывается нанося 1 повреждение стене после этого создается новый монстр.
     */
    private double targetx;
    private double targety;
    private double angle=0;

    private double mx;
    private double my;
    private int stepNum=1;
    private Paint paint;
    @Override
    public Bitmap getImage() {
        if (paint==null) paint=new Paint();
        Bitmap result=Bitmap.createBitmap(80,80, Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(result);
        Matrix matrix=new Matrix();
        matrix.preRotate((float) (angle), image.getWidth() / 2, image.getHeight() / 2);
        canvas.drawBitmap(image, matrix, paint);
       return result;
    }
    public Hunter(int x, int y, World world) {
        super(x, y, world);
        start();
    }
    private void start(){
        image= ImageLoader.getImage("hunter1");
        this.targetx=(world.getWidth()*Math.random());
        this.targety=(world.getHeight()*Math.random());;
        double distance=Math.sqrt(Math.pow(targetx-x,2)+Math.pow(targety-y,2));
        angle=Math.acos((targetx-x)/distance);
        mx=(targetx-x)/(distance/10);
        my=(targety-y)/(distance/10);
    }
    @Override
    public boolean move(){
            stepNum++;

            if (stepNum<10) {
                image = ImageLoader.getImage("hunter1");
            }
            else {
                image=ImageLoader.getImage("hunter2");
            }
            if (stepNum>19) stepNum=1;

        if (Math.abs(targetx-x)<mx) mx=targetx-x;
        if (Math.abs(targety-y)<my) my=targety-y;
        x+=mx;
        y+=my;
            for (GameObject o:world.Objs) {

                double disobj = Math.sqrt(Math.pow(x - o.getX(), 2) + Math.pow(y - o.getY(), 2));
                //Выбрать цель
                if (o instanceof GameStructure && !(o instanceof StoneMine) && disobj<80 && !(o instanceof MonsterPortal)) {
                    x-=mx;
                    y-=my;
                    world.addObject(new Bite(o.getX(),o.getY(),world,this));
                    break;

                }
                if (o instanceof GameStructure && !(o instanceof StoneMine) && disobj<500 && !(o instanceof MonsterPortal)) {
                    targetx=o.getX();
                    targety=o.getY();
                    double distance=Math.sqrt(Math.pow(targetx-x,2)+Math.pow(targety-y,2));
                    angle=Math.acos((targetx-x)/distance);
                    mx=(targetx-x)/(distance/10);
                    my=(targety-y)/(distance/10);
                    //Создать эффект
                    break;
                }
            }
            return true;

    }
    public boolean doDamage(GameEffect effect){
        hp-=effect.getPower();
        if (hp<0){
            if (Math.random()*100>50) world.addObject(new Bag((int)x,(int)y,world));
            world.removeObject(this);
            return true;
        }
        return false;
    }
}

