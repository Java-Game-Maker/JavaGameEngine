package javagameengine.components.Animation;

import javagameengine.msc.Vector2;

public class AnimationPoint extends Vector2 {

    private int time = 0;

    public AnimationPoint(float x, float y, int i) {
        super(x, y);
        this.time = i;
    }

    public AnimationPoint(Vector2 vector2) {
        super(vector2);
    }

    public AnimationPoint(Vector2 vector2, int time) {
        super(vector2);
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
