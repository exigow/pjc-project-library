import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookDatabase extends Speaker {
    List<BookDefinition> definitions;

    // Constructor. Create book database from file.
    public BookDatabase(String filename) {
        this.giveName("Database");
        definitions = new ArrayList<BookDefinition>();
        FileData.startReading(filename);
        ArrayList<String> str;
        while ((str = FileData.readLine()) != null) {
            //System.out.println(str);
            this.addDefinition(new BookDefinition(Integer.parseInt(str.get(0)), str.get(1), str.get(2), Integer.parseInt(str.get(3))));
        }
        FileData.endReading();
    }

    // Add definition to list of books.
    public BookDefinition addDefinition(BookDefinition def) {
        definitions.add(def);
        return def;
    }

    // Show all defs.
    public void showAllDefinitions() {
        sayBegin("Showing definitions:");
        for (BookDefinition def: definitions) {
            sayMid("" + def.toString());
        }
        sayEnd();
    }

    // TODO wtf...?
    public BookDefinition getBookDefinition() {
        return definitions.get(0);
    }

    // Return book def by id of book.
    public BookDefinition getBookDefinitionById(int id) {
        for (BookDefinition def: definitions) {
            if (def.id == id) {
                return def;
            }
        }
        return null;
    }
}
