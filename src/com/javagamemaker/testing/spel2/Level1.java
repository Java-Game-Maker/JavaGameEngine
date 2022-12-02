package com.javagamemaker.testing.spel2;

import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.Scene;
import com.javagamemaker.javagameengine.components.Grabber;
import com.javagamemaker.javagameengine.msc.Vector2;

public class Level1 extends Scene {
    @Override
    public void start() {
        super.start();
        getCamera().setPosition(new Vector2(getCamera().getPosition().getX()*2,JavaGameEngine.getWindowSize().getY()));
        Ground startGround = new Ground(JavaGameEngine.getWindowSize().getX(),new Vector2(0,0));
        add(startGround);
        add(Main.player);
    }

    @Override
    public void update() {
        super.update();

    }
}
