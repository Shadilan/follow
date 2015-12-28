package com.coe.follow;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * поток отрисовки
 */
public class DrawThread  extends Thread{
        private boolean runFlag = false;
        private SurfaceHolder surfaceHolder;
        private Matrix matrix;
        private Bitmap picture;
        private Bitmap back;
        public World world;
        private Paint paint;

        private long prevTime;

        public DrawThread(SurfaceHolder surfaceHolder, Resources resources){
            this.surfaceHolder = surfaceHolder;

            // загружаем картинку, которую будем отрисовывать
            //picture = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher);
            //back=BitmapFactory.decodeResource(resources, R.mipmap.back);
            // формируем матрицу преобразований для картинки
            paint=new Paint();
            paint.setColor(Color.WHITE);
            // сохраняем текущее время
            prevTime = System.currentTimeMillis();
        }

        public void setRunning(boolean run) {
            runFlag = run;
        }
        public boolean getRunning(){return runFlag;}
        @Override
        public void run() {
            Canvas canvas;
            Log.d("DrawThread", "Рисуем, сегодня мы с тобой рисуем...");
            while (runFlag) {
                // получаем текущее время и вычисляем разницу с предыдущим
                // сохраненным моментом времени
                long now = System.currentTimeMillis();
                long elapsedTime = now - prevTime;
                if (elapsedTime > 5) {
                    canvas = null;
                    try {
                        // получаем объект Canvas и выполняем отрисовку
                        canvas = surfaceHolder.lockCanvas(null);
                        if (canvas != null) {

                            synchronized (surfaceHolder) {
                                Bitmap pic=world.getImage();
                                canvas.drawBitmap(pic,new Rect(0,0,pic.getWidth(),pic.getHeight()),new Rect(0,0,canvas.getWidth(),canvas.getHeight()),paint);


                            }
                        }
                    } finally {
                        if (canvas != null) {
                            // отрисовка выполнена. выводим результат на экран
                            surfaceHolder.unlockCanvasAndPost(canvas);
                        }
                    }
                }
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

}
