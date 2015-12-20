package com.coe.follow.Structures;

import com.coe.follow.GameBase.GameEffect;
import com.coe.follow.GameBase.GameStructure;
import com.coe.follow.World;

/**
 * Created by Shadilan on 20.12.2015.
 */
public class MonsterPortal extends GameStructure {
    protected int hp=10000;
    public MonsterPortal(int x, int y, World world) {
        super(x, y, world);
    }
    public boolean doDamage(GameEffect effect){
        hp-=effect.getPower();
        if (hp<0){
            Active=false;
            world.removeObject(this);
            return true;
        }
        return false;
    }
}
