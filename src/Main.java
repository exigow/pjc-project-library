import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Test start.");

        // Load database.
        BookDatabase base = new BookDatabase("bookdefs.txt");
        base.showAllDefinitions();
/*
        final Reader a = new Reader("Tom");
        a.addAction(new Action("say hello") {
            public void action() {
                a.say("Hello!");
            }
        });
        a.addAction(new Action("read") {
            public void action() {
                a.read();
            }
        });
        a.showActionStack();
        a.start();
*/

    }
}