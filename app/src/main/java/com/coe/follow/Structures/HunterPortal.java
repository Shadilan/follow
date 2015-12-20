package com.coe.follow.Structures;

import com.coe.follow.GameBase.GameStructure;
import com.coe.follow.Monsters.Hunter;
import com.coe.follow.World;
import com.coe.follow.utils.ImageLoader;

/**
 * Created by Shadilan on 20.12.2015.
 */
public class HunterPortal extends MonsterPortal {
    public HunterPortal(int x, int y, World world) {
        super(x, y, world);
        image= ImageLoader.getImage("hunterportal1");
    }
    int step=0;
    int summon=500;
    public boolean move(){
        step++;
        summon--;
        if (step==10){
            image=ImageLoader.getImage("hunterportal2");
        } else if(step==20){
            image=ImageLoader.getImage("hunterportal3");
        } else if(step==30){
            image=ImageLoader.getImage("hunterportal1");
            step=0;
            //Создать эманацию портала
        }
        if (summon==0){
            summon=500;
            world.addObject(new Hunter((int)x,(int)y,world));
        }

        return false;
    }
}
