public abstract class Speaker extends Thread {
    protected String name;

    protected void say(String what) {
        System.out.println(this.name + " -> " + what);
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
}