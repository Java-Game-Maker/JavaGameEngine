package Testing;

import JavaGameEngine.Backend.ComponentHandler;
import JavaGameEngine.Components.GameObject;
import JavaGameEngine.Components.Ui.Label;
import JavaGameEngine.JavaGameEngine;
import JavaGameEngine.msc.Vector2;
import java.awt.*;
import javax.swing.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main extends JavaGameEngine{

    public static void main(String[] args){

        init();

        Player s = new Player(new Vector2(10,10));
        ComponentHandler.addObject(s);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setTitle("My example Title");
        frame.setSize(500,500);

        Ground ground = new Ground();
        ComponentHandler.addObject(ground);
        
        // Test t = new Test();
        // ComponentHandler.addObject(t);

        for(int i = 0;i<10;i++){
            Coin coin = new Coin();
            coin.setPosition(new Vector2(i*100,420));
            ComponentHandler.addObject(coin);
        }
        
        start();
    }

}
