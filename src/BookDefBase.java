import java.util.ArrayList;
import java.util.List;

public class BookDefBase {
    List<BookDef> definitions;
    public BookDefBase() {
        definitions = new ArrayList<BookDef>();
    }

    public BookDef addDefinition(BookDef def) {
        definitions.add(def);
        return def;
    }



    public void showAllDefinitions() {
        System.out.println("Definitions: ");
        for (BookDef def: definitions) {
            System.out.println(def);
        }
    }
}
