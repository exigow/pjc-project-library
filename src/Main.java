import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Test start.");

        // Load database.
        BookDatabase base = new BookDatabase("data/bookdefs.txt");
        base.showAllDefinitions();

        // Create libraries with books.
        ArrayList<Library> libs = Library.createLibsFromFile("data/libs.txt", base);

        // Create readers from file.
        ArrayList<Reader> readers = Reader.createReadersFromFile("data/readersqueue.txt", libs, base);

        // Show action stacks.
        Reader.showStacks(readers);

        // Run them!
        Reader.startAllReaders(readers);
    }
}










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