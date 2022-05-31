package JavaGameEngine.Components.Ui;

import JavaGameEngine.Components.Component;
import JavaGameEngine.msc.Debug;

import java.awt.*;

public class Label extends UiComponent {

    private String value;
    public Label(String value){this.value=value;}
    public Label(){}

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.drawString(getValue(), (int) getPosition().getX(), (int) getPosition().getY());
    }
}
