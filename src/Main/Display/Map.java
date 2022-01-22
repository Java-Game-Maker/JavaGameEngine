package Main.Display;

import Main.Msc.ObjectHandler;
import Main.Objects.Object;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
    public void CollisionHandler1()
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
    //rect1.x < rect2.x + rect2.w &&
    //        rect1.x + rect1.w > rect2.x &&
    //        rect1.y < rect2.y + rect2.h &&
    //        rect1.h + rect1.y > rect2.y
    public void CollisionHandler()
    {
        for(Object rect1 : ObjectHandler.getObjects()) {
            for(Object rect2 : ObjectHandler.getObjects()) {
                if((int)rect1.getSpritePosition().getX() < (int)rect2.getSpritePosition().getX() + (int)rect2.getScale().getX() && (int)rect1.getSpritePosition().getX() + (int)rect1.getScale().getX() > (int)rect2.getSpritePosition().getX() && (int)rect1.getSpritePosition().getY() < (int)rect2.getSpritePosition().getY() + (int)rect2.getScale().getY() && (int)rect1.getScale().getY() + (int)rect1.getSpritePosition().getY() > (int)rect2.getSpritePosition().getY())
                {
                    rect1.onCollision(rect2);
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
        CollisionHandler1();
        repaint();
        Toolkit.getDefaultToolkit().sync();
    }
    @Override
    protected void paintComponent(Graphics g) {
        long start = System.nanoTime();
        super.paintComponent(g);

        for(Object animal : ObjectHandler.getObjects())
        {
            g.drawImage((Image) animal.Display(), (int) animal.getSpritePosition().getX(), (int) animal.getSpritePosition().getY(),null);
            g.drawRect((int) ((int) animal.getPosition().getX()), (int) ((int) animal.getPosition().getY()), 10,10);
            if(animal.isShowHitBox())
                //g.drawOval((int) ((int) animal.getPosition().getX()-animal.getScale().getX()/2), (int) ((int) animal.getPosition().getY()-animal.getScale().getY()/2), (int) animal.getRadius(), (int) animal.getRadius());
                g.drawRect((int) ((int) animal.getPosition().getX()-animal.getScale().getX()/2), (int) ((int) animal.getPosition().getY()-animal.getScale().getY()/2), (int) animal.getScale().getX(), (int) animal.getScale().getY());

        }

        long end = System.nanoTime();
        //System.out.println((start-end)/1000000);

    }
}
