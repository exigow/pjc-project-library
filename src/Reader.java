import java.util.ArrayList;

public class Reader extends Speaker {
    private Thread ptr;
    private ArrayList<Action> actionStack;
    private ArrayList<Library> libList;
    private BookDatabase data;

    public Reader(String name) {
        this.giveName(name);
        this.actionStack = new ArrayList<Action>();
        say("Created!");
    }

    public void addAction(Action action) {
        say("I've got action " + action.pickName() + ".");
        action.setOwner(this);
        actionStack.add(action);
    }

    public void showActionStack() {
        sayBegin("This is my action stack:");
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

    public static ArrayList<Reader> createReadersFromFile(String filesrc, ArrayList<Library> libraries, BookDatabase data) {
        ArrayList<Reader> readers = new ArrayList<Reader>();

        FileData.startReading(filesrc);
        ArrayList<String> str;
        while ((str = FileData.readLine()) != null) {
            Reader reader = new Reader(str.get(0));
            reader.setLibList(libraries);
            reader.data = data;

            for (int i = 1; i < str.size(); i++) {
                final int _bookid = Integer.parseInt(str.get(i));
                reader.addAction(new Action("findbook" + _bookid) {
                    @Override
                    public void action() {
                        getOwner().actionFindBook(_bookid);
                    }
                });
            }

            readers.add(reader);
        }
        FileData.endReading();

        // Return list of libraries.
        return readers;
    }

    public void setLibList(ArrayList<Library> list) {
        libList = list;
        say("I've got list of libraries.");
    }

    public static void showStacks(ArrayList<Reader> readers) {
        for (Reader r: readers) {
            r.showActionStack();
        }
    }

    public static void startAllReaders(ArrayList<Reader> readers) {
        for (Reader r: readers) {
            r.start();
        }
    }

    public void actionFindBook(final int bookid) {
        // First wait.
        try {
            Thread.sleep((long)(250.0 + Math.random() * 750.0));
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted. Sorry.");
        }

        // Then do.
        say("Im going to find book " + data.getBookDefinitionById(bookid));
    }
}
