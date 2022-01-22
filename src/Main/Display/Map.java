package Main.Display;

import Main.Msc.ObjectHandler;
import Main.Objects.Collision.CircleCollider;
import Main.Objects.Collision.Collider;
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

    private void UpdateObjects()
    {
        for(Object obj:ObjectHandler.getObjects())
        {
            obj.Update();
        }
    }
    private void checkCollisions()
    {
        for(Object ob1 : ObjectHandler.getObjects())
        {
            for(Object ob2 : ObjectHandler.getObjects())
            {
                if(ob1!=(ob2))
                {
                    for(Collider c1 : ob1.getColliders())
                    {
                        for(Collider c2 : ob2.getColliders())
                        {
                            c1.collided(c2);
                        }
                    }
                }
            }
        }
    }
    public void Update()
    {
        long start = System.nanoTime();

        if(holding)
        {
            for(Object obj:ObjectHandler.getObjects())
            {
                obj.keyDown(keyEvent);
            }
        }
        UpdateObjects();
        checkCollisions();
        repaint();
        Toolkit.getDefaultToolkit().sync();
        long end = System.nanoTime();
        //System.out.println((start-end)/1000000);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for(Object animal : ObjectHandler.getObjects())
        {
            g.drawImage((Image) animal.Display(), (int) animal.getSpritePosition().getX(), (int) animal.getSpritePosition().getY(),null);
            g.drawRect((int) ((int) animal.getPosition().getX()), (int) ((int) animal.getPosition().getY()), 10,10);

        }
        DrawColliders(g);


    }
    private void DrawColliders(Graphics g)
    {
        for(Object ob : ObjectHandler.getObjects())
        {
            for(Collider c : ob.getColliders())
            {
                if(c instanceof CircleCollider&&c.isVisible())
                {
                    g.drawOval((int) (c.getPosition().getX()-(c.getScale().getX()/2)), (int) (c.getPosition().getY()-(c.getScale().getY()/2)), (int) c.getScale().getX(), (int) c.getScale().getY());
                    g.drawRect((int) c.getPosition().getX(), (int) c.getPosition().getY(),10,10);
                }
            }
        }
    }
}
