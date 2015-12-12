package com.coe.follow;

/**
 * Created by Shadilan on 10.12.2015.
 */
public class Player {
    private double x;
    public double getX(){return x;}
    private double y;
    public double getY(){return y;}
    private double targetx;
    private double targety;
    private double startX;
    private double startY;
    private int healing=0;
    public boolean getHealing() {
        return healing>0;
    }
    private int hp=100;
    public int getHP(){
        return hp;
    }
    public void heal(){
        hp+=2;
        if (hp>100) hp=100;
    }
    public Player(){
        x=0;
        y=100;
        targetx=x;
        targety=y;
        startX=x;
        startY=y;

    }
    public Player(double x,double y){
        this.x=x;
        this.y=y;
        targetx=x;
        targety=y;
        startX=x;
        startY=y;
    }
    public boolean move(){
        if (x!=targetx || y!=targety)
        {
            double dx=targetx-x;
            double dy=targety-y;
            double g=Math.sqrt(Math.pow(dx,2)+Math.pow(dy,2));
            double k=g/3;
            double mx=dx/k;
            double my=dy/k;
            if (Math.abs(mx)>Math.abs(dx)) mx=dx;
            if (Math.abs(my)>Math.abs(dy)) my=dy;
            x+=mx;
            y+=my;
            if (Math.sqrt(Math.pow(startX-x,2)+Math.pow(startY-y,2))<100){healing=20;}
            if (healing>0){
                healing--;
                heal();
            }

            return true;
        } else return false;
    }
    public void setTarget(double x,double y){
        targetx=x;
        targety=y;
    }
    public void hit(){
        hp--;
    }
}
