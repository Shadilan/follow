package com.coe.follow;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;

/**
 * Created by Shadilan on 10.12.2015.
 */
public class Hunter implements GameObject{
    private double x;
    private double y;
    private int direction;
    public int getX(){return (int)x;}
    public int getY(){return (int)y;}
    private boolean isMoving=false;
    private int stepNum=1;
    private Paint paint;
    private Bitmap image;
    double angle=0;
    private World world;
    @Override
    public Bitmap getImage() {
        if (paint==null) paint=new Paint();

        Bitmap result=Bitmap.createBitmap(40,40, Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(result);


        Matrix matrix=new Matrix();
        //matrix.postTranslate(image.getWidth()/2, image.getHeight()/2);
        matrix.preRotate((float) (angle),image.getWidth()/2, image.getHeight()/2);
        //matrix.postTranslate(canvas.getWidth() / 2, canvas.getHeight() / 2);

        Log.d("HunterTest", "Angle:" + angle);

        canvas.drawBitmap(image, matrix, paint);

        return result;
    }

    public double getAngle(){return angle;}
    public double playerang;
    public double playerang2;
    public boolean shooting=false;
    public Hunter(){
        x=200;
        y=200;
        direction=1;
        angle=0;
    }
    public Hunter(double x,double y,World world){
        image=ImageLoader.getImage("hunter1");
        this.world=world;
        this.x=x;
        this.y=y;
        direction=1;
        angle=0;
    }
    @Override
    public boolean move(){
        shooting=false;
        //Check if see player;
        double dy=world.player.getY()-y;
        double dx=world.player.getX()-x;
        double distance=Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        double viewang=Math.asin(dx / distance)/Math.PI*180;
        double viewang2=Math.acos(dy/distance)/Math.PI*180;
        playerang=viewang;
        playerang2=viewang2;
        if (playerang==playerang2){
            //1q
        }else
        if (playerang==-1*playerang2){
            //4q
        } else
        if (playerang>0){
            //2q
            playerang=-1*playerang2;
        } else
        if (playerang<0){
            //3q
            playerang=playerang2;
        }
        Log.d("HunterTest","PA:"+playerang+" HA:"+angle);

        if ((Math.abs(playerang-angle)<30) && distance<200){
            stepNum++;

            if (stepNum<10) {
                image = ImageLoader.getImage("hunter1");
            }
            else {
                image=ImageLoader.getImage("hunter2");
            }
            if (stepNum>19) stepNum=1;
            angle=playerang;
            direction=-1*direction;
            int speed=3;
            if (distance<100) {
                speed=2;
                if (Math.random()*10>8) {
                    shooting = true;
                }
            };
            double k=distance/speed;
            double mx=dx/k;
            double my=dy/k;
            if (Math.abs(mx)>Math.abs(dx)) mx=dx;
            if (Math.abs(my)>Math.abs(dy)) my=dy;
            x+=mx;
            y+=my;
            return true;
        } else
        {
            angle+=5*direction;
            if (angle>180) {
                //angle-=Math.PI*2;
                direction=direction*-1;
            } else if (angle<-180) {
                //angle+=Math.PI*2;
                direction=direction*-1;
            }
            return false;
        }
    }
}

