package com.javagamemaker.javagameengine.input;

import com.javagamemaker.javagameengine.components.Component;
import com.javagamemaker.javagameengine.msc.Debug;

public class InputComponent extends Component {

    private String context = "";

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
        Input.setChecking(context);

    }
}
