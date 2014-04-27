import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookDatabase extends Speaker {
    List<BookDefinition> definitions;

    public BookDatabase(String filename) {
        this.giveName("Database");
        definitions = new ArrayList<BookDefinition>();
        FileData.startReading(filename);
        ArrayList<String> str;
        while ((str = FileData.readLine()) != null) {
            //System.out.println(str);
            this.addDefinition(new BookDefinition(Integer.parseInt(str.get(0)), str.get(1), str.get(2)));
        }
        FileData.endReading();
    }

    public BookDefinition addDefinition(BookDefinition def) {
        definitions.add(def);
        return def;
    }

    public void showAllDefinitions() {
        sayBegin("showing definitions:");
        for (BookDefinition def: definitions) {
            System.out.println("    " + def);
        }
        sayEnd();
    }

    public BookDefinition getBookDefinition() {
        return definitions.get(0);
    }

    public BookDefinition getBookDefinitionById(int id) {
        for (BookDefinition def: definitions) {
            if (def.id == id) {
                return def;
            }
        }
        return null;
    }
}
