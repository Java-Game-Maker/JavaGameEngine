package example;
import JavaGameEngine.JavaGameEngine;

public class Main extends JavaGameEngine{ // We extend JavaGameEngine

    public static void main(String[] args){ // Main function
        Main m = new Main(); // create a new instance of our main class
        m.init(); // init our game

        m.start(); // start our game
    }

    @Override
    public void update() { // Updates every update
        super.update();

    }
}
