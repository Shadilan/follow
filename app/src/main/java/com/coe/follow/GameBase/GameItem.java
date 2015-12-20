package com.coe.follow.GameBase;

import com.coe.follow.World;

/**
 * Используется для объектов которые активируются единоразово при приближении игрока.
 */
public class GameItem extends GameObject {
    public GameItem(int x, int y, World world) {
        super(x, y, world);
    }
}
