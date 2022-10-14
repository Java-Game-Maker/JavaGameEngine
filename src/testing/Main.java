package testing;

import javagameengine.JavaGameEngine;
import javagameengine.Scene;
import javagameengine.components.*;
import javagameengine.components.Component;
import javagameengine.components.shapes.Circle;
import javagameengine.components.shapes.Rect;
import javagameengine.input.Input;
import javagameengine.input.Keys;
import javagameengine.msc.Debug;
import javagameengine.msc.Padding;
import javagameengine.msc.Vector2;
import javagameengine.ui.Button;
import javagameengine.ui.Column;
import javagameengine.ui.Panel;
import javagameengine.ui.UiElement;

import java.awt.*;

public class Main extends JavaGameEngine {

    public static void main(String[] args){

        Scene scene1 = new Scene();

        Panel twoCols = new Panel();
        twoCols.setPadding(new Padding(5));
        twoCols.setColor(Color.red);
        twoCols.setPosition(new Vector2(100,100));

        Panel col1 = new Panel();
        col1.setPadding(new Padding(4));
        Column column = new Column(col1);
        col1.setLayout(column);

        col1.setColor(Color.green);
        col1.add(new Button("1"));
        col1.add(new Button("2"));
        col1.add(new Button("3"));


        Panel col2 = new Panel();
        col2.setPadding(new Padding(4));
        Column column1 = new Column(col2);
        col2.setLayout(column1);

        col2.setColor(Color.blue);
        col2.add(new Button("4"));
        col2.add(new Button("5"));
        col2.add(new Button("6"));

        twoCols.add(col2);
        twoCols.add(col1);

        scene1.add(twoCols);

        scene1.getCamera().add(new CameraMovement());

        setSelectedScene(scene1);

        JavaGameEngine.size = new Vector2(1920/2,1080/2);

        start();


    }


}
