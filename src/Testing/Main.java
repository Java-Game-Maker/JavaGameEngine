package Testing;

import JavaGameEngine.Backend.ComponentHandler;
import JavaGameEngine.Components.GameObject;
import JavaGameEngine.Components.Ui.Label;
import JavaGameEngine.JavaGameEngine;
import JavaGameEngine.msc.Vector2;

import java.awt.*;

public class Main extends JavaGameEngine{

    public static void main(String[] args){

        init();
        Player s = new Player();
        Ground ground = new Ground();
        //Label l = new Label();
        //l.setValue("knas");

        //ComponentHandler.addObject(l);
        ComponentHandler.addObject(ground);
        ComponentHandler.addObject(s);

        start(null);
    }

}
