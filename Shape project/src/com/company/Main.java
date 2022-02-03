package com.company;

import JavaGameEngine.Backend.ComponentHandler;
import JavaGameEngine.Backend.Input.Input;
import JavaGameEngine.Backend.Input.Keys;
import JavaGameEngine.Components.GameObject;
import JavaGameEngine.JavaGameEngine;
import JavaGameEngine.msc.Debug;
import JavaGameEngine.msc.Vector2;

import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends JavaGameEngine{

    public static void main(String[] args) {

        init();

        ComponentHandler.addObject(new object());

        start();

    }

    public static class object extends GameObject{

        Polygon p1 = new Polygon(new int[]{100,100,0,0},new int[]{0,100,100,0}, 4);
        Polygon p2 = new Polygon(new int[]{100,300,300,100},new int[]{100,100,300,300}, 4);
        Vector2 position = new Vector2(200,200);
        public object(){
            setScale(new Vector2(0,0));
        }

        @Override
        public void draw(Graphics g) {
            super.draw(g);

            g.drawPolygon(p1);
            g.drawPolygon(p2);
            g.fillOval(200,200,10,10);
        }

        private void rotate(float angle){
            p2 = buildPolygon((int)position.getX(),(int)position.getY(), p2.xpoints, p2.ypoints,angle);
            //p2.translate(10,10);
        }
        private Polygon buildPolygon(int centerX, int centerY, int[] xp, int[] yp, double rotationAngle) throws IllegalArgumentException {
            // copy the arrays so that we dont manipulate the originals, that way we can
            // reuse them if necessary
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

            // rotate the points
            AffineTransform transform = AffineTransform.getRotateInstance(Math.toRadians(rotationAngle), centerX, centerY);
            transform.transform(list.toArray(new Point2D[0]), 0, rotatedPoints, 0, rotatedPoints.length);

            // build the polygon from the rotated points and return it
            int[] ixp = new int[list.size()];
            int[] iyp = new int[list.size()];
            for(int i = 0; i < ixp.length; i++){
                ixp[i] = (int)rotatedPoints[i].getX();
                iyp[i] = (int)rotatedPoints[i].getY();
            }
            return new Polygon(ixp, iyp, ixp.length);
        }


        @Override
        public void update() {
            super.update();

            //setPos(getPosition().add(Vector2.right));

            if(Input.isKeyPressed(Keys.SPACE)){
                rotate(45);
            }


        }

        private void setPos(Vector2 pos){
            int[] x = new int[p2.npoints];
            int[] y = new int[p2.npoints];

            for(int i = 0;i<p2.npoints;i++){
                x[i] = (int) ((p2.xpoints[i]-this.position.getX()/2)+pos.getX());
                y[i] = (int) ((p2.ypoints[i]-this.position.getY()/2)+pos.getY());
            }
            p2.xpoints = x;
            p2.ypoints = y;
            this.position = pos;

        }

        private boolean checkColl(){

            for(int i = 0;i<p2.npoints;i++){
                if(p2.contains(p1.xpoints[i],p1.ypoints[i])){
                    return true;
                }
            }
            for(int i = 0;i<p1.npoints;i++){
                if(p1.contains(p2.xpoints[i],p2.ypoints[i])){
                    return true;
                }
            }
            return false;
        }

    }

}
