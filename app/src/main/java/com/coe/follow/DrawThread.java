package com.coe.follow;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by Shadilan on 10.12.2015.
 */
public class DrawThread  extends Thread{
        private boolean runFlag = false;
        private SurfaceHolder surfaceHolder;
        private Matrix matrix;
        private Bitmap picture;
        public World world;
        private Paint paint;

        private long prevTime;

        public DrawThread(SurfaceHolder surfaceHolder, Resources resources){
            this.surfaceHolder = surfaceHolder;

            // загружаем картинку, которую будем отрисовывать
            picture = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher);

            // формируем матрицу преобразований для картинки
            matrix = new Matrix();
            matrix.postScale(3.0f, 3.0f);
            matrix.postTranslate(100.0f, 100.0f);

            paint=new Paint();
            paint.setColor(Color.WHITE);
            // сохраняем текущее время
            prevTime = System.currentTimeMillis();
        }

        public void setRunning(boolean run) {
            runFlag = run;
        }

        @Override
        public void run() {
            Canvas canvas;
            Log.d("DrawThread", "Рисуем, сегодня мы с тобой рисуем...");
            while (runFlag) {
                // получаем текущее время и вычисляем разницу с предыдущим
                // сохраненным моментом времени
                long now = System.currentTimeMillis();
                long elapsedTime = now - prevTime;
                if (elapsedTime > 30) {
                    // если прошло больше 30 миллисекунд - сохраним текущее время
                    // и повернем картинку на 2 градуса.
                    // точка вращения - центр картинки
                    prevTime = now;
                    matrix.preRotate(2.0f, picture.getWidth() / 2, picture.getHeight() / 2);
                }
                if (elapsedTime > 10) {
                    canvas = null;
                    try {
                        // получаем объект Canvas и выполняем отрисовку
                        canvas = surfaceHolder.lockCanvas(null);
                        if (canvas != null) {
                            synchronized (surfaceHolder) {
                                world.handler.sendEmptyMessage(0);
                                canvas.drawColor(Color.BLACK);
                                canvas.drawBitmap(picture, matrix, null);
                                if (world.player.getHealing()){
                                    paint.setColor(Color.CYAN);
                                    canvas.drawCircle((int)world.player.getX(),(int)world.player.getY(),12,paint);
                                }
                                paint.setColor(Color.WHITE);
                                canvas.drawCircle((int) world.player.getX(), (int) world.player.getY(), 10, paint);

                                if (world.hunter.shooting) {
                                    paint.setColor(Color.GREEN);
                                    canvas.drawLine((int) world.hunter.getX(), (int) world.hunter.getY(), (int) world.player.getX(), (int) world.player.getY(), paint);
                                }

                                paint.setColor(Color.RED);
                                canvas.drawText("HP:"+world.player.getHP(),10,10,paint);
                                canvas.drawCircle((int) world.hunter.getX(), (int) world.hunter.getY(), 10, paint);
                                canvas.drawCircle((int) (world.hunter.getX() + Math.sin(world.hunter.getAngle()) * 8), (int) (world.hunter.getY() + Math.cos(world.hunter.getAngle()) * 8), 6, paint);
                                paint.setColor(Color.BLACK);
                                canvas.drawCircle((int) (world.hunter.getX() + Math.sin(world.hunter.getAngle()) * 8), (int) (world.hunter.getY() + Math.cos(world.hunter.getAngle()) * 8), 5, paint);



                            }
                        }
                    } finally {
                        if (canvas != null) {
                            // отрисовка выполнена. выводим результат на экран
                            surfaceHolder.unlockCanvasAndPost(canvas);
                        }
                    }
                }
            }
        }

}
