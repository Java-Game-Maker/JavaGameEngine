package Main.Msc.Input;

import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class Input {


    private static LinkedList<Integer> keyDowns = new LinkedList<>();

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
}
