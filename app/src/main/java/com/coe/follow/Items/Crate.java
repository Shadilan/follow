package com.coe.follow.Items;

import android.graphics.Bitmap;

import com.coe.follow.GameBase.GameItem;
import com.coe.follow.GameBase.GameObject;
import com.coe.follow.World;
import com.coe.follow.utils.ImageLoader;

/**
 * Created by Shadilan on 12.12.2015.
 */
public class Crate extends GameItem {
    public Crate(int x, int y, World world) {
        super(x, y, world);
    }
    @Override
    public boolean move() {
        return true;
    }
    @Override
    public Bitmap getImage() {
        return ImageLoader.getImage("crate1");
    }

}
