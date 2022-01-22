package Testing;

import Main.Display.Map;
import Main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GameScreen extends JPanel {

    public GameScreen() {
        JPanel s = this;
        add(new JLabel("HELLO BOYS"));
        Button b = new Button("Start");
        b.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.isPlaying=true;
                Map.removeJComponent(s);
            }
        });
        add(b);
        setBackground(new Color(0,0,0,0));
    }
}
