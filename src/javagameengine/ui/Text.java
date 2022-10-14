package javagameengine.ui;

import javagameengine.msc.Debug;

import java.awt.*;

public class Text extends UiElement{

    private String text = "My Text";
    private Font font = new Font("TimesRoman", Font.PLAIN, 16);
    public Text(String text){
        this.text = text;
        setColor(Color.WHITE);
        setLayer(0);
    }

    public float getLength(){
        return text.length()*font.getSize();
    }
    @Override
    public void render(Graphics2D g) {
        super.render(g);
        Color c = g.getColor();
        g.setColor(getColor());
        g.drawString(text,getPosition().getX()-getLength()/4,getPosition().getY());
        g.setColor(c);
    }
}