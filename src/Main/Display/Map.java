package Main.Display;

import Main.Main;
import Main.Msc.Input.Input;
import Main.Msc.ObjectHandler;
import Main.Objects.Collision.CircleCollider;
import Main.Objects.Collision.Collider;
import Main.Objects.Collision.ScareCollider;
import Main.Objects.Object;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Map extends JPanel {

    public static LinkedList<Object> newObjects = new LinkedList<>();
    public static LinkedList<Object> delObjects = new LinkedList<>();

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
                Input.keyDown(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                Input.keyUp(e);

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

        if(Map.newObjects.size()>0&&Map.delObjects.size()>0)
        {
            for(int i = 0;i<Math.max(Map.newObjects.size(),Map.delObjects.size());i++)
            {
                try{ObjectHandler.addObject(Map.newObjects.get(i));} catch (Exception e){}
                try{ObjectHandler.removeObject(Map.delObjects.get(i));} catch (Exception e){}
            }
        }
        else
        {
            if(Map.newObjects.size()>0) {
                for (Object o : Map.newObjects) {
                    ObjectHandler.addObject(o);
                }
            }
            if(Map.delObjects.size()>0) {
                for (Object o : Map.delObjects) {
                    ObjectHandler.removeObject(o);
                }
            }
        }
        Map.newObjects.clear();
        for(Object obj:ObjectHandler.getObjects())
        {
            obj.Update();
        }
    }
    private void checkCollisions()
    {
        long start = System.nanoTime();

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
        long end = System.nanoTime();
        //System.out.println((end-start)/100000);
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

        UpdateObjects();
        checkCollisions();

        long end = System.nanoTime();

        repaint();
        Toolkit.getDefaultToolkit().sync();
        //System.out.println((end-start)/100000);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(Main.isPlaying)
        {
            Iterator<Object> iterator = ObjectHandler.getObjects().iterator();

            while (iterator.hasNext()){
                Object ob = iterator.next();
                if(ob.Display()!=null)
                    g.drawImage((Image) ob.Display(), (int) ob.getSpritePosition().getX(), (int) ob.getSpritePosition().getY(),null);
                else
                    g.fillRect((int) ob.getSpritePosition().getX(), (int) ob.getSpritePosition().getY(), (int) ob.getScale().getX(), (int) ob.getScale().getY());

            }
            DrawColliders(g);
        }
    }
    private void DrawColliders(Graphics g)
    {

        Iterator<Object> iterator = ObjectHandler.getObjects().iterator();

        while (iterator.hasNext()){
            Object ob = iterator.next();
            class Point{
                float x;
                float y;
            }


            for(Collider c : ob.getColliders())
            {
                /*
                Point ob11 = new Point();
                ob11.x = c.getPosition().getX()+c.getScale().devide(2).getX();
                ob11.y = c.getPosition().getY()-c.getScale().devide(2).getY();

                Point ob12 = new Point();
                ob12.x = c.getPosition().getX()-c.getScale().devide(2).getX();
                ob12.y = c.getPosition().getY()-c.getScale().devide(2).getY();

                Point ob13 = new Point();
                ob13.x = c.getPosition().getX()-c.getScale().devide(2).getX();
                //ob.getPosition().subtract((ob.getScale().devide(2))).getX();
                ob13.y = c.getPosition().getY()+c.getScale().devide(2).getY();

                Point ob14 = new Point();
                ob14.x = c.getPosition().getX()+c.getScale().devide(2).getX();
                //ob.getPosition().subtract((ob.getScale().devide(2))).getX();
                ob14.y = c.getPosition().getY()+c.getScale().devide(2).getY();
                g.drawOval((int) ob11.x, (int) ob11.y,5,5);
                g.drawOval((int) ob12.x, (int) ob12.y,5,5);
                g.drawOval((int) ob13.x, (int) ob13.y,5,5);
                g.drawOval((int) ob14.x, (int) ob14.y,5,5);
*/
                if(c instanceof CircleCollider&&c.isVisible())
                {
                    g.drawOval((int) (c.getPosition().getX()-(c.getScale().getX()/2)), (int) (c.getPosition().getY()-(c.getScale().getY()/2)), (int) c.getScale().getX(), (int) c.getScale().getY());
                }
                else if(c instanceof ScareCollider &&c.isVisible())
                {
                    g.drawRect((int) (c.getPosition().getX()-(c.getScale().getX()/2)), (int) (c.getPosition().getY()-(c.getScale().getY()/2)), (int) c.getScale().getX(), (int) c.getScale().getY());
                }
            }
        }
    }

}
