package com.javagamemaker.testing.spel2;

import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.Scene;
import com.javagamemaker.javagameengine.msc.Vector2;

public class Main extends JavaGameEngine {
    public static Player player;

    public static void main(String[] args){
        player = new Player();

        player.setPosition(new Vector2(0,-200));

        setSelectedScene(new Level1());
        start();
    }
}
