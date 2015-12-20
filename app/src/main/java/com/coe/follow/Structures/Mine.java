package com.coe.follow.Structures;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.coe.follow.Effects.Explosion;
import com.coe.follow.GameBase.GameEffect;
import com.coe.follow.GameBase.GameMob;
import com.coe.follow.GameBase.GameObject;
import com.coe.follow.GameBase.GameStructure;
import com.coe.follow.Monsters.Hunter;
import com.coe.follow.World;
import com.coe.follow.utils.ImageLoader;

/**
 * Created by Shadilan on 14.12.2015.
 */
public class Mine extends GameStructure {
    private int Step=1;
    private Paint paint;
    public Mine(int x,int y,World world){
        super(x,y,world);
        paint=new Paint();
        hp=5;
        image= ImageLoader.getImage("mine1");

    }
    @Override
    public boolean move() {
        Step++;
        if (Step==7){
            image=ImageLoader.getImage("mine2");
        } else if (Step==15){
            Step=1;
            image=ImageLoader.getImage("mine1");
        }
        for (GameObject o:world.Objs) {
            double disobj = Math.sqrt(Math.pow(x - o.getX(), 2) + Math.pow(y - o.getY(), 2));
            if (o instanceof GameMob && disobj<80 && Active) {
                Active=false;
                world.removeObject(this);
                world.addObject(new Explosion((int)x, (int) y,world,this));
                break;

            }
        }
        return false;
    }
    public boolean doDamage(GameEffect effect){
        if (Active) {
            Active = false;
            world.removeObject(this);
            world.addObject(new Explosion((int) x, (int) y, world, this));
            return true;
        }
        return false;
    }

}
