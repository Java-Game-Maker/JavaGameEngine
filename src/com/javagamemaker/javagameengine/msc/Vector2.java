package com.javagamemaker.javagameengine.msc;

import java.util.Objects;

public class Vector2 {

    public static Vector2 up = new Vector2(0,-1);
    public static Vector2 down = new Vector2(0,1);
    public static Vector2 right = new Vector2(1,0);
    public static Vector2 left = new Vector2(-1,0);
    public static Vector2 zero = new Vector2(0,0);
    private float x,y;

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public Vector2(Vector2 vector2) {
        this.x = vector2.x;
        this.y = vector2.y;
    }
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public String toString()
    {
        return ("{x:{"+x+"} y:{"+y+"}}");
    }

    public Vector2 multiply(float multiple) {
        return new Vector2(x*multiple,y*multiple);
    }
    public Vector2 multiply(double multiple) {
        return new Vector2((float) (x*multiple), (float) (y*multiple));
    }
    public Vector2 multiply(Vector2 vector2) {
        return new Vector2(x*vector2.x,y*vector2.y);
    }

    public float getMagnitude (){
        return (float) Math.sqrt((double) this.x * (double) this.x + (double) this.y * (double) this.y);
    }

    /**
     *
     * @return (x,y) => (y,x)
     */
    public Vector2 getOpposite(){
        return new Vector2(y,x);
    }

    public Vector2 divide(float a){
        return new Vector2(x/a,y/a);
    }
    public Vector2 divide(Vector2 a){
        return new Vector2(x/a.getX(),y/a.getY());
    }
    public Vector2 add(Vector2 vector2) {
        return new Vector2(x+ vector2.x,y+ vector2.y);
    }
    public Vector2 add(float a) {
        return new Vector2(x+ a,y+ a);
    }
    public Vector2 subtract(Vector2 vector2) {
        return new Vector2(x - vector2.x,y - vector2.y);
    }
    public Vector2 subtract(float value) {
        return new Vector2( x- value,y - value);
    }

    public Vector2 getNormalized()
    {
        if(getHighest()!=0)
            return divide(getHighest());
        return Vector2.zero;
    }

    private float getHighest() {
        return Math.max(Math.abs(x), Math.abs(y));
    }

    /**
     * distance between this and param
     * @param vector2 next point
     * @return distance as double
     */
    public double getDistance(Vector2 vector2)
    {
        return Math.sqrt(Math.pow(Math.abs(vector2.getY()-y),2)+Math.pow(Math.abs(vector2.getX()-x),2));
    }

    public float getAngle()
    {
        //A = atan2(V.y, V.x)
        return (float)(Math.toDegrees(Math.atan2(-y,-x)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2 vector2 = (Vector2) o;
        return Float.compare(vector2.x, x) == 0 && Float.compare(vector2.y, y) == 0;
    }
    public static Vector2 getDirection(double angle) {
        float x = (float) Math.cos(Math.toRadians(angle));
        float y = (float) Math.sin(Math.toRadians(angle));
        return new Vector2(x,y);
    }
    /**
     Returns the angle between object position and vector given
     @param toLookAt the vector to look at
     @return the degrees from this to the toLookAt vector
     **/
    public float lookAt(Vector2 toLookAt)
    {
        float angle;
        if((toLookAt.getX()>getX())){
            float b = getX()-toLookAt.getX();
            float a = getY()-toLookAt.getY();
            //a/b=tan v
            //System.out.println("a; "+a+"b: "+b);
             angle = (float) Math.toDegrees(Math.atan(a/b));
        }else{
            float b = getX()-toLookAt.getX();
            float a = getY()-toLookAt.getY();
            //a/b=tan v
            //System.out.println("a; "+a+"b: "+b);
            angle = 180+(float) Math.toDegrees(Math.atan(a/b));
        }
        return angle;
    }
    public static double angleFromOriginCounterClockwise(Vector2 a) {
        double degrees = Math.toDegrees(Math.atan(a.getY()/a.getX()));
        if(a.getX() < 0.0) return degrees+180.0;
        else if(a.getY() < 0.0) return degrees+360.0;
        else return degrees;
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Vector2 removeY(){
        return new Vector2(x,0);
    }
    public Vector2 removeX(){
        return new Vector2(0,y);
    }

    public Vector2 divide(int i) {
        return this.divide(new Vector2(i,i));
    }
}
