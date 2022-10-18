package javagameengine.input;

import javagameengine.msc.Debug;
import javagameengine.msc.Vector2;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class Input {
    private static LinkedList<Integer> keyDowns = new LinkedList<>();
    private static boolean isPressed = false;
    private static int mouseIsPressed = 1000;
    private static Vector2 mousePosition=new Vector2(0,0);
    private static Vector2 mousePositionOnCanvas =new Vector2(0,0);
    private static float scrollValue=0;
    private static LinkedList<Integer> mouseButtonDowns = new LinkedList<>();

    private static MouseEvent mouseEvent = null;
    public static Vector2 getMousePosition() {
        return mousePosition;
    }

    public static MouseEvent getMouseEvent() {
        return mouseEvent;
    }

    public static void setMouseEvent(MouseEvent e) {
        Input.mouseEvent = e;
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
        if(!isMouseDown(e.getButton())){
            mouseButtonDowns.add(new Integer(e.getButton()));
            mouseIsPressed = new Integer(e.getButton());
        }


    }

    public static Vector2 getMousePositionOnCanvas() {
        return mousePositionOnCanvas;
    }

    public static void setMousePositionOnCanvas(Vector2 mousePositionOnCanvas) {
        Input.mousePositionOnCanvas = mousePositionOnCanvas;
    }

    public static void removeMouseButton(MouseEvent e) {

        if(isMouseDown(e.getButton()))
            mouseButtonDowns.remove(new Integer(e.getButton()));
    }
    public static boolean isMouseDown(int mouseButtonDown)
    {
        return(mouseButtonDowns.contains(mouseButtonDown));
    }

    public static boolean isMouseDown() {
        return mouseButtonDowns.size() > 0;
    }

    public static boolean isMousePressed(){
        return mouseIsPressed!=1000;
    }

    /**
     * Returns true the frame the mousebutton was pressed
     * Can only be called in the update (not in updateSecound)
     * @param keyCode the button to check
     * @return true
     */
    public static boolean isMousePressed(int keyCode){

        return mouseIsPressed == keyCode;
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


    public static void setMousePressed(int i) {
        mouseIsPressed = i;
    }
}
