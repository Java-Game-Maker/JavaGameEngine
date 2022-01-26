package JavaGameEngine.Objects.Components.Visual;

import JavaGameEngine.Msc.Vector2;
import JavaGameEngine.Objects.Components.Component;
import JavaGameEngine.Objects.Components.GameObject;

import java.awt.*;

public class Shape_idk extends Polygon {

    /*
     * All points when getting set should be set in local space
     *
     * */
    private Vector2 scale = new Vector2(0,0);
    private Vector2 position = new Vector2(0,0);
    public Vector2 center = new Vector2(0,0);

    private Component parent;

    public Shape_idk() {
    }

    public Shape_idk(int[] xpoints, int[] ypoints, int npoints, Component parent) {
        super(xpoints, ypoints, npoints);
        this.parent = (GameObject) parent;
    }

    public Shape_idk(Shape_idk square, Component parent) {
        super(square.xpoints,square.ypoints,square.npoints);
        this.parent = (GameObject) parent;
    }

    public Component getParent() {
        return parent;
    }

    public void setParent(GameObject parent) {
        this.parent = parent;
    }

    public Vector2 getScale() {
        return scale;
    }

    public Vector2 getPosition() {
        return position;
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

    public void setScale(Vector2 scale)
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
        System.out.println("set scale "+scale);

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
        System.out.println("set pos "+position);

        int i = 0;
        for(int x : xpoints) {
            //setX(i, (int) ((int) position.getX()));//
            xpoints[i] = (int) ((x - this.position.getX())+(position.getX()-(getParent().getScale().getX()/2)));
            i++;
        }
        i = 0;
        for(int y : ypoints) {
            //setY(i, (int) ((int) position.getY())); //
            ypoints[i] = (int) ( (y - this.position.getY()) + (position.getY()-(getParent().getScale().getY()/2)));
            i++;
        }
        center = new Vector2((center.getX()-this.position.getX())+position.getX(),(center.getY()-this.position.getY())+position.getY());
        this.position = position.subtract(parent.getScale());
    }

    public void Update()
    {
       // setScale(getParent().getScale());
      //  setPosition(getParent().getPosition());
    }

    @Override
    public void translate(int deltaX, int deltaY) {
        super.translate(deltaX, deltaY);
        System.out.println("tr");
    }
}
