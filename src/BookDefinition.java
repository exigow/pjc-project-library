import java.io.Serializable;

public class BookDefinition implements Serializable {
    private String title, author;
    int id;
    private int length;

    // Constructor.
    public BookDefinition(int id, String title, String author, int length) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.length = length;
    }

    // toString.
    @Override
    public String toString() {
        return "<[" + id + "] " + title + " (author: " + author + ") (length: " + length + ")>";
    }

    public String getTitle() {
        return "<" + title + ">";
    }

    public String getAuthor() {
        return "<" + author + ">";
    }

    public int getLength() {
        return length;
    }
}
