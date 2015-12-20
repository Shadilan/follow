package com.coe.follow.Effects;

import android.graphics.Bitmap;

import com.coe.follow.GameBase.GameEffect;
import com.coe.follow.GameBase.GameMob;
import com.coe.follow.GameBase.GameObject;
import com.coe.follow.Monsters.Hunter;
import com.coe.follow.World;
import com.coe.follow.utils.ImageLoader;

/**
 * Created by Shadilan on 16.12.2015.
 */
public class Bullet extends GameEffect {
    private World world;
    private GameObject target;
    public Bullet(int x,int y,World world, GameObject owner,GameObject target){
        super(x,y,world,owner);
        this.target=target;
        image= ImageLoader.getImage("ball1");
    }
    private int Step=0;
    @Override
    public boolean move() {
        Step++;
        switch (Step) {
            case 1: image=ImageLoader.getImage("ball1");
            break;
            case 5: image=ImageLoader.getImage("ball2");
                break;
            case 10: image=ImageLoader.getImage("ball3");
                break;
            case 15: image=ImageLoader.getImage("ball4");
                break;
            case 20: image=ImageLoader.getImage("ball5");
                break;
            case 25: image=ImageLoader.getImage("ball6");
                break;
            case 29: Step=0;
        }
        double dx=target.getX()-x;
        double dy=target.getY()-y;
        double k=Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2))/20;
        double mx=dx/k;
        double my=dy/k;
        if (Math.abs(mx)>Math.abs(dx)) mx=dx;
        if (Math.abs(my)>Math.abs(dy)) my=dy;
        x+=mx;
        y+=my;
        for (GameObject o:world.Objs){
            double distance=Math.sqrt(Math.pow(o.getX()-x,2)+Math.pow(o.getY()-y,2));
            if (o instanceof GameMob && distance<80){
                ((GameMob) o).doDamage(this);
                world.removeObject(this);
                break;
            }

        }
        if (!target.getActive() || (x>world.getWidth() || x<0 || y>world.getHeight() || y<0))
        {
            world.removeObject(this);
        }
        return true;
    }




}
