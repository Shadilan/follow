package com.coe.follow.Items;

import com.coe.follow.GameBase.GameItem;
import com.coe.follow.World;
import com.coe.follow.utils.ImageLoader;

/**
 * Created by Shadilan on 20.12.2015.
 */
public class Bag extends GameItem {
    public Bag(int x, int y, World world) {
        super(x, y, world);
        image= ImageLoader.getImage("bag1");
    }
}
