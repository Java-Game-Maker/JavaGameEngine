package com.javagamemaker.testing.panser;

import com.javagamemaker.javagameengine.components.Collider;
import com.javagamemaker.javagameengine.components.GameObject;

public class Wall extends GameObject {

    public Wall (){
        Collider c = new Collider();
        add(c);
    }


}
