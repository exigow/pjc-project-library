public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Test start.");

        BookDatabase base = new BookDatabase("books.txt");
        base.showAllDefinitions();

        Library lib = new Library("Biblioteka");
        lib.addBookInstance(base.getBookDefinitionById(3));
        lib.addBookInstance(base.getBookDefinitionById(3));
        lib.addBookInstance(base.getBookDefinitionById(121));
        lib.addBookInstance(base.getBookDefinitionById(73));
        lib.addBookInstance(base.getBookDefinitionById(73));
        lib.addBookInstance(base.getBookDefinitionById(73));

        lib.showBooks();
    }
}