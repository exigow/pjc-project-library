import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookDatabase {
    List<BookDefinition> definitions;

    public BookDatabase(String filename) {
        definitions = new ArrayList<BookDefinition>();

        File file = new File("books.txt");
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsoluteFile()), "utf-8"));
        } catch (IOException e) {
            System.err.println("File not found! (or unsupported coding)");
        }

        if (reader != null) {
            String line, subword;
            char arr[];
            String sub[] = new String[4];
            int ctr = 0;
            try {
                while ((line = reader.readLine()) != null) {
                    subword = "";
                    arr = line.toCharArray();
                    for (char c: arr) {
                        if (c == ';') {
                            sub[ctr] = subword;
                            subword = "";
                            ctr += 1;
                        } else {
                            subword += c;
                        }
                    }
                    ctr = 0;
                    // System.out.println("title: " + sub[0] + ", author: " + sub[1]);
                    this.addDefinition(new BookDefinition(Integer.parseInt(sub[0]), sub[1], sub[2]));
                }
            } catch (IOException e) {
                System.err.println("File read exception!");
            }

            try {
                reader.close();
            } catch (IOException e) {
                System.err.println("File close exception!");
            }
        }
    }

    public BookDefinition addDefinition(BookDefinition def) {
        definitions.add(def);
        return def;
    }

    public void showAllDefinitions() {
        System.out.println("Definitions: ");
        for (BookDefinition def: definitions) {
            System.out.println(def);
        }
    }
}
