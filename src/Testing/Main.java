package Testing;

import JavaGameEngine.Backend.ComponentHandler;
import JavaGameEngine.Components.GameObject;
import JavaGameEngine.JavaGameEngine;
import JavaGameEngine.msc.Vector2;

import java.awt.*;

public class Main extends JavaGameEngine{

    public static void main(String[] args){

        init();
        Player s = new Player();
        GameObject child = new GameObject();
        child.setLocalPosition(new Vector2(100,0));
        s.addChild(child);


        ComponentHandler.addObject(s);

        start(null);
    }

}
