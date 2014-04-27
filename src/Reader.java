import java.util.ArrayList;

public class Reader extends Speaker {
    public Reader(String name) {
        this.name = name;
    }

    public void findBookById(int bookid, ArrayList<Library> list) {
        /*Thread thread = new Thread(new Runnable() {
            public void run() {
            }
        });
        thread.start();*/

        for (Library lib: list) {
            //System.out.println(this.name + " is requesting book from " + lib.libname + "...");
            say("Im requesting book from " + lib.name + "...");
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                System.err.println("Can't wait.");
            }

            BookInstance i = lib.findBookById(bookid);
            if (i != null) {
                break;
            }
        }
    }

    public void run() {

    }
}
