package JavaGameEngine.Backend;

import JavaGameEngine.Components.Component;
import JavaGameEngine.Components.GameObject;

import javax.swing.*;
import java.awt.*;

public class GameWorld extends JPanel{



    /**
     * Here is the main drawing function
     * */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawComponents(g);
    }

    private void drawComponents(Graphics g){
        for(Component c : ComponentHandler.getObjects()){
            try{
                ((GameObject)c).draw(g);
            }catch (Exception e){}
        }
    }

}
