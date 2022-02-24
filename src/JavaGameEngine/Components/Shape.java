package JavaGameEngine.Components;

import JavaGameEngine.msc.Debug;
import JavaGameEngine.msc.Vector2;
import com.sun.corba.se.spi.ior.IdentifiableBase;

import javax.xml.bind.helpers.DefaultValidationEventHandler;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;

public class Shape extends Component{
    /*
        Shape should be able to rotate, scale, change position and return a polygon
        Game objects should have a shape component to draw
        And colliders should have a shape
        How to set shape
        set it as a list of vector2 points
        default shape is a rectangle
     */


    //this is the points to represent the shape
    private Vector2[] localPoints = new Vector2[]{new Vector2(0,0),new Vector2(1,0),new Vector2(0,1),new Vector2(1,1)};

    //this is the points that have a position in space
    //they have the shape of the localpoints but not the position
    private Vector2[] points = new Vector2[]{new Vector2(200,190),new Vector2(200,300),new Vector2(300,300),new Vector2(300,200)};

    Vector2[] vertices = new Vector2[]{new Vector2(0,0),new Vector2(10,0),new Vector2(0,10),new Vector2(10,10)};//this is the vertices representing the polygon (rectangle)

    public Shape(GameObject parent) {
        this.parent=parent;
        this.position = parent.getPosition();

    }
    public Shape() {
    }

    @Override
    public void setPosition(Vector2 position) {

        Debug.log(vertices [0]);

        for(int i = 0;i<vertices.length;i++){
            float deltaX = position.getX();
            float deltaY = position.getY();
            float x = deltaX;
            float y = deltaY;
            vertices[i] = (new Vector2(x,y));
        }
        this.position = position;
    }

    public void rotate(float angle) {
        this.buildPolygon((int)this.position.getX(), (int)this.position.getY(), (double)angle);
    }

    private void buildPolygon(int centerX, int centerY, double rotationAngle) throws IllegalArgumentException {

        if (false) {
            throw new IllegalArgumentException("The provided x points are not the same length as the provided y points.");
        } else {
            ArrayList<Point2D> list = new ArrayList();

            for(int i = 0; i < vertices.length; ++i) {
                list.add(new Point2D.Double((double)vertices[i].getX(), (double)vertices[i].getY()));
            }

            Point2D[] rotatedPoints = new Point2D[list.size()];
            AffineTransform transform = AffineTransform.getRotateInstance(Math.toRadians(rotationAngle), (double)centerX, (double)centerY);
            transform.transform((Point2D[])list.toArray(new Point2D[0]), 0, rotatedPoints, 0, rotatedPoints.length);
            int[] ixp = new int[list.size()];
            int[] iyp = new int[list.size()];

            for(int i = 0; i < ixp.length; ++i) {
                Vector2 point = new Vector2(0,0);
                point.setX((float) rotatedPoints[i].getX());
                point.setY((float) rotatedPoints[i].getY());
                vertices[i] = point;
            }

        }
    }

    public Polygon getPolygon(){
        int[] x = new int[vertices.length];
        int[] y = new int[vertices.length];

        for(int i = 0;i< vertices.length;i++){
           Vector2 point = vertices[i];
            x[i] = (int)point.getX();
            y[i] = (int)point.getY();
        }

        return new Polygon(x,y,vertices.length);
    }

    /*

    private boolean checkColl() {
        int i;
        for(i = 0; i < this.p2.npoints; ++i) {
            if (this.p2.contains(this.p1.xpoints[i], this.p1.ypoints[i])) {
                return true;
            }
        }

        for(i = 0; i < this.p1.npoints; ++i) {
            if (this.p1.contains(this.p2.xpoints[i], this.p2.ypoints[i])) {
                return true;
            }
        }

        return false;
    }
    */


}
