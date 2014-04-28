import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;

public class Reader extends Speaker {
    private ArrayList<Action> actionStack;
    private ArrayList<Library> libList;
    private BookDatabase data;
    private BookInstance myBook;
    private int atPage;
    private Library myBookFrom;

    ActionListener taskPerformer = new ActionListener() {
        int iter = 0;
        public void actionPerformed(ActionEvent evt) {
            System.out.println("Tick " + iter);
            iter++;
        }
    };

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

                reader.addAction(new Action("readwhatyouhave") {
                    @Override
                    public void action() {
                        getOwner().actionReadBook();
                    }
                });

                reader.addAction(new Action("returnwhatyouhave") {
                    @Override
                    public void action() {
                        getOwner().returnBook();
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
        BookInstance _book = null;
        int safety = 0;
        int safetyMax = 8;
        boolean safetyPass = false;
        while (safety < safetyMax) {
            safety++;
            lib = libList.get(libToCheck);
            say("Checking lib (" + safety + ")" + lib.pickName());

            // Code!
            sleepRandTime(100);

            _book = askLibForBook(lib, bookid);
            if (_book != null) {
                say("Book founded! Lib: " + lib.pickName());
                myBookFrom = lib;
                lib.getBook(this, _book);
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
            say("Safety end. No book. Exterminate thread.");
        }
    }

    public void giveBook(BookInstance book) {
        myBook = book;
        say("Got book " + book.isInstanceOf().getTitle());
    }

    public void actionReadBook() {
        atPage = 0;
        say("Reading book " + myBook.isInstanceOf().getTitle() + "...");

        while (atPage < myBook.isInstanceOf().getLength()) {
            say("Reading at page " + atPage);
            sleepRandTime(100);
            atPage += 10;
        }

        say("Finish! (reading)");
    }

    public void returnBook() {
        say("Returning book " + myBook.isInstanceOf().getTitle() + "...");
        myBookFrom.setBookActive(myBook, true);
        say("Return done.");
    }

    public BookInstance askLibForBook(Library lib, int bookid) {
        say("Asking " + lib.pickName() + " for book...");
        for (BookInstance i: lib.books) {
            if (i.isInstanceOf().id == bookid) {
                if (i.isAvailable()) {
                    return i;
                } else {
                    say("Book is not avalible :(");
                }
            }
        }
        return null;
    }
}
