package com.javagamemaker.testing.spel2;

import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.components.lights.LightManager;
import com.javagamemaker.javagameengine.msc.Vector2;

import javax.sound.sampled.*;
import java.io.IOException;
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
        //Debug.showWhere=true;
        player = new Player();
        size = new Vector2(600,1000);
        player.setPosition(new Vector2(0,-200));

        LightManager.opacity = 0.99f;
        setSelectedScene(new Level1());
        start();
    }


    public static void playSound(InputStream clipFile) throws IOException,
            UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    class AudioListener implements LineListener {
                        private boolean done = false;

                        @Override
                        public synchronized void update(LineEvent event) {
                            LineEvent.Type eventType = event.getType();
                            if (eventType == LineEvent.Type.STOP || eventType == LineEvent.Type.CLOSE) {
                                done = true;
                                notifyAll();
                            }
                        }

                        public synchronized void waitUntilDone() throws InterruptedException {
                            while (!done) {
                                wait();
                            }
                        }
                    }
                    AudioListener listener = new AudioListener();
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(clipFile);
                    try {
                        Clip clip = AudioSystem.getClip();
                        clip.addLineListener(listener);
                        clip.open(audioInputStream);
                        try {
                            clip.start();
                            listener.waitUntilDone();
                        } finally {
                            clip.close();
                        }
                    } finally {
                        audioInputStream.close();
                    }
                }catch (Exception e){ e.printStackTrace(); }
            }
        }).start();
    }

}
