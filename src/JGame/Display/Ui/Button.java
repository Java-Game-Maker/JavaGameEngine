package JGame.Display.Ui;
import JGame.Main;
import JGame.Msc.Input.Input;
import JGame.Msc.Vector2;
import JGame.Objects.Components.GameObject;
public class Button extends UiComponent{

    public Button() {
        setScale(new Vector2(300,100));
        setPosition(new Vector2(300,300));
        setVisible(false);
        setText("Press me");
    }

    @Override
    public void Update() {
        super.Update();
        if(Input.getMouseButtonDowns().size()>0&&Input.getMousePosition().getDistance(this.getPosition())<100)
        {
            if(getEvent()!=null)
            {
                getEvent().mouseIsDown(Input.getMouseButtonDowns().get(0));
            }
        }
    }
}
