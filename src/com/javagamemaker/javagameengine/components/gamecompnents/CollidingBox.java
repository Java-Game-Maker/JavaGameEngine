package com.javagamemaker.javagameengine.components.gamecompnents;

import com.javagamemaker.javagameengine.components.Collider;
import com.javagamemaker.javagameengine.components.GameObject;
import com.javagamemaker.javagameengine.msc.Vector2;

import java.util.ArrayList;

public class CollidingBox extends GameObject {
    Vector2 newPost = new Vector2(0,0);
    public CollidingBox(ArrayList<Vector2> localVertices) {
        super(localVertices);
    }

    public CollidingBox() {
    }

    public CollidingBox(Vector2 vector2) {
        newPost = vector2;
    }

    @Override
    public void start() {
        super.start();
        add(new Collider(true));
        setPosition(newPost);
    }
}
