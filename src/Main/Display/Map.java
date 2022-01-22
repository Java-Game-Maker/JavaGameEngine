package Main.Display;

import Main.Main;
import Main.Msc.ObjectHandler;
import Main.Objects.Collision.CircleCollider;
import Main.Objects.Collision.Collider;
import Main.Objects.Object;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Map extends JPanel {

    private boolean holding = false;
    private KeyEvent keyEvent;
    private static ArrayList<JComponent> jComponents = new ArrayList<>();

    public Map() {

        for(JComponent c : jComponents)
        {
            add(c);

        }
        setBackground(Color.GREEN);

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

    public static void addJComponent(JComponent jComponent)
    {
        jComponents.add(jComponent);
    }
    public static void removeJComponent(JComponent jComponent)
    {
        jComponents.remove(jComponent);

    }

    public static ArrayList<JComponent> getJComponents() {
        return jComponents;
    }

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
    /**Check if a Jcomponent has been removed in the Jcomponents list and if so removes it in the panel**/
    public void UpdateSwingComponents()
    {
        for(Component c : getComponents())
        {
            if(!jComponents.contains(c))
            {
                remove(c);
            }
        }
    }

    public void Update()
    {
        UpdateSwingComponents();
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
        //System.out.println((end-start)/100000);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(Main.isPlaying)
        {
            for(Object animal : ObjectHandler.getObjects())
            {
                g.drawImage((Image) animal.Display(), (int) animal.getSpritePosition().getX(), (int) animal.getSpritePosition().getY(),null);

            }
            DrawColliders(g);
        }
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
                }
            }
        }
    }
}
