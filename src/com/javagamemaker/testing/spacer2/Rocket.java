package com.javagamemaker.testing.spacer2;

import com.javagamemaker.javagameengine.msc.Vector2;

public class Rocket extends Laser {

    public float reloadTime = 1000;
    public Rocket(float angle, Vector2 pos){
        init(angle,pos,"/missile.png");
    }
    public Rocket(){
    }

}
