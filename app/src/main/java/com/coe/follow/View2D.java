package com.coe.follow;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.coe.follow.utils.ImageLoader;

/**
 * @author Shadilan
 */

public class View2D  extends SurfaceView implements SurfaceHolder.Callback {
    private DrawThread drawThread;
    private Player player;
    private World world;
    public Player getPlayer(){
        return player;
    }
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

        ImageLoader.LoadImage(this.getResources());
        world=new World();
        player=world.player;
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getY() > v.getHeight() - 100) {
                    if (event.getX() < 100) {
                        player.setMine();
                        return false;
                    } else if (event.getX() < 200) {
                        player.setCannon();
                        return false;
                    }
                }
                    player.setTarget((int) (event.getX() / v.getWidth() * world.getWidth()), (int) (event.getY() / (v.getHeight() - 100) * world.getHeight()));
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
