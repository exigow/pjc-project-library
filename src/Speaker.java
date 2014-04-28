public abstract class Speaker extends Thread {
    protected String name;

    protected void say(String what) {
        System.out.println(Time.getTimeStr() + " " + this.name + " -> " + what);
    }

    protected void sayBegin(String what) {
        say(what + "\n{");
    }
    protected void sayMid(String what) {
        System.out.print("    ");
        say(what);
    }
    protected void sayEnd() {
        System.out.println("}");
    }

    public void giveName(String name) {
        this.name = name;
    }

    public String pickName() {
        return "<" + name + ">";
    }

    public void sleepRandTime(float time) {
        try {
            Thread.sleep((long)((time / 2) + Math.random() * (time / 2)));
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted. Sorry.");
        }
    }
}