package com.coe.follow.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.coe.follow.R;

import java.util.HashMap;

/**
 * Created by Shadilan on 12.12.2015.
 */
public class ImageLoader {
    private static HashMap<String,Bitmap> map;
    public static void LoadImage(Resources res){
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inScaled=false;
        o.inJustDecodeBounds = false;
        map=new HashMap<>();
        Bitmap t;
        t= BitmapFactory.decodeResource(res, R.drawable.hero1);
        map.put("hero1",t);
        t= BitmapFactory.decodeResource(res,R.drawable.hero2);
        map.put("hero2",t);
        t= BitmapFactory.decodeResource(res,R.drawable.herocargo1);
        map.put("herocargo1",t);
        t= BitmapFactory.decodeResource(res,R.drawable.herocargo2);
        map.put("herocargo2",t);

        t= BitmapFactory.decodeResource(res,R.drawable.back);
        map.put("back",t);

        t= BitmapFactory.decodeResource(res,R.drawable.hunter1);
        map.put("hunter1",t);
        t= BitmapFactory.decodeResource(res,R.drawable.hunter2);
        map.put("hunter2",t);

        t= BitmapFactory.decodeResource(res,R.drawable.field);
        map.put("field1",t);
        t= BitmapFactory.decodeResource(res,R.drawable.field2);
        map.put("field2",t);

        t= BitmapFactory.decodeResource(res,R.drawable.crate1);
        map.put("crate1",t);

        t= BitmapFactory.decodeResource(res,R.drawable.wall1);
        map.put("wall1",t);
        t= BitmapFactory.decodeResource(res,R.drawable.wall2);
        map.put("wall2",t);
        t= BitmapFactory.decodeResource(res,R.drawable.wall3);
        map.put("wall3",t);
        t= BitmapFactory.decodeResource(res,R.drawable.wall4);
        map.put("wall4",t);

        t= BitmapFactory.decodeResource(res,R.drawable.mine1);
        map.put("mine1",t);
        t= BitmapFactory.decodeResource(res,R.drawable.mine2);
        map.put("mine2",t);
        t= BitmapFactory.decodeResource(res,R.drawable.explosion);
        map.put("explosion",t);

        t= BitmapFactory.decodeResource(res,R.drawable.ball1);
        map.put("ball1",t);
        t= BitmapFactory.decodeResource(res,R.drawable.ball2);
        map.put("ball2",t);
        t= BitmapFactory.decodeResource(res,R.drawable.ball3);
        map.put("ball3",t);
        t= BitmapFactory.decodeResource(res, R.drawable.ball4);
        map.put("ball4",t);
        t= BitmapFactory.decodeResource(res,R.drawable.ball5);
        map.put("ball5",t);
        t= BitmapFactory.decodeResource(res,R.drawable.ball6);
        map.put("ball6",t);

        t= BitmapFactory.decodeResource(res,R.drawable.cannon1);
        map.put("cannon1",t);
        t= BitmapFactory.decodeResource(res,R.drawable.cannon2);
        map.put("cannon2",t);
        t= BitmapFactory.decodeResource(res,R.drawable.cannon3);
        map.put("cannon3",t);
        t= BitmapFactory.decodeResource(res,R.drawable.cannon4);
        map.put("cannon4",t);
        t= BitmapFactory.decodeResource(res,R.drawable.cannon5);
        map.put("cannon5",t);
        t= BitmapFactory.decodeResource(res,R.drawable.cannon6);
        map.put("cannon6",t);

        t= BitmapFactory.decodeResource(res,R.drawable.base1);
        map.put("base1",t);
        t= BitmapFactory.decodeResource(res,R.drawable.base2);
        map.put("base2",t);
        t= BitmapFactory.decodeResource(res,R.drawable.base3);
        map.put("base3",t);
        t= BitmapFactory.decodeResource(res,R.drawable.base4);
        map.put("base4",t);

        t= BitmapFactory.decodeResource(res,R.drawable.bag1);
        map.put("bag1",t);

        t= BitmapFactory.decodeResource(res,R.drawable.hunterportal1);
        map.put("hunterportal1",t);
        t= BitmapFactory.decodeResource(res,R.drawable.hunterportal2);
        map.put("hunterportal2",t);
        t= BitmapFactory.decodeResource(res,R.drawable.hunterportal3);
        map.put("hunterportal3",t);

        t= BitmapFactory.decodeResource(res,R.drawable.bite1);
        map.put("bite11",t);

        t= BitmapFactory.decodeResource(res,R.drawable.explosionb);
        map.put("explosionb",t);
        t= BitmapFactory.decodeResource(res,R.drawable.cannonb);
        map.put("cannonb",t);

    }
    public static Bitmap getImage(String Name){
        Bitmap res=map.get(Name);
        if (res == null) res=map.get("wall3");
        return res;
    }
}
