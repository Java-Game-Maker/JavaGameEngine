package example;
import JavaGameEngine.Components.GameObject;
import JavaGameEngine.JavaGameEngine;
import JavaGameEngine.Backend.ComponentHandler;


public class Main extends JavaGameEngine{ // We extend JavaGameEngine

    public static void main(String[] args){ // Main function
        Main m = new Main(); // create a new instance of our main class
        m.init(); // init our game
        ComponentHandler.addObject(new GameObject()); // adding a empty Gameobject to our scene
        m.start(); // start our game
    }
}
