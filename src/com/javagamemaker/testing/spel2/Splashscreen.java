package com.javagamemaker.testing.spel2;

import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.Scene;
import com.javagamemaker.javagameengine.components.Sprite;
import com.javagamemaker.javagameengine.components.lights.LightManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Splashscreen extends Scene {

    JPanel p = new JPanel();
    public Splashscreen(){
        LightManager.opacity = 0;
        //p.setBackground(new Color(50,50,50));
        JLabel title =new JLabel("Cookie Jumper 2");
        title.setFont(new Font("Verdana",Font.BOLD,32));
        title.setLocation(0,300);
        title.setSize(500,100);

        JButton start = new JButton("Start");
        start.setSize(500,100);
        start.setLocation(0,500);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JavaGameEngine.setSelectedScene(new Level1());
            }
        });
        add(start);
        add(title);

        // try {
        //     UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
    }

    @Override
    public void update() {
        super.update();
    }
}
