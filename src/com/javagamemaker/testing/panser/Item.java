package com.javagamemaker.testing.panser;

import com.javagamemaker.javagameengine.components.Collider;
import com.javagamemaker.javagameengine.components.Sprite;

public class Item extends Sprite {

    public Item(){
        add(new Collider());
    }

}
