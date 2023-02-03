package com.javagamemaker.testing.solar;

import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.Scene;
import com.javagamemaker.javagameengine.components.*;
import com.javagamemaker.javagameengine.components.Component;
import com.javagamemaker.javagameengine.components.shapes.Rect;
import com.javagamemaker.javagameengine.input.Input;
import com.javagamemaker.javagameengine.input.Keys;
import com.javagamemaker.javagameengine.msc.Debug;
import com.javagamemaker.javagameengine.msc.Vector2;

import java.awt.*;
import java.util.LinkedList;

public class Main extends JavaGameEngine {


    static GameObject sun = new GameObject(new Rect(50,50));
    static PhysicsBody sunBody = new PhysicsBody();

    public static void main(String[] args){


        sun = new GameObject(new Rect(50,50)){
            @Override
            public void mouseEntered() {
                super.mouseEntered();
                setColor(Color.green);
            }

            @Override
            public void mouseLeft() {
                super.mouseLeft();
                setColor(Color.darkGray);
            }

            @Override
            public void update() {
                super.update();
                if(Input.isMouseDown()&&mouseInside){
                    setSelectedScene(new Scene());
                }
            }
        };
        sunBody = new PhysicsBody();
        sun.add(sunBody);
        sunBody.mass = 1000;
       // sunBody.mass = 1;




        Scene scene = new Scene(){
            @Override
            public void update() {
                super.update();
                for(Component comp1 : getComponents1()){
                    for(Component comp2 : getComponents1()){
                        if(comp1!=comp2)
                        {
                            try {
                                Planet planet = (Planet) comp1;
                                PhysicsBody planetBody1 = (PhysicsBody) planet.getChild(new PhysicsBody());

                                Planet planet2 = (Planet) comp2;
                                PhysicsBody planetBody2 = (PhysicsBody) planet2.getChild(new PhysicsBody());

                                double g = 6.67408*Math.pow(10,-7);
                                double fg = (planetBody1.mass*planetBody2.mass*g)/(planet.getPosition().getDistance(planet2.getPosition()))*100;

                                planetBody1.addForce(planet.getPosition().subtract(planet2.getPosition()).multiply(-fg));
                                planetBody2.addForce(planet2.getPosition().subtract(planet.getPosition()).multiply(-fg));
                            }catch (Exception e){}

                        }
                    }
                }
            }
        };

        scene.add(sun);
        //scene.add(new Planet(new Vector2(200,200),6,10));

        scene.add(new Planet(new Vector2(400,200),1,10));
        scene.add(new Planet(new Vector2(100,300),1,30));

        scene.getCamera().add(new CameraMovement());

        Debug.log(scene.getCamera().getChildren().toString());

        setSelectedScene(scene);

        start();

    }


    static class Planet extends GameObject{
        PhysicsBody body = new PhysicsBody();
        LinkedList<Vector2> tray = new LinkedList<>();
        int index = 0;
        public Planet(Vector2 startPos, float v,float mass){
            setPosition(startPos);
            localVertices = new Rect(20,20);
            body.mass = mass;
            body.setFriction(0);
            body.velocity = new Vector2(-0.5f*v,2);
            add(body);
            add(new Collider(new Rect(20,20)));
        }

        @Override
        public void update() {
            super.update();


            double g = 6.67408*Math.pow(10,-7);
            double fg = (sunBody.mass*body.mass*g)/(body.getPosition().getDistance(sun.getPosition()))*100;
            if(Input.isKeyDown(Keys.UPARROW)){
                fg = fg * 10f;
            }
            if(Input.isKeyDown(Keys.DOWNARROW)){
                fg = fg / 10f;
            }
            body.addForce(body.getPosition().subtract(sun.getPosition()).multiply(-fg));
            sunBody.addForce(sunBody.getPosition().subtract(body.getPosition()).multiply(-fg));


        }

        @Override
        public void render(Graphics2D g) {
            super.render(g);
            g.drawString(body.velocity.toString(),0,200);
            if (Input.isKeyDown(Keys.SPACE)){
                for (Vector2 t : tray){
                    g.fillOval((int) t.getX(), (int) t.getY(),5,5);
                }
                if(index%10==1)
                    tray.add(new Vector2(getPosition()));
                index++;
            }
            else{
                tray.clear();   
            }

        }
    }



}
