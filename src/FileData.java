import java.io.*;
import java.util.ArrayList;

public class FileData {
    private static BufferedReader reader;
    private static String name;
    private static String line;

    public static void startReading(String filename) {
        name = filename;
        msg("Reading start.");
        File file = new File(name);
        reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsoluteFile()), "utf-8"));
        } catch (IOException e) {
            System.err.println("File not found! (or unsupported coding)");
        }
    }

    public static ArrayList<String> readLine() {
        ArrayList<String> list = new ArrayList<String>();

        try {
            line = reader.readLine();
        } catch (IOException e) {
            System.err.println("File read exception!");
        }

        String subword;
        if (line != null) {
            subword = "";
            char arr[] = line.toCharArray();
            for (char c: arr) {
                if (c == ';') {
                    list.add(subword);
                    subword = "";
                } else {
                    subword += c;
                }
            }
            return list;
        }
        return null;
    }

    public static void endReading() {
        try {
            reader.close();
        } catch (IOException e) {
            System.err.println("File close exception!");
        }
        msg("Reading end.");
        name = "<empty_name>";
    }

    public static void msg(String what) {
        System.out.println("[File-IO] (" + name + "): " + what);
    }
}