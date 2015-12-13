package com.coe.follow;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.HashMap;

/**
 * Created by Shadilan on 12.12.2015.
 */
public class ImageLoader {
    private static HashMap<String,Bitmap> map;
    public static void LoadImage(Resources res){
        map=new HashMap<>();
        Bitmap t;
        t= BitmapFactory.decodeResource(res,R.mipmap.hero1);
        map.put("hero1",t);
        t= BitmapFactory.decodeResource(res,R.mipmap.hero2);
        map.put("hero2",t);
        t= BitmapFactory.decodeResource(res,R.mipmap.back);
        map.put("back",t);
        t= BitmapFactory.decodeResource(res,R.mipmap.hunter1);
        map.put("hunter1",t);
        t= BitmapFactory.decodeResource(res,R.mipmap.hunter2);
        map.put("hunter2",t);
        t= BitmapFactory.decodeResource(res,R.mipmap.field);
        map.put("field1",t);
        t= BitmapFactory.decodeResource(res,R.mipmap.field2);
        map.put("field2",t);
        t= BitmapFactory.decodeResource(res,R.mipmap.crate1);
        map.put("crate1",t);
    }
    public static Bitmap getImage(String Name){
        return map.get(Name);
    }
}
