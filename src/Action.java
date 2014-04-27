public class Action extends Thread {
    private String name;

    public Action(String actionName) {
        this.name = actionName;
    }

    public void action() {
        System.err.println("Empty action! Check.");
    }

    @Override
    public void run() {
        this.action();
    }

    public String pickName() {
        return "<" + name + ">";
    }
}
