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
    public int haveTimeMax, haveTimeStart;
    public int ivepaid;

    // Constructor. Give name.
    public Reader(String name) {
        this.giveName(name);
        this.actionStack = new ArrayList<Action>();
        say("Created!");
        ivepaid = 0;
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
        say("I'm starting perform my actions (thread start)...");
        for (Action a: actionStack) {
            say("Executing action " + a.pickName());
            a.run();
        }
        say("#HAPPY# I've done all my jobs from action stack (thread end).");
        say("After all, i've paid " + ivepaid + ".");
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
                        getOwner().actionReturnBook();
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

            _book = lib.askLibForBook(this, bookid);
            if (_book != null) {
                say("Book founded! Lib: " + lib.pickName());
                myBookFrom = lib;
                lib.giveBook(this, _book);
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
            say("#ANGRY/SAD# I cant find it! I've asked everywhere. Many times.");
        }
    }

    public void giveBook(BookInstance book, int timeMax) {
        myBook = book;
        haveTimeMax = timeMax;
        haveTimeStart = (int)Time.getTime();
        say("Got book " + book.isInstanceOf().getTitle());
    }

    public void actionReadBook() {
        if (myBook != null) {
            atPage = 0;
            say("Reading book " + myBook.isInstanceOf().getTitle() + "...");

            while (atPage < myBook.isInstanceOf().getLength()) {
                say("Reading at page " + atPage);
                sleepRandTime(100);
                atPage += 10;

                // Check how much time do i have.
                int diff = this.timeDiff();
                if (diff > 0) {
                    say("#WORRIED# I have to return my book! Time expired by " + diff + " time units.");
                }
            }

            say("Finish! (reading)");
        } else {
            say("#ANGRY# I dont have any book! I cant read!");
        }
    }

    public int timeDiff() {
        return (int)Time.getTime() - haveTimeMax;
    }

    public void actionReturnBook() {
        if (myBook != null) {

            say("Returning book " + myBook.isInstanceOf().getTitle() + "...");
            myBookFrom.giveBackBook(this, myBook);
            say("Return done.");

            myBookFrom = null;
            myBook = null;
        } else {
            say("#ANGRY# I cant return any book! I dont have one!");
        }
    }

    /*public BookInstance askLibForBook(Library lib, int bookid) {
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
    }*/
}
