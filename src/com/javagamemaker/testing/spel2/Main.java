package com.javagamemaker.testing.spel2;

import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.components.lights.LightManager;
import com.javagamemaker.javagameengine.msc.Vector2;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

/**TODO
 * Spawn coins in chunks
 * add a enemy
 * upgrade graphics (cookie, coin and background)
 * sound
 */
public class Main extends JavaGameEngine {
    public static Player player;
    public static void main(String[] args){
        //Debug.showWhere=true;
        player = new Player();
        size = new Vector2(600,1000);
        player.setPosition(new Vector2(0,-200));
        g.setY(g.getY()*2);
        //setSelectedScene(new Level1());
        setSelectedScene(new Splashscreen());
        start();
    }


}
