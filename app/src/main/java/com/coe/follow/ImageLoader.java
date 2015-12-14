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
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inScaled=false;
        o.inJustDecodeBounds = false;
        map=new HashMap<>();
        Bitmap t;
        t= BitmapFactory.decodeResource(res,R.drawable.hero1);
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
    }
    public static Bitmap getImage(String Name){
        return map.get(Name);
    }
}
