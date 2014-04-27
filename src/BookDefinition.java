import java.io.Serializable;

public class BookDefinition implements Serializable {
    String title, author;
    int id;

    public BookDefinition(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    @Override
    public String toString() {
        return "<[" + id + "] " + title + " (author: " + author + ")>";
    }
}