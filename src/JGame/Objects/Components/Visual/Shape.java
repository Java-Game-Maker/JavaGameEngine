package JGame.Objects.Components.Visual;

import JGame.Display.GameWorld;
import JGame.Msc.Vector2;
import JGame.Objects.Components.Component;

import java.awt.*;

public class Shape extends Polygon {

    /*
     * All points when getting set should be set in local space
     *
     * */
    private Vector2 scale=new Vector2(0,0);
    private Vector2 position = new Vector2(0,0);
    public Vector2 center = new Vector2(0,0);
    public Shape() {
    }

    public Shape(int[] xpoints, int[] ypoints, int npoints) {
        super(xpoints, ypoints, npoints);
    }

    public Vector2 getScale() {
        return scale;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setScale(Vector2 scale) {
        int i = 0;
        for(int x : xpoints) {
            xpoints[i] = (int) ((x-getScale().getX())*scale.getX());
            i++;
        }
        i = 0;
        for(int y : ypoints) {
            ypoints[i] = (int) ((y-getScale().getY())*scale.getY());
            i++;
        }
        this.scale = scale;
    }

    /**
     * @return returns the local x coordinate
     * */
    public int getX(int getI)
    {
        return (int) (xpoints[getI]-this.position.getX());
    }
    /**
     * @return returns the local y coordinate
     * */
    public int getY(int getI)
    {
        return (int) (ypoints[getI]-this.position.getY());
    }
    public void setX(int index,int x) {
        xpoints[index] = (int) (x + this.position.getX());
    }

    public void setY(int index,int y) {
        ypoints[index] = (int) (y + this.position.getY());
    }

    public void scaleTest(Vector2 scale)
    {/*
        int maxX=0;
        int maxY= 0;
        int minX=0;
        int minY=0;
        for(int i = 0;i<npoints;i++)
        {
            int x = xpoints[i];
            int y = ypoints[i];
            maxX = Math.max(x,maxX);
            maxY = Math.max(y,maxY);
        }
        System.out.println("minx "+maxX+" miny "+maxY);
        int xD =  maxX -minX; //no pun intended
        int yD =  maxY -minY; //no pun intended
        System.out.println("xd "+xD);

        for(int i = 0;i<npoints;i++) {
            System.out.println("before "+new Vector2(xpoints[i],ypoints[i]));

            if(xpoints[i]<(xD)) {
                //xpoints[i] -= (int) scale.getX();
            }
            else {
                xpoints[i] += (int) scale.getX()*2;
            }
            if(ypoints[i]<yD) {
                //ypoints[i] -= (int) scale.getY();
            }
            else {
                ypoints[i] += (int) scale.getY()*2;
            }
            System.out.println("after "+new Vector2(xpoints[i],ypoints[i]));
        }*/

        int maxX=0;
        int maxY= 0;
        for(int i = 0;i<npoints;i++) {
            int x = getX(i);
            int y = getY(i);
            maxX = Math.max(x,maxX);
            maxY = Math.max(y,maxY);
        }

        int minX=maxX;
        int minY=maxY;
        for(int i = 0;i<npoints;i++) {
            int x = getX(i);
            int y = getY(i);
            minX = Math.min(x,minX);
            minY = Math.min(y,minY);

        }
        int xD =  maxX - minX; //no pun intended
        int yD =  maxY + minY; //no pun intended
        //  System.out.println(maxX);
        //  System.out.println(minX);
        //  System.out.println(xD);
        center = new Vector2((xD/2),(yD/2));

        for(int i = 0;i<npoints;i++) {
            int x = getX(i);
            int y = getY(i);
            //System.out.println("x cord: "+npoints[]);
            Vector2 point = new Vector2(x,y);
            //Vector2 newPoint = point.add(point.getDirection(Math.round(point.LookAt(center)))).multiply(scale);
            // System.out.println(scale);
            Vector2 newPoint = point.add(new Vector2(point.getX()-(center.getX()-this.position.getX()),point.getY()+(center.getY()-this.position.getY())).multiply(scale));
            setX(i, (int) newPoint.getX());
            setY(i, (int) newPoint.getY());
        }
    }


    public void setPosition(Vector2 position) {
        int i = 0;
        for(int x : xpoints) {
            xpoints[i] = (int) ((x- this.position.getX())+position.getX());
            i++;
        }
        i = 0;
        for(int y : ypoints) {
            ypoints[i] = (int) ( (y - this.position.getY()) +position.getY());
            i++;
        }
        center = new Vector2(center.getX()+position.getX(),center.getY()+position.getY());
        this.position = position;
    }

    @Override
    public void translate(int deltaX, int deltaY) {
        super.translate(deltaX, deltaY);
        System.out.println("tr");
    }
}
