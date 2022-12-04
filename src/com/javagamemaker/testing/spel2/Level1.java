package com.javagamemaker.testing.spel2;

import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.Scene;
import com.javagamemaker.javagameengine.components.lights.LightManager;
import com.javagamemaker.javagameengine.input.Input;
import com.javagamemaker.javagameengine.msc.Debug;
import com.javagamemaker.javagameengine.msc.Random;
import com.javagamemaker.javagameengine.msc.Vector2;

import javax.swing.*;
import java.awt.*;

public class Level1 extends Scene {
    public static JLabel coinsLabel = new JLabel("Coins: 0");
    public Level1(){
        // ui
        Debug.showWhere = true;
        LightManager.opacity = 0.99f;
        setBackground(new Color(50,50,50));
        JavaGameEngine.masterVolume = 0.1f;
        //playSound("/spel2/sound/ambience.wav");
        //playSound("/spel2/sound/theme.wav");
        coinsLabel.setFont(new Font("Verdana",Font.BOLD,32));
        coinsLabel.setForeground(Color.WHITE);
        coinsLabel.setLocation(100,100);
        coinsLabel.setSize(1000,100);
        add(coinsLabel);
    }
    @Override
    public void start() {
        getCamera().setPosition(new Vector2(getCamera().getPosition().getX()*2,JavaGameEngine.getWindowSize().getY()));
        Ground startGround = new Ground(JavaGameEngine.getWindowSize().getX(),new Vector2(0,0)){
            @Override
            public void respawn() {}
        };

        add(startGround);

        add(new Ground(100,new Vector2(-100,-200)));
        add(new Ground(100,new Vector2(0,-400)));
        add(new Ground(100,new Vector2(100,-600)));

        add(new Ground(100,new Vector2(-100,-800)){
            @Override
            public void onCameraLeft() {
                super.onCameraLeft();
                if(Math.round(new Random().nextFloat(0,3))==3){
                    switch (new Random().nextInt(3)){
                        case 0:
                            instantiate(new CoinChunk(CoinChunk.box,getPosition().removeX().add(new Vector2(-300,-1500))));
                            break;
                        case 1:
                            instantiate(new CoinChunk(CoinChunk.spiral,getPosition().removeX().add(new Vector2(-300,-1500))));
                            break;
                        case 2:
                            instantiate(new CoinChunk(CoinChunk.pipe,getPosition().removeX().add(new Vector2(-300,-1500))));
                            break;
                    }
                }
            }
        });
        add(new Ground(100,new Vector2(0,-1000)));
        add(new Ground(100,new Vector2(100,-1200)));
        add(new Ground(100,new Vector2(200,-1400)));
        add(new Ground(100,new Vector2(-200,-1600)));
        add(new Ground(100,new Vector2(0,-1800)));
        add(new Ground(100,new Vector2(-300,-2000)));

        add(new CoinChunk(CoinChunk.pipe,new Vector2(-300,-1300)));

        add(new Enemy(new Vector2(150,-110)));
        add(Main.player);
        super.start();
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.drawOval((int) (Input.getMousePositionOnCanvas().getX()-20), (int) (Input.getMousePositionOnCanvas().getY()-20),50,50);
    }
}
