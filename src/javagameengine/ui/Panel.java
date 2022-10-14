package javagameengine.ui;

import javagameengine.msc.Debug;
import javagameengine.msc.Vector2;
import javagameengine.ui.UiFillElement;
import javagameengine.ui.layout.Layout;
import javagameengine.ui.layout.Row;
import testing.Main;

import java.awt.*;

public class Panel extends UiFillElement {

    Layout layout = new Row(this);
    public Panel(){
        setColor(new Color(126, 126, 126));
        setScale(new Vector2(100,100));
    }
    public Panel(Layout layout){
        setColor(new Color(126, 126, 126));
        setScale(new Vector2(100,100));
        this.layout = layout;
        layout.setParent(this);
    }

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    @Override
    public void update() {
        layout.orient(getChildren());
        super.update();
        Debug.log((Main.getSelectedScene().hasA!=null?Main.getSelectedScene().hasA.toString():""));
    }
}