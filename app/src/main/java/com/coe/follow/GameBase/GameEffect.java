package com.coe.follow.GameBase;

import com.coe.follow.GameBase.GameObject;
import com.coe.follow.World;

/**
 * Используется для объектов генерируемых строениями и монстрами
 * Воздействует на монстров строения и потенциалньо игрока (ка правило не воздействует на создателя.
 */
public class GameEffect extends GameObject {
    protected GameObject owner;
    public GameEffect(int x, int y, World world, GameObject owner) {
        super(x, y, world);
        this.owner=owner;
    }
    protected int power;
    public int getPower(){return power;}
}
