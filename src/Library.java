import java.util.ArrayList;

public class Library {
    ArrayList<BookInstance> books;
    String libname;

    public Library(String libname) {
        this.books = new ArrayList<BookInstance>();
        this.libname = libname;
    }

    public void addBookInstance(BookDefinition definition) {
        System.out.println("Adding book instance of " + definition.toString() + " to " + this.libname);
        this.books.add(new BookInstance(definition));
    }

    public void showBooks() {
        System.out.println("Showing books from library " + libname + "...");
        for (BookInstance i: books) {
            System.out.println("*** " + i.isInstanceOf().toString() + "[state: " + i.isAvailableStr() + "]");
        }
    }
}
