package com.coe.follow.Effects;

import com.coe.follow.GameBase.GameEffect;
import com.coe.follow.GameBase.GameObject;
import com.coe.follow.GameBase.GameStructure;
import com.coe.follow.Structures.StoneMine;
import com.coe.follow.World;
import com.coe.follow.utils.ImageLoader;

/**
 * Created by Shadilan on 20.12.2015.
 */
public class Bite extends GameEffect {

    public Bite(int x, int y, World world, GameObject owner) {
        super(x, y, world, owner);
        power=1;
        image= ImageLoader.getImage("bite1");
    }
    public boolean move(){
        for (GameObject o:world.Objs) {

            double disobj = Math.sqrt(Math.pow(x - o.getX(), 2) + Math.pow(y - o.getY(), 2));
            if (o instanceof GameStructure && !(o instanceof StoneMine) && disobj<80) {
                ((GameStructure) o).doDamage(this);

                break;

            }
        }
        Active=false;
        world.removeObject(this);
        return false;
    }
}
