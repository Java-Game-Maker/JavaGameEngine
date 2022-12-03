package com.javagamemaker.testing.spel2;

import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.Scene;
import com.javagamemaker.javagameengine.msc.Debug;
import com.javagamemaker.javagameengine.msc.Vector2;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.InputStream;

/**TODO
 * Spawn coins in chunks
 * add a enemy
 * upgrade graphics (cookie, coin and background)
 * sound
 */
public class Main extends JavaGameEngine {
    public static Player player;
    public static void main(String[] args){
        player = new Player();
        size = new Vector2(600,1000);
        player.setPosition(new Vector2(0,-200));

        setSelectedScene(new Level1());
        start();
    }

    public static synchronized void playSound(final String url) {
        Debug.log("asd");
        new Thread(new Runnable() {
        // The wrapper thread is unnecessary, unless it blocks on the
        // Clip finishing; see comments.
          public void run() {
            try {
              Clip clip = AudioSystem.getClip();
              AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                Main.class.getResourceAsStream(url));
              clip.open(inputStream);
              clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
          }
        }).start();
    }

}
