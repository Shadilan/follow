package com.coe.follow;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * @author Shadilan
 */

public class View2D  extends SurfaceView implements SurfaceHolder.Callback {
    private DrawThread drawThread;
    private Player player;
    private World world;
    public View2D(Context context) {
        super(context);
        getHolder().addCallback(this);
    }
    public View2D(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
    }

    public View2D(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getHolder().addCallback(this);

    }
    private void initListeners(){
        //Переписать.
        world=new World(this.getWidth(),this.getHeight());
        player=world.player;
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                player.setTarget((int) event.getX(), (int) event.getY());
                return true;
            }
        });
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d("DrawThread", "Раз два три четыри пять, начинаю рисовать...");
        initListeners();
        drawThread = new DrawThread(getHolder(), getResources());
        drawThread.world=world;
        drawThread.setRunning(true);
        drawThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        // завершаем работу потока
        drawThread.setRunning(false);
        while (retry) {
            try {
                drawThread.join();
                retry = false;
            } catch (InterruptedException e) {
                // если не получилось, то будем пытаться еще и еще
            }
        }
    }
}
