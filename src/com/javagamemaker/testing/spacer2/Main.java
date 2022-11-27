package com.javagamemaker.testing.spacer2;
import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.Scene;

public class Main extends JavaGameEngine{

    public static Player player = new Player();

    public static void main(String[] args){

        setSelectedScene(new Space());
        start();

    }

}
