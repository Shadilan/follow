package com.coe.follow;

import android.util.Log;

/**
 * Created by Shadilan on 10.12.2015.
 */
public class Hunter {
    private double x;
    private double y;
    private int direction;
    private double angle;
    public double getX(){return x;}
    public double getY(){return y;}
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
    public Hunter(double x,double y){
        this.x=x;
        this.y=y;
        direction=1;
        angle=0;
    }
    public boolean move(Player player){
        shooting=false;
        //Check if see player;
        double dy=player.getY()-y;
        double dx=player.getX()-x;
        double distance=Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        double viewang=Math.asin(dx / distance);
        double viewang2=Math.acos(dy/distance);
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
            playerang=playerang2;
        } else
        if (playerang<0){
            //3q
            playerang=playerang2*-1;
        }


        if ((Math.abs(playerang-angle)<Math.PI/6)){
                    //Move
            Log.d("HunterTest","HA:"+angle+" PA:"+playerang);
            angle=playerang;
            direction=-1*direction;
            int speed=3;
            if (distance<100) {
                speed=2;
                if (Math.random()*10>8) {
                    shooting = true;
                    player.hit();
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
            angle+=Math.PI/36*direction;
            if (angle>Math.PI) {
                //angle-=Math.PI*2;
                direction=direction*-1;
            } else if (angle<Math.PI*-1) {
                //angle+=Math.PI*2;
                direction=direction*-1;
            }
            return false;
        }
    }
}

