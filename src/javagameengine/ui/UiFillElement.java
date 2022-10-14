package javagameengine.ui;

import javagameengine.components.Component;
import javagameengine.input.Input;

import java.awt.*;

public class UiFillElement extends UiElement {
    @Override
    public void render(Graphics2D g) {
        Color c = g.getColor();
        g.setColor(getColor());
        g.fillPolygon(getPolygon());
        super.render(g);
        g.setColor(c);
    }
}