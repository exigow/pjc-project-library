public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Test start.");

        BookDatabase base = new BookDatabase("books.txt");
        base.showAllDefinitions();
    }
}