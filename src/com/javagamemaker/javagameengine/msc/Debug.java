package com.javagamemaker.javagameengine.msc;

import com.javagamemaker.javagameengine.components.Component;

/**
 * This class has a lot of nice features for debugging
 * with startcount and endcount can you count time of your coude
 * log will print to console
 * showWhere = true => will print from where they are ( log hunter ) | false only the content
 * shouldLog = false => no logs at all | true = logs
 */
public class Debug {
    static float a = 0;
    public static boolean shouldLog = true;
    /**
     * This is a handy tool to find where you have placed your logging
     * It will make it log from what file and line the log is coming from
     */
    public static boolean showWhere = false;

    /**
     * Use this function and endCount to see how long a piece of code takes
     * set the startCount before the code and endCount after
     *<pre><code>
     *Debug.startCount();
     *myFunction();
     *Debug.endCount()
     * </code></pre>
     */
    public static void startCount(){
        a = System.nanoTime();
    }
    /***
     * Use this function and startCount to see how long a piece of code takes
     * set the startCount before the code and endCount after
     *<pre><code>
     *Debug.startCount();
     *myFunction();
     *Debug.endCount()
     * </code></pre>
     * to see in milliseconds endCountMilliSeconds
     */
    public static void endCount(){
        logPriv(String.valueOf((System.nanoTime()-a)));
    }
    /***
     * Use this function and startCount to see how long a piece of code takes
     * set the startCount before the code and endCount after
     *<pre><code>
     *Debug.startCount();
     *myFunction();
     *Debug.endCount()
     * </code></pre>
     */
    public static void endCountMillSeconds(){
        logPriv(String.valueOf((System.nanoTime()-a)/1000000));
    }
    public static void endCount(int devide){
        logPriv(String.valueOf((System.nanoTime()-a)/devide));
    }
    public static void log(Component log){
        logPriv((log.toString()));
    }
    public static void log(Boolean log){
        logPriv((log.toString()));
    }
    public static void log(String log){
        logPriv((log));
    }
    public static void log(int log){
        logPriv(String.valueOf(log));
    }
    public static void log(float log){
        logPriv(String.valueOf(log));
    }
    public static void log(Vector2 log){
        logPriv(log.toString());
    }
    public static void log(Double log){logPriv(String.valueOf(log));}
    private static void logPriv(String log)
    {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        if(shouldLog&&showWhere)
            System.out.println(stackTraceElements[3]+log);
        else if(shouldLog)
            System.out.println(log);
    }

}
