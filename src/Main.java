import java.util.ArrayList;

public class Main {
    static ArrayList<Library> libraries;

    public static void main(String[] args) throws Exception {
        System.out.println("Test start.");

        // Load database.
        BookDatabase base = new BookDatabase("bookdefs.txt");
        base.showAllDefinitions();

        // List of libraries.
        libraries = new ArrayList<Library>();

        // Create libArary A
        Library libA = new Library("libA");
        libraries.add(libA);
        libA.addBookInstance(base.getBookDefinitionById(121));
        libA.addBookInstance(base.getBookDefinitionById(73));
        libA.addBookInstance(base.getBookDefinitionById(73));
        libA.showBooks();

        // Create library B.
        Library libB = new Library("libB");
        libraries.add(libB);
        libB.addBookInstance(base.getBookDefinitionById(13));
        libB.addBookInstance(base.getBookDefinitionById(121));
        libB.showBooks();

        // Create readers.
        Reader rA = new Reader("Tom");
        Reader rB = new Reader("Sam");

        //rA.start();
        rA.findBookById(13, libraries);
        rB.findBookById(73, libraries);

  /*      Test a = new Test("A");
        Test b = new Test("B");
        Test c = new Test("C");

        a.start();
        b.start();
        c.start();

        System.out.println("END");*/
    }
}