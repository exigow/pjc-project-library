
public class Time extends Thread {
    private long t;
    private int lastLiveTime;

    public Time() {
        t = 0;
        lastLiveTime = 0;
        isCounting = false;
    }

    public void run() {
        while (lastLiveTime < 100) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                //
            }
            t += 1;
            lastLiveTime += 1;
        }

        summary();
    }

    public static void invokeLife() {
        singletonTime.lastLiveTime = 0;
    }

    public static long getTime() {
        return singletonTime.t;
    }

    public static String getTimeStr() {
        return "[" + getTime() + ":" + singletonTime.lastLiveTime + "]";
    }

    public static Time singletonTime;
    public static boolean isCounting;
    public static void setSingletonTime(Time time) {
        singletonTime = time;
    }

    public void summary() {
        System.err.println("Default time summary.");
    }
}
