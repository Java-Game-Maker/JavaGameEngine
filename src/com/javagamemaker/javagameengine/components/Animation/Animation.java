package com.javagamemaker.javagameengine.components.Animation;

import com.javagamemaker.javagameengine.components.Component;
import com.javagamemaker.javagameengine.msc.Vector2;

import java.util.LinkedList;

public class Animation extends Component {

    boolean repeat = false;                 // repeat or not
    int time   =     0;                 // how long the animation is
    LinkedList<AnimationPoint> selectedPoints = new LinkedList<>();    // the points to move to
    LinkedList<AnimationPoint> inBetweenPoints = new LinkedList<>();    // the points to move to

    int pointIndex = 0;
    int inBetweenIndex = 0;

    public Animation(LinkedList<AnimationPoint> selectedPoints,int time){
        this.selectedPoints = selectedPoints;
        this.time = time;
    }

    public Animation() {
    }
    private boolean idk = false;
    /**
     * Here we se the previous point
     */
    public Vector2 getPoint(){
        //if(selectedPoints.size()>0){

        if(pointIndex >= selectedPoints.size()-1) pointIndex = 0;

        if(idk){
            idk = false;
            pointIndex++;
            return selectedPoints.get(pointIndex);
        }

        if(inBetweenPoints.size()>0){
            if(inBetweenPoints.size()==1){
                idk = true;
            }
            return inBetweenPoints.pop();
        }

        if(pointIndex + 1 < selectedPoints.size() ){
           // if delta time is bigger then
            int deltaTime = (selectedPoints.get(pointIndex+1).getTime() - selectedPoints.get(pointIndex).getTime() );
            if(deltaTime > 1 && inBetweenPoints.size() <= 0 ){
                //distance for each in between point
                Vector2 distance = selectedPoints.get(pointIndex+1).subtract(selectedPoints.get(pointIndex)).divide(deltaTime);
                for(int i = 0;i<deltaTime*10;i++){
                    AnimationPoint point = new AnimationPoint(selectedPoints.get(pointIndex).add(distance.divide(10).multiply ((i+1))));
                    point.setTime(selectedPoints.get(pointIndex).getTime()+i);
                    inBetweenPoints.add(point);
                }
                return inBetweenPoints.pop();

           }else{
                pointIndex ++;
                return selectedPoints.get(pointIndex-1);
            }
       }
        pointIndex ++;
        return selectedPoints.get(pointIndex-1);

    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public LinkedList<AnimationPoint> getSelectedPoints() {
        return selectedPoints;
    }

    public void setSelectedPoints(LinkedList<AnimationPoint> selectedPoints) {
        this.selectedPoints = selectedPoints;
    }

    public LinkedList<AnimationPoint> getInBetweenPoints() {
        return inBetweenPoints;
    }

    public void setInBetweenPoints(LinkedList<AnimationPoint> inBetweenPoints) {
        this.inBetweenPoints = inBetweenPoints;
    }

    public int getPointIndex() {
        return pointIndex;
    }

    public void setPointIndex(int pointIndex) {
        this.pointIndex = pointIndex;
    }

    public int getInBetweenIndex() {
        return inBetweenIndex;
    }

    public void setInBetweenIndex(int inBetweenIndex) {
        this.inBetweenIndex = inBetweenIndex;
    }
}
