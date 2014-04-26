import java.io.Serializable;

public class BookDef implements Serializable {
    String title, author;

    //int instances;

    public BookDef(String title, String author) {
        this.title = title;
        this.author = author;
    }

    @Override
    public String toString() {
        return "" + title + " (author: " + author + ")";
    }
}
