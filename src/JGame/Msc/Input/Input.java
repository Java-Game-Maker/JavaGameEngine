package JGame.Msc.Input;

import JGame.Msc.Vector2;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Vector;

public class Input {


    private static LinkedList<Integer> keyDowns = new LinkedList<>();
    private static LinkedList<Integer> mouseButtonDowns = new LinkedList<>();
    private static Vector2 mousePosition=new Vector2(0,0);

    public static Vector2 getMousePosition() {
        return mousePosition;
    }

    public static void setMousePosition(Vector2 mousePosition) {
        Input.mousePosition = mousePosition;
    }

    public static void mouseButtonDown(MouseEvent e) {
        if(!isMouseDown(e.getButton()))
            mouseButtonDowns.add(new Integer(e.getButton()));
    }
    public static void mouseButtonUp(MouseEvent e) {
        if(isMouseDown(e.getButton()))
            mouseButtonDowns.remove(new Integer(e.getButton()));
    }
    public static boolean isMouseDown(int mouseButtonDown)
    {
        //System.out.println(keyDowns.contains(keyCode));
        return(mouseButtonDowns.contains(mouseButtonDown));
    }

    public static void keyDown(KeyEvent e) {
        if(!isKeyDown(e.getKeyCode()))
            keyDowns.add(new Integer(e.getKeyCode()));
    }
    public static void keyUp(KeyEvent e) {
        if(isKeyDown(e.getKeyCode()))
            keyDowns.remove(new Integer(e.getKeyCode()));
    }
    public static boolean isKeyDown(int keyCode)
    {
        //System.out.println(keyDowns.contains(keyCode));
        return(keyDowns.contains(keyCode));
    }

    public static void isMouseUp(int button) {
    }
}
