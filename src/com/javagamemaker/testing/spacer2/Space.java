package com.javagamemaker.testing.spacer2;

import com.javagamemaker.javagameengine.Scene;
import com.javagamemaker.javagameengine.components.CameraMovement;
import com.javagamemaker.javagameengine.msc.Random;
import com.javagamemaker.javagameengine.msc.Vector2;

import java.awt.*;

public class Space extends Scene {

    public Space(){
        setBackground(Color.black);
        add(Main.player);
    }

    @Override
    public void start() {
        super.start();
        getCamera().add(new CameraMovement());
        //getCamera().setScale(new Vector2(2,2));
        for(int i = 0;i<5;i++){
            Stone stone = new Stone(new Vector2(i*100,0));
            Turret turret = new Turret(new Vector2(i*-100,0));
            //add(stone);
            //add(turret);
        }
    }
}
