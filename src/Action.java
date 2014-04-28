public class Action extends Thread {
    private String name;
    private Reader owner;

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

    public void setOwner(Reader owner) {
        this.owner = owner;
    }

    public Reader getOwner() {
        return owner;
    }
}
