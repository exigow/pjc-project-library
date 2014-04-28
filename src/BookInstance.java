
public class BookInstance {
    private boolean state; // Dostepna?
    private BookDefinition definition;
    public Reader owner;

    public BookInstance(BookDefinition definition) {
        state = true;
        this.definition = definition;
    }

    public BookDefinition isInstanceOf() {
        return definition;
    }

    public boolean isAvailable() {
        return state;
    }

    public void setAvalible(boolean av) {
        this.state = av;
    }

    public String isAvailableStr() {
        if (isAvailable()) {
            return "available";
        } else {
            return "NOT AVAILABLE";
        }
    }
}
