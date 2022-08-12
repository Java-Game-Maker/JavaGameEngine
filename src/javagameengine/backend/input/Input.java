package javagameengine.backend.input;

import Testing.Main;
import javagameengine.JavaGameEngine;
import javagameengine.backend.UpdateThread;
import javagameengine.msc.Debug;
import javagameengine.msc.Vector2;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class Input {
    private static LinkedList<Integer> keyDowns = new LinkedList<>();
    private static boolean isPressed = false;
    private static boolean mouseIsPressed = false;
    private static Vector2 mousePosition=new Vector2(0,0);
    private static float scrollValue=0;
    private static LinkedList<Integer> mouseButtonDowns = new LinkedList<>();

    public static Vector2 getMousePosition() {
        return mousePosition;
    }
    public static Vector2 getMouseWorldPosition(){
        Vector2 scale = JavaGameEngine.getWindowSize().devide(new Vector2(1920,1080));
        scale = scale.multiply(JavaGameEngine.getScene().getCamera().getScale());

        Vector2 pos = Input.getMousePosition().devide(scale).add(JavaGameEngine.getWindowSize().subtract(new Vector2(1920,1080)).devide(2));

        return pos;
    }
    public static void setScrollValue(float scrollValue1){
        scrollValue = scrollValue1;
    }
    public static float getScrollValue(){
        return scrollValue;
    }
    public static void setMousePosition(Vector2 mousePosition) {
        Input.mousePosition = mousePosition;
    }
    public static void addMouseButton(MouseEvent e) {
        if(!isMouseDown(e.getButton()))
            mouseButtonDowns.add(new Integer(e.getButton()));

        if(!mouseIsPressed)
        {
            mouseIsPressed = true;
        }
    }
    public static void removeMouseButton(MouseEvent e) {

        if(isMouseDown(e.getButton()))
            mouseButtonDowns.remove(new Integer(e.getButton()));
    }
    public static LinkedList<Integer> getMouseDown(){
        return mouseButtonDowns;
    }
    public static boolean isMouseDown(int mouseButtonDown)
    {
        return(mouseButtonDowns.contains(mouseButtonDown));
    }
    public static boolean isMouseDown() {
        return mouseButtonDowns.size() > 0;
    }

    public static boolean isMousePressed(){
        boolean temp = mouseIsPressed;
        mouseIsPressed = false;
        return temp;
    }
    /**
     * @param keyCode the key to check
     * @return true if the keyCode is held down
     */
    public static boolean isKeyDown(int keyCode) {
        return(keyDowns.contains(keyCode));
    }

    public static void addKey(KeyEvent e){
        if(!isKeyDown(e.getKeyCode()))
            keyDowns.add(new Integer(e.getKeyCode()));
        if(!isPressed)
            isPressed = true;
    }
    public static void removeKey(KeyEvent e) {
        if(isKeyDown(e.getKeyCode()))
            keyDowns.remove(new Integer(e.getKeyCode()));
    }
    public static boolean isKeyPressed(int keyCode){

        boolean pressed =  isKeyDown(keyCode);
        if(pressed)
            keyDowns.remove(new Integer(keyCode));
        return pressed;
    }
    
    public static int getHorizontal()
    {
        if(isKeyDown(Keys.D))
            return 1;
        if(isKeyDown(Keys.A))
            return -1;
        return 0;
    }
    
    public static int getVertical()
    {
        if(isKeyDown(Keys.W))
            return 1;
        if(isKeyDown(Keys.S))
            return -1;
        return 0;
    }


}
