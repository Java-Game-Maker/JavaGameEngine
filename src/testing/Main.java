package testing;

import javagameengine.JavaGameEngine;
import javagameengine.Scene;
import javagameengine.msc.Debug;
import javagameengine.msc.Padding;
import javagameengine.msc.Vector2;
import javagameengine.ui.Button;
import javagameengine.ui.Panel;

public class Main extends JavaGameEngine {

    public static void main(String[] args){

        Scene scene1 = new Scene();

        Button b = new Button("Text"){
            @Override
            public void onClick() {
                super.onClick();
                Debug.log("asdasd");
            }
        };
        b.setLayer(10);

        scene1.add(b);

        setSelectedScene(scene1);

        JavaGameEngine.size = new Vector2(1920/2,1080/2);

        start();

    }
}