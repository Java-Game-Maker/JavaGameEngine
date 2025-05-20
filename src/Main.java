import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.Scene;
import com.javagamemaker.javagameengine.components.Component;
import com.javagamemaker.javagameengine.input.Input;
import com.javagamemaker.javagameengine.input.Keys;
import com.javagamemaker.javagameengine.msc.Debug;
import com.javagamemaker.javagameengine.msc.Vector2;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

/*
    user can add script
    this will create default java class that extends component
    when that file is updated we build it and add it to a game jar file (javagameneinge jar)
    when the script is placed on a component a temporary file will be created
    this file will state which component (with index) should have this component
    when the game starts this file gets removed and the scene will add the component to the
    selected components

 */

public class Main extends JavaGameEngine {

    public static void main(String[] args){

        Scene scene1 = new Scene(){
            @Override
            public void start() {
                super.start();
                getCamera().setScale(new Vector2(2,2));
                //getCamera().setPosition(new Vector2(100,0));
            }

            @Override
            public void debugUpdate() {
                super.debugUpdate();
                if(Input.isKeyPressed(Keys.ESCAPE)){
                    setDebugMode(false);
                }
            }
        };
        try{
            scene1.load();
        }catch (Exception e){

        }
        // Create a File object on the root of the directory containing the class file
        //File file = new File("/home/spy/dev/java/Test/out/production/Test/Player.class");
        //File file = new File("/home/spy/dev/java/JavaGameEngine/out/production/JavaGameEngine/Player.class");

        String scriptFiles = "/home/spy/dev/java/JavaGameEngine/out/artifacts/edit/scripts";
        /*
        File folder = new File(scriptFiles);
        for( File file : folder.listFiles() ){
            if(file.getName().contains(".class")) {

                try {
                    // Convert File to a URL
                    URL url = file.toURI().toURL();          // file:/c:/myclasses/
                    URL[] urls = new URL[]{url};

                    // Create a new class loader with the directory
                    ClassLoader cl = new URLClassLoader(urls);

                    // Load in the class; MyClass.class should be located in
                    // the directory file:/c:/myclasses/com/mycompany
                    Class cls = cl.loadClass("Player");
                    Component c = (Component) cls.newInstance();
                    c.update();
                    scene1.getComponents1().get(0).add(c);


                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

         */


        Debug.log(scene1.getComponents1().size());


        scene1.setDebugMode(true);

       // scene1.add(new GameObject());

        setSelectedScene(scene1);

        JavaGameEngine.size = new Vector2(1920/2,1080/2);

        start();
    }

}
