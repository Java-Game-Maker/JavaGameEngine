package Testing;

import javagameengine.components.Audio;
import javagameengine.components.colliders.SquareCollider;
import javagameengine.components.Component;
import javagameengine.components.GameObject;
import javagameengine.components.physics.PhysicsBody;
import javagameengine.components.sprites.Sprite;
import javagameengine.msc.Vector2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;

public class Player extends GameObject {
    Label speed = new Label();
    public Sprite sprite = new Sprite();//,new Rectangle(0,250,250,250)
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
        addChild(new PlayerMovement());
        Audio audio = new Audio("1.wav");

        try {
            audio.playSound();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        addChild(audio);
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
}
