package com.coe.follow.GameBase;


import com.coe.follow.GameBase.GameEffect;
import com.coe.follow.GameBase.GameObject;
import com.coe.follow.World;

/**
 * Используется для враждебных игроку объектов как правило перемещающихся по карте.
 * Объекты могут генерировать эффекты, и стараются разрушить здания.
 */
public class GameMob extends GameObject {
    public GameMob(int x, int y, World world) {
        super(x, y, world);
    }
    protected int hp;
    public boolean doDamage(GameEffect effect){
        hp-=effect.getPower();
        if (hp<=0){
            world.removeObject(this);
            return true;
        } else return false;
    }
}
