package JavaGameEngine.Objects.Components.Visual;

import JavaGameEngine.Msc.Vector2;

import java.awt.*;

public class Shape {

    Vector2[] localVertices = new Vector2[]{new Vector2(0,0),new Vector2(100,0),new Vector2(100,100),new Vector2(0,100)};
    Vector2[] worldVertices = new Vector2[]{new Vector2(0,0),new Vector2(1,0),new Vector2(1,1),new Vector2(0,1)};
    public Vector2 origin = new Vector2(0,0);

    public void setPosition(Vector2 position) {
        //200,200
        origin = position;
        System.out.println(origin);
        worldVertices = localVertices.clone();
        int i = 0;
        for(Vector2 vertice : localVertices)
        {
            worldVertices[i] = vertice.add(position);
            i++;
        }
    }

    public void Scale(float toScale) {
        Vector2[] temp = localVertices.clone();

        float val = toScale;
        Vector2[] matrix = new Vector2[]{new Vector2(0,0),new Vector2(val,0),new Vector2(val,val),new Vector2(0,val)};
        int index = 0;
        for(Vector2 x : temp)
        {
            temp[index].setX(matrix[index].getX());
            temp[index].setY(matrix[index].getY());
        }
        localVertices = temp;
    }

    public Polygon getPolygon()
    {
        int[] xp = new int[100];
        int[] yp = new int[100];
        int i = 0;
        for(Vector2 vertice : worldVertices)
        {
            xp[i] = (int) vertice.getX();
            yp[i] = (int) vertice.getY();
            i++;
        }
        return new Polygon(xp,yp,4);
    }


}
