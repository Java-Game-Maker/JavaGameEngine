package javagameengine.ui;

import javagameengine.msc.Debug;

import java.awt.*;
import java.util.LinkedList;

public class Text extends UiElement{

    public String text = "Example text";

    public Text(String text) {
        this.text = text;
    }

    public Text() {
    }

    private LinkedList<String> getLines(){
        LinkedList<String> lines = new LinkedList<>();
        String line = "";
        int len = 0;
        for (String word:text.split(" ")){
            len += word.length()*10;
            if(len>scale.getX()){
                lines.add(line);
                line="";
                len = 0;
            }else{
                line+=word+" ";
            }
        }
        lines.add(line+" ");
        line=" ";
        len = 0;

        return lines;
    }

    @Override
    public void draw(Graphics g) {
        Color c = g.getColor();
        int y = 0;
        for (String line: getLines()){
            g.setColor(getColor());
            g.drawString(line, (int) getSpritePosition().getX()+10, (int) ((getSpritePosition().getY()+10)+(y*15)));
            y++;
        }
        g.setColor(c);
    }
}
