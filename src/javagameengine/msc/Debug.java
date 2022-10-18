package javagameengine.msc;

import javagameengine.components.Component;

public class Debug {
    static float a = 0;
    public static boolean shouldLog = true;
    /**
     * This is a handy tool to find where you have placed your logging
     * It will make it log from what file and line the log is coming from
     */
    public static boolean showWhere = false;
    public static void startCount(){
        a = System.nanoTime();
    }
    /***
     * this function will print the amount of time in nanosecunds since the startcound function was called
     */
    public static void endCount(){
        logPriv(String.valueOf((System.nanoTime()-a)));
    }
    public static void endCountMiliSeconds(){
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
