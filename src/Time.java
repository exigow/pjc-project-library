


// SINGLETON, he-he.
public class Time extends Thread {
    private long t;
    private int lastLiveTime;

    // How it works:
    // If nobody is responding (for example 100 units of time), terminate timer.

    public Time() {
        t = 0;
        lastLiveTime = 0;
    }


    public void run() {
        //System.err.println("### TIME START ###");

        // Time loop.
        while (lastLiveTime < 100) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                //
            }
            t += 1;
            lastLiveTime += 1;
        }

        // Exec summary.
        summary();
    }

    // Reset timer-of-death.
    public static void invokeLife() {
        singletonTime.lastLiveTime = 0;
    }

    // Get time from beginning.
    public static long getTime() {
        return singletonTime.t;
    }

    public static String getTimeStr() {
        return "[" + getTime() + ":" + singletonTime.lastLiveTime + "]";
    }

    // Static singleton ref.
    public static Time singletonTime;
    public static void setSingletonTime(Time time) {
        singletonTime = time;
    }

    // Override this.
    public void summary() {
        System.err.println("Default time-summary.");
    }
}
