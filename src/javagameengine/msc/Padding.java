package javagameengine.msc;

public class Padding {
    int right=0,left=0,top=0,bottom=0;

    public Padding(){}
    public Padding(int padding){
        this.right = padding;
        this.left = padding;
        this.top = padding;
        this.bottom = padding;
    }

    public Padding(int right,int left, int top, int bottom){
        this.right = right;
        this.left = left;
        this.top = top;
        this.bottom = bottom;
    }
    public int getX(){
        return left+right;
    }
    public int getY(){
        return top+bottom;
    }
    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getBottom() {
        return bottom;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;

        try{
            Padding ob2 = (Padding)obj;
            return (right == ob2.right && left == ob2.left && top == ob2.top && bottom == ob2.bottom);

        }catch (Exception e){
            return false;
        }
    }
}