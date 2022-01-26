package JavaGameEngine.Objects.Components.Visual;

import JavaGameEngine.Msc.Vector2;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;

public class Shape_s {

    int[] localXs = new int[]{0,20,20,0};
    int[] localYs = new int[]{20,20,0,0};

    private Polygon polygon = new Polygon();
    //private Polygon polygon = new Polygon(new int[]{0,2,2,0},new int[]{2,2,0,0},4);
    private Vector2 scale = new Vector2(100,100);
    public Vector2 position = new Vector2(100,100);


    public Polygon getPolygon() {
        int[] y = localYs.clone();
        int[] x = localXs.clone();

        for(int i = 0;i<y.length;i++)
        {
            x[i] = (int) ((int) ((int) x[i]+position.getX())*5);
            y[i] = (int) ((int) ((int) y[i]+position.getY())*5);
            System.out.println(new Vector2(x[i],y[i]));
        }
        polygon.npoints =4;
        polygon.xpoints = x;
        polygon.ypoints = y;
        return polygon;
    }

    public void setPolygon(Polygon polygon) {
        this.polygon = polygon;
    }

    public Vector2 getCenter()
    {
        int maxX=0;
        int maxY= 0;
        for(int i = 0;i<polygon.npoints;i++) {
            int x = polygon.xpoints[i];
            int y = polygon.ypoints[i];
            maxX = Math.max(x,maxX);
            maxY = Math.max(y,maxY);
        }

        int minX=maxX;
        int minY=maxY;
        for(int i = 0;i<polygon.npoints;i++) {
            int x = polygon.xpoints[i];
            int y = polygon.ypoints[i];
            minX = Math.min(x,minX);
            minY = Math.min(y,minY);

        }
        int xD =  maxX - minX; //no pun intended
        int yD =  maxY + minY;

        return new Vector2((xD/2),(yD/2));
    }

    public void rotate(double rotationAngle) {

        // copy the arrays so that we dont manipulate the originals, that way we can
        // reuse them if necessary

        int[] xp=localXs;
        int[] yp=localYs;

        int[] xpoints = Arrays.copyOf(xp,xp.length);
        int[] ypoints = Arrays.copyOf(yp,yp.length);
        if(xpoints.length != ypoints.length){
            throw new IllegalArgumentException("The provided x points are not the same length as the provided y points.");
        }

        // create a list of Point2D pairs
        ArrayList<Point2D> list = new ArrayList();
        for(int i = 0; i < ypoints.length; i++){
            list.add(new Point2D.Double(xpoints[i], ypoints[i]));
        }

        // create an array which will hold the rotated points
        Point2D[] rotatedPoints = new Point2D[list.size()];

        Vector2 c = position;

        AffineTransform transform = AffineTransform.getRotateInstance(Math.toRadians(rotationAngle), c.getX(), c.getY());
        transform.transform(list.toArray(new Point2D[0]), 0, rotatedPoints, 0, rotatedPoints.length);

        int[] ixp = new int[list.size()];
        int[] iyp = new int[list.size()];
        for(int i = 0; i < ixp.length; i++){
            ixp[i] = (int)rotatedPoints[i].getX();
            iyp[i] = (int)rotatedPoints[i].getY();
        }
        localXs = ixp;
        localYs = iyp;
    }

    public void scale(float x, float y){

        int[] xpoints = Arrays.copyOf(polygon.xpoints,polygon.xpoints.length);
        int[] ypoints = Arrays.copyOf(polygon.ypoints,polygon.ypoints.length);

        if(xpoints.length != ypoints.length){
            throw new IllegalArgumentException("The provided x points are not the same length as the provided y points.");
        }

        // create a list of Point2D pairs
        ArrayList<Point2D> list = new ArrayList();
        for(int i = 0; i < ypoints.length; i++){
            list.add(new Point2D.Double(xpoints[i], ypoints[i]));
        }

        // create an array which will hold the rotated points
        Point2D[] scaledPoints = new Point2D[list.size()];

        AffineTransform affineTransform = AffineTransform.getScaleInstance(x,y);
        affineTransform.transform(list.toArray(new Point2D[0]), 0, scaledPoints, 0, scaledPoints.length);
        // build the polygon from the rotated points and return it
        int[] ixp = new int[list.size()];
        int[] iyp = new int[list.size()];
        for(int i = 0; i < ixp.length; i++){
            ixp[i] = (int)scaledPoints[i].getX();
            iyp[i] = (int)scaledPoints[i].getY();
        }
        localXs = ixp;
        localYs = iyp;
    }

    public void setPosition(Vector2 position){
        //this.position = position;
    }


}
