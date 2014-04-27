import java.util.ArrayList;

public class Library extends Speaker {
    ArrayList<BookInstance> books;

    public Library(String name) {
        this.books = new ArrayList<BookInstance>();
        this.name = name;
        this.start();
    }

    public void addBookInstance(BookDefinition definition) {
        //System.out.println("Adding book instance of " + definition.toString() + " to " + this.name);
        say("I received book instance of " + definition.toString());
        this.books.add(new BookInstance(definition));
    }

    public void showBooks() {
        //System.out.println("Showing books from library " + name + "...");
        for (BookInstance i: books) {
            System.out.println("*** " + i.isInstanceOf().toString() + "[state: " + i.isAvailableStr() + "]");
        }
    }

    public BookInstance findBookById(int id) {
        say("Answering request. Trying to find book...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            //
        }
        for (BookInstance instance: books) {
            if (instance.isAvailable()) {
                if (instance.isInstanceOf().id == id) {
                    say("SUCCESS. Book has been found.");
                    return instance;
                }
            }
        }
        say("Request FAILED. No book.");
        return null;
    }
}
