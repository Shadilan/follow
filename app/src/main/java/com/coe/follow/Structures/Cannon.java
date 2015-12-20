package com.coe.follow.Structures;

import android.graphics.Bitmap;
import android.util.Log;

import com.coe.follow.Effects.Bullet;
import com.coe.follow.GameBase.GameEffect;
import com.coe.follow.GameBase.GameMob;
import com.coe.follow.GameBase.GameObject;
import com.coe.follow.GameBase.GameStructure;
import com.coe.follow.World;
import com.coe.follow.utils.ImageLoader;

/**
 * Created by Shadilan on 16.12.2015.
 */
public class Cannon extends GameStructure {
    private int bullets=100;
    public Cannon(int x,int y,World world){
        super(x,y,world);
        hp=20;
        image= ImageLoader.getImage("cannon2");
    }
    private int Step=0;
    @Override
    public boolean move() {
        if (Step!=50) Step++;

        if (Step==50){
            for (GameObject o:world.Objs){
                double distance=Math.sqrt(Math.pow(o.getX()-x,2)+Math.pow(o.getY()-y,2));
                if (o instanceof GameMob && distance<300){
                    Step=0;
                    world.addObject(new Bullet((int)x,(int)y,world,this,o));
                    bullets--;
                    if (bullets<0)
                    {
                        Active = false;
                        world.removeObject(this);
                    }
                    break;
                }
            }
        }
        switch (Step){
            case 0: image=ImageLoader.getImage("cannon2");
                break;
            case 10: image=ImageLoader.getImage("cannon3");
                break;
            case 20: image=ImageLoader.getImage("cannon4");
                break;
            case 30: image=ImageLoader.getImage("cannon5");
                break;
            case 40: image=ImageLoader.getImage("cannon6");
                break;
            case 50: image=ImageLoader.getImage("cannon1");
        }
        return false;
    }
    @Override
    public boolean doDamage(GameEffect effect) {
        hp-=effect.getPower();
        if (hp<0){
            Active = false;
            world.removeObject(this);
            return true;
        } else return false;
    }

}
