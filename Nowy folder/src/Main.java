public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Test start.");

        BookDatabase base = new BookDatabase("books.txt");
        base.showAllDefinitions();

<<<<<<< HEAD
        // Create libraries with books.
        ArrayList<Library> libs = Library.createLibsFromFile("libs.txt");
    }
}










/*
        final Reader a = new Reader("Tom");
        a.addAction(new Action("say hello") {
            public void action() {
                a.say("Hello!");
            }
        });
        a.addAction(new Action("read") {
            public void action() {
                a.read();
            }
        });
        a.showActionStack();
        a.start();
*/
=======
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
>>>>>>> parent of 67a590b... Save, before refactor.
