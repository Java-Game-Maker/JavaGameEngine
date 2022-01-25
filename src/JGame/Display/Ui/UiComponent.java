package JGame.Display.Ui;

import JGame.Objects.Components.GameObject;

public class UiComponent extends GameObject {
    private String text;
    private UiEvent event;

    public UiEvent getEvent() {
        return event;
    }

    public void addNewEventListener(UiEvent event) {
        this.event = event;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
