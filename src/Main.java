import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Test start.");

        File file = new File("books.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsoluteFile()), "utf-8"));
        String line, subword;
        char arr[];
        String sub[] = new String[4];
        int ctr = 0;
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
            System.out.println("title: " + sub[0] + ", author: " + sub[1]);
        }
        reader.close();
    }
}