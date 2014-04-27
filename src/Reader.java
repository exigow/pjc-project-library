import java.util.ArrayList;

public class Reader extends Speaker {
    private Thread ptr;
    private ArrayList<Action> actionStack;

    public Reader(String name) {
        this.giveName(name);
        this.actionStack = new ArrayList<Action>();
    }

    public void addAction(Action action) {
        actionStack.add(action);
    }

    public void showActionStack() {
        sayBegin("this is my action stack:");
        int i = 0;
        for (Action a: actionStack) {
            System.out.println("    " + i +  ": " + a.pickName());
            i++;
        }
        sayEnd();
    }

    @Override
    public void run() {
        say("I'm starting perform my actions...");
        for (Action a: actionStack) {
            say("executing action " + a.pickName());
            a.run();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                //
            }
        }
    }


    public void read() {
        say("Reading...");
        for (int i = 0; i < 4; i++) {
            say("I'm reading hard (step: " + i + ")");
            try {
                Thread.sleep((long)(128 + Math.random() * 128));
            } catch (InterruptedException e) {
                //
            }
        }
    }
}
