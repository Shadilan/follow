package com.coe.follow.Structures;

import com.coe.follow.GameBase.GameStructure;
import com.coe.follow.Items.Crate;
import com.coe.follow.World;
import com.coe.follow.utils.ImageLoader;
import com.coe.follow.utils.MyMath;

/**
 * Created by Shadilan on 20.12.2015.
 */
public class StoneMine extends GameStructure {
    public StoneMine(int x, int y, World world) {
        super(x, y, world);
        image= ImageLoader.getImage("stonemine1");
        hp=-1;
    }
    int step=0;
    int work=100;

    public boolean move(){
        image= ImageLoader.getImage("stonemine1");
        int playerDistance= (int) MyMath.getDistance(x,y,world.player.getX(),world.player.getY());
        if (playerDistance<120){
            step++;
            work--;
            if (work==0)
            {
                int nx=(int)(Math.random()*160-80+x);
                int ny=(int)(140+y);
                world.addObject(new Crate(nx,ny,world));
                work=100;
            }
            if (step<6){
                image= ImageLoader.getImage("stonemine2");
            } if (step==10){
                step=0;
            }
            else {
                image = ImageLoader.getImage("stonemine3");
            }

        }
        return false;
    }
}
