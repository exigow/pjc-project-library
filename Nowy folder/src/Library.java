import java.util.ArrayList;

<<<<<<< HEAD
public class Library extends Speaker {
    public Library(String name) {
        giveName(name);
    }


    public static ArrayList<Library> createLibsFromFile(String libsource) {
        ArrayList<Library> libs = new ArrayList<Library>();

        FileData.startReading(libsource);
        ArrayList<String> str;
        while ((str = FileData.readLine()) != null) {
            System.out.println(str);

            Library lib = new Library(
                    /* name */ str.get(0));

            //this.addDefinition(new BookDefinition(Integer.parseInt(str.get(0)), str.get(1), str.get(2)));

        }
        FileData.endReading();
        return libs;
=======
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
>>>>>>> parent of 67a590b... Save, before refactor.
    }
}
