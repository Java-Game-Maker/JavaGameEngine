package javagameengine.ui;

import javagameengine.components.shapes.Rect;
import javagameengine.msc.Debug;
import javagameengine.msc.Padding;
import javagameengine.msc.Vector2;

import java.awt.*;

public class Panel extends UiFillElement{

    Layout layout = new Row(this);
    public Panel(){
        setColor(new Color(126, 126, 126));
        setScale(new Vector2(100,100));
    }
    public Panel(Layout layout){
        setColor(new Color(126, 126, 126));
        setScale(new Vector2(100,100));
        this.layout = layout;
        layout.parent = this;
    }

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    @Override
    public void update() {
        super.update();
        layout.orient(getChildren());
    }
}