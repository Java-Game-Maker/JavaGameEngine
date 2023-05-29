package com.javagamemaker.javagameengine.input;

import com.javagamemaker.javagameengine.msc.Vector2;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;

public class Input {

    private static final LinkedList<Integer> keyDowns = new LinkedList<>();
    private static boolean isPressed = false;
    private static int mouseIsPressed = 1000;
    private static final LinkedList<Integer> mouseButtonDowns = new LinkedList<>();
    private static Vector2 mousePosition = new Vector2(0, 0);
    private static float scrollValue = 0;
    private static Vector2 mousePositionOnCanvas = new Vector2(0, 0);

    private static MouseEvent mouseEvent = null;

    private static ArrayList<String> activeContext = new ArrayList<>();

    private static String checking = "";

    private Input() {
    }
    /**
     *
     * @return mouse position in world
     */
    public static Vector2 getMousePosition() {
        return mousePosition;
    }

    public static void addContext(String context){
        activeContext.add(context);
    }
    public static void removeContext(String context){
        activeContext.remove(context);
    }

    public static ArrayList<String> getActiveContext() {
        return activeContext;
    }

    public static void setActiveContext(ArrayList<String> activeContext_) {
        activeContext = activeContext_;
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
        if (!isMouseDown(e.getButton()))
            mouseButtonDowns.add(Integer.valueOf(e.getButton()));
        mouseIsPressed = Integer.valueOf(e.getButton());

    }

    public static String getChecking() {
        return checking;
    }

    public static void setChecking(String checking) {
        Input.checking = checking;
    }

    /**
     *
     * @return mouse position of panel so top right = 0,0
     */
    public static Vector2 getMousePositionOnCanvas() {
        return mousePositionOnCanvas;
    }

    public static void setMousePositionOnCanvas(Vector2 mousePositionOnCanvas) {
        Input.mousePositionOnCanvas = mousePositionOnCanvas;
    }

    public static void removeMouseButton(MouseEvent e) {

        if(isMouseDown(e.getButton()))
            mouseButtonDowns.remove(Integer.valueOf(e.getButton()));
    }
    public static boolean isMouseDown(int mouseButtonDown)
    {
        return(mouseButtonDowns.contains(mouseButtonDown)) && activeContext.contains(checking);
    }

    public static boolean isMouseDown() {
        return mouseButtonDowns.size() > 0&& activeContext.contains(checking);
    }

    public static boolean isMousePressed(){
        return mouseIsPressed !=1000&& activeContext.contains(checking);
    }

    /**
     * Returns true the frame the mousebutton was pressed
     * Can only be called in the update (not in updateSecound)
     * @param keyCode the button to check
     * @return true
     */
    public static boolean isMousePressed(int keyCode){
        return mouseIsPressed == keyCode&& activeContext.contains(checking);
    }
    /**
     * @param keyCode the key to check
     * @return true if the keyCode is held down
     */
    public static boolean isKeyDown(int keyCode) {
        return(keyDowns.contains(keyCode))&& activeContext.contains(checking);
    }

    public static void addKey(KeyEvent e){
        if(!isKeyDown(e.getKeyCode()))
            keyDowns.add(Integer.valueOf(e.getKeyCode()));
        if(!isPressed)
            isPressed = true;
    }
    public static void removeKey(KeyEvent e) {
        if(isKeyDown(e.getKeyCode()))
            keyDowns.remove(Integer.valueOf(e.getKeyCode()));
    }
    public static boolean isKeyPressed(int keyCode){
        boolean pressed =  isKeyDown(keyCode);
        if(pressed)
            keyDowns.remove(Integer.valueOf(keyCode));
        return pressed && activeContext.contains(checking);
    }
    public static void setMousePressed(int i) {
        mouseIsPressed = i;
    }
    public static int getKeyDown(){
        try{
            return keyDowns.getFirst();
        }catch (Exception e){}
        return 0;
    }
}
