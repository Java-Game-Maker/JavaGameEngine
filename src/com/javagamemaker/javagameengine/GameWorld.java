package com.javagamemaker.javagameengine;

import com.javagamemaker.javagameengine.input.Input;
import com.javagamemaker.javagameengine.msc.Vector2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameWorld extends JPanel {
    
    public GameWorld(){
        setLayout(new GridLayout(0, 1));
        setBackground(Color.cyan);
        setFocusable(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                Input.addKey(e);
            }
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                Input.removeKey(e);
            }
        });

        /*
          mouse input
         */
        MouseAdapter mouseAdapter = new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                Input.addMouseButton(e);
                Input.setMouseEvent(e);

            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                Input.removeMouseButton(e);
                Input.setMouseEvent(e);

            }
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                Input.setMousePositionOnCanvas(new Vector2((float) e.getPoint().getX(), (float) e.getPoint().getY()));
                Input.setMouseEvent(e);

            }
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                Input.setMousePositionOnCanvas(new Vector2((float) e.getPoint().getX(), (float) e.getPoint().getY()));
                Input.setMouseEvent(e);

            }
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                Input.setScrollValue((float) e.getPreciseWheelRotation());
                Input.setMouseEvent(e);
            }

        };
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
        addMouseWheelListener(mouseAdapter);

    }


}
