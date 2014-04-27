public abstract class Speaker extends Thread {
    public String name;

    public void say(String what) {
        System.out.println(this.name + " says: " + what);
    }
}
