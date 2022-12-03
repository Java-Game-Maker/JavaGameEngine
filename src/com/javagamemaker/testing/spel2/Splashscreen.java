package com.javagamemaker.testing.spel2;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.Scene;
import com.javagamemaker.javagameengine.components.Sprite;
import com.javagamemaker.javagameengine.components.lights.LightManager;
import com.javagamemaker.javagameengine.input.Input;
import com.javagamemaker.javagameengine.input.Keys;
import com.javagamemaker.javagameengine.msc.Debug;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Splashscreen extends Scene {

    JLabel title =new JLabel("Cookie Jumper 2",SwingConstants.CENTER);
    JButton start = new JButton("Start");
    public Splashscreen(){
        LightManager.opacity = 0;
        setBackground(new Color(50,50,50));

       try {
           UIManager.setLookAndFeel(new FlatDarculaLaf());
       } catch (Exception e) {
           e.printStackTrace();
       }
       title =new JLabel("Cookie Jumper 2",SwingConstants.CENTER);
         start = new JButton("Start");
        title.setFont(new Font("Verdana",Font.BOLD,32));
        title.setLocation(0,300);
        title.setSize(500,100);

        start.setSize(300,50);
        start.setLocation(0,500);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JavaGameEngine.setSelectedScene(new Level1());
            }
        });
        add(start);
        add(title);
    }

    @Override
    public void update() {
        super.update();
        if(Input.isKeyDown(Keys.SPACE))
            JavaGameEngine.setSelectedScene(new Level1());

        int centerx = (int) (JavaGameEngine.getWindowSize().getX()/2);
        title.setLocation(centerx-250,title.getY());
        start.setLocation(centerx-150,start.getY());
    }
}
