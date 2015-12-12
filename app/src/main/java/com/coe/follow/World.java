package com.coe.follow;


import android.os.Handler;
import android.view.Window;

/**
 * Created by Shadilan on 10.12.2015.
 */
public class World {
    Handler handler;
    Player player;
    Hunter hunter;
    public World(int Width,int Height){
        player= new Player(Width/2,0);
        hunter=new Hunter(Width/2,Height/2);
        handler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                boolean isPlayerMoved = player.move();
                if (isPlayerMoved) {//MoveHunter}
                    hunter.move(player);
                }
            }
        };
    }
    public World() {
        player = new Player();
        hunter = new Hunter();
        handler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                boolean isPlayerMoved = player.move();
                if (isPlayerMoved) {//MoveHunter}
                    hunter.move(player);
                }
            }
        };
    }
}
