package com.coe.follow;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

/**
 * Created by Shadilan on 13.12.2015.
 */
public class Field implements GameObject{
    private int stepNum=1;
    private Paint paint;
    private Bitmap image;
    private World world;
    private double x;
    private int crates=0;
    private Bitmap crateImg;
    public void addCrate(){
        crates++;
        if (crateImg==null){
            crateImg=Bitmap.createBitmap(image.getWidth(),image.getHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas =new Canvas(crateImg);
        int width=canvas.getWidth();
        Bitmap crate=ImageLoader.getImage("crate1");
        int count=width/crate.getWidth();
        double cx=(crates-1)%count*crate.getWidth();
        double cy=(crates-1)/count*crate.getHeight();
        canvas.drawBitmap(crate,(int)cx,(int)cy,paint);
    }
    public int getX(){
        return (int)x;
    }
    private double y;
    public int getY(){
        return (int)y;}

    @Override
    public boolean move() {
        stepNum++;

        if (stepNum==1) {
            image = ImageLoader.getImage("field1");
        }
        else if (stepNum==9) {
            image=ImageLoader.getImage("field2");
        }
        if (stepNum>9) stepNum=0;
        return true;
    }

    @Override
    public Bitmap getImage() {
        Bitmap result=Bitmap.createBitmap(image.getWidth(),image.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(image,0,0,paint);
        if (crateImg!=null) canvas.drawBitmap(crateImg,0,0,paint);
        return result;
    }

    @Override
    public String getType() {
        return "Field";
    }

    public Field(int x,int y,World world){
        this.x=x;
        this.y=y;
        this.world=world;
        image=ImageLoader.getImage("field1");
    }
}
