package JavaGameEngine.msc;

public class Debug {
    static float a = 0;
    public static boolean shouldLog = true;
    public static void startCount(){
        a = System.nanoTime();
    }
    public static void endCount(){
        System.out.println((System.nanoTime()-a));
    }
    public static void log(String log){
        if(shouldLog)
            System.out.println("[] "+log);
    }
    public static void log(int log){
        if(shouldLog)
            System.out.println("[] "+log);
    }
    public static void log(float log){
        if(shouldLog)
            System.out.println("[] "+log);
    }

}
