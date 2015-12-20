package com.coe.follow.GameBase;

import com.coe.follow.GameBase.GameEffect;
import com.coe.follow.GameBase.GameObject;
import com.coe.follow.World;

/**
 * Строение создаваемое игроком (или существующие со старта.
 */
public class GameStructure extends GameObject {
    public GameStructure(int x, int y, World world) {
        super(x, y, world);
    }
    protected int hp;
    public boolean doDamage(GameEffect effect){
        hp-=effect.getPower();
        if (hp<=0){
            world.removeObject(this);
            return true;
        } else return false;

    };

}
