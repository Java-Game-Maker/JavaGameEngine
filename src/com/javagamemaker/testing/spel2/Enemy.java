package com.javagamemaker.testing.spel2;

import com.javagamemaker.javagameengine.components.Sprite;
import com.javagamemaker.javagameengine.msc.Debug;

public class Enemy extends Sprite {

    public Enemy(){
        loadAnimation(new String[]{
                "/spel2/pixil-frame-0.png",
                "/spel2/pixil-frame-1.png",
                "/spel2/pixil-frame-2.png",
                "/spel2/pixil-frame-3.png",
                "/spel2/pixil-frame-4.png",
        });
        setTimer(20);
    }
    int times = 0;
    @Override
    public void animationDone() {
        super.animationDone();
        Debug.log("asd");
        Debug.log("");
        if(times == 3){
            setInverted(!isInverted());
            times=0;
        }
        times++;
    }
}
