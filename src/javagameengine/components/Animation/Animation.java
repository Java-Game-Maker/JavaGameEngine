package javagameengine.components.Animation;

import javagameengine.components.Component;
import javagameengine.msc.Vector2;

import java.util.LinkedList;

public class Animation extends Component {

    boolean             repeat = false;                 // repeat or not
    int                 time   =     0;                 // how long the animation is
    LinkedList<Vector2> points = new LinkedList<>();    // the points to move to
    LinkedList<Vector2> selectedPoints = new LinkedList<>();    // the points to move to

    public Animation(LinkedList<Vector2> selectedPoints,int time){
        this.selectedPoints = selectedPoints;
        this.time = time;
    }
    /**
     * This function generates all the points between the selected
     * points to create a smooth transition
     */
    public void generate(){

    }

}
