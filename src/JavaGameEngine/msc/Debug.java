package JavaGameEngine.msc;

import JavaGameEngine.Components.Component;

public class Debug {
    static float a = 0;
    public static boolean shouldLog = true;
    public static void startCount(){
        a = System.nanoTime();
    }
    public static void endCount(){
        logPriv(String.valueOf((System.nanoTime()-a)));
    }
    public static void endCount(int devide){
        logPriv(String.valueOf((System.nanoTime()-a)/devide));
    }
    public static void log(Component log){
        logPriv((log.toString()));
    }
    public static void log(String log){
        logPriv((log));
    }
    public static void log(Vector2 vec2){
        logPriv((vec2).toString());
    }
    public static void log(int log){
        logPriv(String.valueOf(log));
    }
    public static void log(float log){
        logPriv(String.valueOf(log));
    }
    public static void logCaller(){
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        System.out.println(stackTraceElements[3]);
    }
    public static void logCaller(int depth){
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        System.out.println(stackTraceElements[depth]);
    }
    private static void logPriv(String log)
    {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        if(shouldLog)
            System.out.println(stackTraceElements[3]+log);
    }

}
