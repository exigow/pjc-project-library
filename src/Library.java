import java.util.ArrayList;

public class Library extends Speaker {
    ArrayList<BookInstance> books;

    public Library(String name) {
        giveName(name);
        books = new ArrayList<BookInstance>();
        say("Created!");
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

    public void getBook(Reader reader, BookInstance instance) {
        say("Giving book " + instance.isInstanceOf().title + " to " + reader.pickName());
    }
}
