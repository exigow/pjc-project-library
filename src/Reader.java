import java.util.ArrayList;

public class Reader extends Speaker {
    private ArrayList<Action> actionStack;
    private ArrayList<Library> libList;
    private BookDatabase data;

    // Constructor. Give name.
    public Reader(String name) {
        this.giveName(name);
        this.actionStack = new ArrayList<Action>();
        say("Created!");
    }

    // Add action on stack.
    public void addAction(Action action) {
        say("I've got action " + action.pickName() + ".");
        action.setOwner(this);
        actionStack.add(action);
    }

    // Shows action stack.
    public void showActionStack() {
        sayBegin("This is my action stack:");
        int i = 0;
        for (Action a: actionStack) {
            System.out.println("    " + i +  ": " + a.pickName());
            i++;
        }
        sayEnd();
    }

    // Run this thread. Execute actions.
    @Override
    public void run() {
        say("I'm starting perform my actions...");
        for (Action a: actionStack) {
            say("executing action " + a.pickName());
            a.run();
        }
    }

    // Create readers from file (give name, build action stack, etc.).
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

    // Set list of libraries (he has to know where to look books).
    public void setLibList(ArrayList<Library> list) {
        libList = list;
        say("I've got list of libraries.");
    }

    // Show reader stacks (give list).
    public static void showStacks(ArrayList<Reader> readers) {
        for (Reader r: readers) {
            r.showActionStack();
        }
    }

    // Run all threads.
    public static void startAllReaders(ArrayList<Reader> readers) {
        for (Reader r: readers) {
            r.start();
        }
    }

    // Predefined action.
    public void actionFindBook(final int bookid) {
        // First wait.
        sleepRandTime(1000);
        // Then do.
        say("Im going to find book " + data.getBookDefinitionById(bookid) + "...");

        int libToCheck = 0;
        Library lib;
        BookInstance libFounded = null;
        int safety = 0;
        int safetyMax = 8;
        boolean safetyPass = false;
        while (safety < safetyMax) {
            safety++;
            lib = libList.get(libToCheck);
            say("Checking lib (" + safety + ")" + lib.pickName());

            // Code!
            sleepRandTime(100);

            libFounded = askLibForBook(lib, bookid);
            if (libFounded != null) {
                say("Book founded! Lib: " + lib.pickName());
                //lib.getBook(this, bookid);
                break;
            } else {
                say("Failed.");
            }

            libToCheck++;
            if (libToCheck >= libList.size()) {
                libToCheck = 0;
                say("I've checked all libs. Starting from beginning.");
            }

            if (safety >= safetyMax) {
                safetyPass = true;
            }
        }

        if (safetyPass) {
            say("Safety end. No book.");
        }
    }

    public BookInstance askLibForBook(Library lib, int bookid) {
        for (BookInstance i: lib.books) {
            if (i.isInstanceOf().id == bookid) {
                return i;
            }
        }
        return null;
    }
}
