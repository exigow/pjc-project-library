import java.util.ArrayList;

public class Library extends Speaker {
    ArrayList<BookInstance> books;
    public int coins;

    public Library(String name) {
        giveName(name);
        books = new ArrayList<BookInstance>();
        say("Created!");
        coins = 0;
    }

    // Add instance of book (from definition).
    public void addBookInstance(BookDefinition definition) {
        //System.out.println("Adding book instance of " + definition.toString() + " to " + this.name);
        sayMid("Book instance added " + definition.toString());
        this.books.add(new BookInstance(definition));
    }

    public static ArrayList<Library> createLibsFromFile(String libsource, BookDatabase base) {
        ArrayList<Library> libs = new ArrayList<Library>();

        FileData.startReading(libsource);
        ArrayList<String> str;
        while ((str = FileData.readLine()) != null) {
            Library lib = new Library(
                    /* name */ str.get(0));

            lib.sayBegin("Receiving books:");

            // Add books.
            for (int i = 1; i < str.size(); i++) {
                lib.addBookInstance(base.getBookDefinitionById(Integer.parseInt(str.get(i))));
            }

            lib.sayEnd();

            // Add lib to list of libraries.
            libs.add(lib);
        }
        FileData.endReading();

        // Return list of libraries.
        return libs;
    }

    // Give book to reader. Set maximum time, false active.
    synchronized public void giveBook(Reader reader, BookInstance instance) {
        int timeMax = 50; // Each reader have 50 time units per book.
        say("Giving book " + instance.isInstanceOf().getTitle() + " to " + reader.pickName() + " You have " + timeMax + " time units.");
        reader.giveBook(instance, (int)Time.getTime() + timeMax);
        setBookActive(instance, false);
    }

    // Return book to lib from reader. Punish him if he expired time.
    synchronized public void giveBackBook(Reader reader, BookInstance instance) {
        say("I've got book " + instance.isInstanceOf().getTitle() + " from " + reader.pickName());
        setBookActive(instance, true);

        int diff = reader.timeDiff();

        if (diff > 0) {
            say("#ANGRY# Time expired! (" + diff + " time units)");
            say("You have to pay " + diff +  " units (1 unit = 1 coin)!");
            coins += diff;
        }

        say("#HAPPY# Thank you " + reader.pickName() + ". Now i have " + coins + " coins.");
    }

    synchronized public void setBookActive(BookInstance book, boolean val){
        book.setAvalible(val);
    }

    public void showBooks() {
        sayBegin("my books: ");
            for (BookInstance i: books) {
                sayMid("" + i.isInstanceOf().toString() + " (" + i.isAvailableStr() + ")");
            }
        sayEnd();
    }

    public static void showAllBooksFromAllLibs(ArrayList<Library> libs) {
        for (Library l: libs) {
            l.showBooks();
        }
    }
}
