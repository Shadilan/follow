package com.coe.follow.Effects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.coe.follow.GameBase.GameEffect;
import com.coe.follow.GameBase.GameMob;
import com.coe.follow.GameBase.GameObject;
import com.coe.follow.GameBase.GameStructure;
import com.coe.follow.World;
import com.coe.follow.utils.ImageLoader;
import com.coe.follow.utils.MyMath;

/**
 * Created by Shadilan on 20.12.2015.
 */
public class Explosion extends GameEffect{
    Paint paint;
    public Explosion(int x, int y, World world, GameObject owner) {
        super(x, y, world, owner);
        paint=new Paint();
        power=40;
        image= ImageLoader.getImage("explosion");
    }
    public boolean move(){
        int radius=201-power*5;
        image= Bitmap.createBitmap(radius,radius, Bitmap.Config.ARGB_8888);

        Canvas c=new Canvas(image);
        c.drawBitmap(ImageLoader.getImage("explosion"),new Rect(0,0,80,80),new Rect(0,0,image.getWidth(),image.getHeight()),paint);
        for (GameObject o:world.Objs){
            int distance= (int) MyMath.getDistance(x,y,o.getX(),o.getY());
            if (distance==radius && (o instanceof GameMob)&& o!=owner){
                ((GameMob) o).doDamage(this);
            }
            if (distance==radius && (o instanceof GameStructure)&& o!=owner){
                ((GameStructure) o).doDamage(this);
            }
        }
        power--;
        if (power<0){
            Active=false;
            world.removeObject(this);
        }
        return true;
    }
}
