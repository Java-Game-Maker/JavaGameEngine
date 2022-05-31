package Testing;

import JavaGameEngine.Backend.Input.Input;
import JavaGameEngine.Backend.Input.Keys;
import JavaGameEngine.Backend.UpdateThread;
import JavaGameEngine.Components.Collider.SquareCollider;
import JavaGameEngine.Components.Component;
import JavaGameEngine.Components.GameObject;
import JavaGameEngine.Components.Physics.PhysicsBody;
import JavaGameEngine.Components.Sprite.Sprite;
import JavaGameEngine.Components.Ui.Label;
import JavaGameEngine.JavaGameEngine;
import JavaGameEngine.msc.Debug;
import JavaGameEngine.msc.Vector2;

import java.awt.*;
import java.util.Observer;

public class Player extends GameObject {
    Label speed = new Label();
    Sprite sprite = new Sprite();//,new Rectangle(0,250,250,250)
    PhysicsBody physicsBody = new PhysicsBody();
    public Player(Vector2 pos) {

        Rectangle[] right = new Rectangle[4];
        for(int i = 0;i<4;i++){
            right[i] = new Rectangle(i*32,2*48,32,48);
        }
        Rectangle[] left = new Rectangle[4];
        for(int i = 0;i<4;i++){
            left[i] = new Rectangle(i*32,1*48,32,48);

        }
        Rectangle[] defaultAnimation = new Rectangle[4];
        for(int i = 0;i<4;i++){
            defaultAnimation[i] = new Rectangle(0*32,0*48,32,48);

        }

        sprite.loadAnimation(right,"/2.png");
        sprite.loadAnimation(left,"/2.png");
        sprite.loadAnimation(defaultAnimation,"/2.png");

        sprite.setTimer(50);
        sprite.setLocalPosition(new Vector2(0,3));
        addChild(sprite);
        setPosition(pos);
        setScale(new Vector2(100,100));

        addChild(physicsBody);
        setTag("player");
        SquareCollider s = new SquareCollider();
        s.setLocalScale(new Vector2(-20,0));
        addChild(s);
    }
    @Override
    public void onTrigger(Component c) {
        super.onTrigger(c);

        if(c.getTag().equals("Coin")){
            switch (Main.level){
                case 1:
                    Main.setSelectedScene(new Main.Level2());
                    break;
                case 2:
                    Main.setSelectedScene(new Main.Level3());
                    break;
                case 3:
                    Main.setSelectedScene(new Main.End());
                    break;
            }

        }
    }
    @Override
    public void update() {
        super.update();

        if(Input.isKeyDown((Keys.D))) {
            movePosition(getPosition().add(Vector2.right.multiply(1.2f)));
            sprite.animationIndex = 0;
        }
        else if(Input.isKeyDown((Keys.A))){
            movePosition(getPosition().add(Vector2.left.multiply(1.2f)));
            sprite.animationIndex = 1;

            //UpdateThread.camera.setX(UpdateThread.camera.getX()-2);
        }
        else {
            sprite.animationIndex =2;
        }
        if(Input.isKeyPressed(Keys.SPACE)){
            physicsBody.addForce(Vector2.up,120);
        }
        UpdateThread.camera.setPosition(getPosition().subtract(Main.getWindowSize().devide(2)));
    }
}
