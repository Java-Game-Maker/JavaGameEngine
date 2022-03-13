package JavaGameEngine.Components;

import java.awt.Polygon;
import java.util.ArrayList;

import JavaGameEngine.msc.Vector2;

public class Shape extends Component{
   
    ArrayList<Vector2> defualtShape = new ArrayList<>();
    ArrayList<Vector2> shape = new ArrayList<>();

    public void Rotate(){

    }

    public void Scale(){

    }
    /**
     *  Returns a java.awt.Polygon object (to draw)
     */
    public Polygon getShape(){
        int[] x = new int[shape.size()];
        int[] y = new int[shape.size()];
        int i = 0; 
        for(Vector2 point : shape){
            x[i] = (int) point.getX();
            y[i] = (int) point.getY();
            i++;
        }
        return new Polygon(x,y,shape.size());
    }


}

