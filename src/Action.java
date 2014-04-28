public class Action extends Thread {
    private String name;
    private Reader owner;

    // Constructor.
    public Action(String actionName) {
        this.name = actionName;
    }

    // Default action.
    public void action() {
        System.err.println("Empty action! Check.");
    }

    // Run action on thread.
    @Override
    public void run() {
        this.action();
    }

    // Return name with visible <this>.
    public String pickName() {
        return "<" + name + ">";
    }

    // Set/get owner of action (Reader class).
    public void setOwner(Reader owner) {
        this.owner = owner;
    }
    public Reader getOwner() {
        return owner;
    }
}
