package com.javagamemaker.testing.spel2;

import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.Scene;
import com.javagamemaker.javagameengine.components.Grabber;
import com.javagamemaker.javagameengine.msc.Vector2;

import javax.swing.*;

public class Level1 extends Scene {
    public Level1(){
        // ui
        JPanel p = new JPanel();

        p.setLocation(300-50,0);
        p.setSize(100,50);
        p.add(new JLabel("Highscore 100"));
        p.add(new JLabel("Coins 100"));

        add(p);
    }
    @Override
    public void start() {
        super.start();
        getCamera().setPosition(new Vector2(getCamera().getPosition().getX()*2,JavaGameEngine.getWindowSize().getY()));
        Ground startGround = new Ground(JavaGameEngine.getWindowSize().getX(),new Vector2(0,0)){
            @Override
            public void onCameraLeft() {}
        };

        add(startGround);

        add(new Ground(100,new Vector2(-100,-200)));
        add(new Ground(100,new Vector2(0,-400)));
        add(new Ground(100,new Vector2(100,-600)));

        add(new Ground(100,new Vector2(-100,-800)));
        add(new Ground(100,new Vector2(0,-1000)));
        add(new Ground(100,new Vector2(100,-1200)));

        add(new CoinChunk(CoinChunk.pipe, new Vector2(-300, -1300)));

        add(Main.player);
    }

    @Override
    public void update() {
        super.update();

    }
}
