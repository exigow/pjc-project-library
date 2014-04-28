
public class Time extends Thread {
    private long t;

    public Time() {
        t = 0;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                //
            }
            t += 1;
        }
    }

    public static long getTime() {
        return singletonTime.t;
    }

    public static String getTimeStr() {
        return "[" + getTime() + "]";
    }

    public static Time singletonTime;
    public static void setSingletonTime(Time time) {
        singletonTime = time;
    }
}
