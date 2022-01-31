package JavaGameEngine.Components;

import JavaGameEngine.msc.Debug;
import JavaGameEngine.msc.Vector2;

import java.awt.*;

public class Shape extends Component{

    //this is the points to represent the shape
    private Vector2[] localPoints = new Vector2[]{new Vector2(0,0),new Vector2(1,0),new Vector2(0,1),new Vector2(1,1)};
    //this is the points that have a position in space
    //they have the shape of the localpoints but not the position
    private Vector2[] points = new Vector2[]{new Vector2(0,0),new Vector2(1,0),new Vector2(0,1),new Vector2(1,1)};



    @Override
    public void setPosition(Vector2 position) {
        this.scale(new Vector2(100,100));
        for(int i = 0;i< points.length;i++){
            points[i] = points[i].subtract(this.position).add(position);
            Debug.log(points[i].toString());
        }
        this.position = position;

    }
    void scale(Vector2 scale){
        for(int i = 0;i< points.length;i++){
            points[i] = points[i].subtract(this.scale).add(scale);
            Debug.log(points[i].toString());
        }
        this.scale = position;
    }
    void rotate(double angle){
        Vector2[] newPoints = new Vector2[points.length];
        int i = 0;
        for(Vector2 p:points){
            double sinAng = Math.sin((angle / 180) * Math.PI);
            double cosAng = Math.cos((angle / 180) * Math.PI);
            double dx = p.getX();
            double dy = p.getY();
            Vector2 pr = new Vector2(0,0);
            pr.setX((int)(dx*cosAng-dy*sinAng));
            pr.setY((int) (dx*sinAng+dy*cosAng));
            newPoints[i] = pr;
            i++;
        }

    }

    public Polygon getPolygon() {
        int pn = points.length;
        int[] x = new int[pn];
        int[] y = new int[pn];
        int i = 0;
        for(Vector2 p:points){
            x[i] = (int) p.getX();
            y[i] = (int) p.getY();
            i++;
        }
        return new Polygon(x,y,pn);
    }
}
