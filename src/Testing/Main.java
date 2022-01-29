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
        for(int i = 0;i<2000;i++){
            Player s = new Player(new Vector2(i*11,10));
            ComponentHandler.addObject(s);
        }

        Ground ground = new Ground();

        //Label l = new Label();
        //l.setValue("knas");

        //ComponentHandler.addObject(l);
        ComponentHandler.addObject(ground);

        start(null);
    }

}
