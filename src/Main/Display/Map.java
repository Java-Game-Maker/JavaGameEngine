package Main.Display;

import Main.Msc.ObjectHandler;
import Main.Objects.Object;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Map extends JPanel {

    private boolean holding = false;
    private KeyEvent keyEvent;
    public Map() {

        setBackground(new Color(44, 157, 228));

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                holding=true;
                keyEvent= e;
                for(Object obj:ObjectHandler.getObjects())
                {
                    obj.keyPressed(e);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                holding=false;
                for(Object obj:ObjectHandler.getObjects())
                {
                    obj.keyReleased(e);
                }
            }
        });

    }
    int x = 0;
    public void CollisionHandler()
    {
        for(Object obj : ObjectHandler.getObjects()) {
            for(Object obj2 : ObjectHandler.getObjects()) {
                if(obj.getPosition().getDistance(obj2.getPosition())<=obj.getRadius()&&!obj.equals(obj2))
                {
                    obj.onCollision(obj2);
                }
            }
        }
    }
    private void UpdateObjects()
    {
        for(Object obj:ObjectHandler.getObjects())
        {
            obj.Update();
        }
    }

    public void Update()
    {
        if(holding)
        {
            for(Object obj:ObjectHandler.getObjects())
            {
                obj.keyDown(keyEvent);
            }
        }
        UpdateObjects();
        CollisionHandler();
        repaint();
        Toolkit.getDefaultToolkit().sync();
    }
    @Override
    protected void paintComponent(Graphics g) {
        long start = System.nanoTime();
        super.paintComponent(g);

        for(Object animal : ObjectHandler.getObjects())
        {
            g.drawImage((Image) animal.getAnimation(), (int) animal.getSpritePosition().getX(), (int) animal.getSpritePosition().getY(),null);
            g.drawOval((int) ((int) animal.getPosition().getX()-animal.getRadius()/2), (int) ((int) animal.getPosition().getY()-animal.getRadius()/2), (int) animal.getRadius(), (int) animal.getRadius());

        }

        long end = System.nanoTime();
        //System.out.println((start-end)/1000000);

    }
}
