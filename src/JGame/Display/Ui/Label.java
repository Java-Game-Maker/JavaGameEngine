package JGame.Display.Ui;

import JGame.Msc.Vector2;

public class Label extends UiComponent{
    public Label() {
        setScale(new Vector2(300,100));
        setPosition(new Vector2(300,300));
        setText("New label");
    }
}
