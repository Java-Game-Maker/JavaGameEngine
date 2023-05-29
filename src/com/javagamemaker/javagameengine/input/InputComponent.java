package com.javagamemaker.javagameengine.input;

import com.javagamemaker.javagameengine.components.Component;
import com.javagamemaker.javagameengine.msc.Debug;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * The InputComponent is used to get input
 * In the component we can set the chanel to listen to
 * and we can then set which chanel the Input class
 * should send to
 */
public class InputComponent extends Component {
    /**
     * The chanel name which
     */
    private String context = "";
    private LinkedList<Integer> leftKeys = new LinkedList<>();

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public InputComponent(String context) {
        this.context = context;
    }

    @Override
    public void update() {
        super.update();
        /*
            If the left key does not exist in the down remove it
         */
        LinkedList<Integer> keyBuffer = new LinkedList<>();
        for(int key : leftKeys){
            if(!Input.isKeyDown(key)){
                keyBuffer.add(key);
            }
        }
        leftKeys.removeAll(keyBuffer);

    }

    public boolean isKeyPressed(int keycode){
        boolean pressed = Input.getKeyDowns().contains(keycode) &&
                !leftKeys.contains(keycode) &&
                Input.getActiveContext().contains(context);
        if(pressed)
            leftKeys.add(keycode);
        return pressed;
    }

    public boolean isMousePressed(int mouseCode){
       return Input.isMousePressed() && Input.getActiveContext().contains(context);
    }

    public boolean isKeyDown(int keycode){
        return Input.getKeyDowns().contains(keycode) &&
                Input.getActiveContext().contains(context);
    }

}
