import java.io.*;
import java.util.ArrayList;

public class FileData {
    private static BufferedReader reader;
    private static String name;
    private static String line;

    /*
    Example:
        FileData.startReading(filename);
        ArrayList<String> str;
        while ((str = FileData.readLine()) != null) {
            System.out.println(str);
        }
        FileData.endReading();
    */

    // You have to start before reading.
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

    // Read line. Returns array of strings separated by ";".
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

            // Convert to char-array.
            char arr[] = line.toCharArray();

            for (char c: arr) {
                // Check until ";".
                if (c == ';') {
                    // Add subword to list and clear.
                    list.add(subword);
                    subword = "";
                } else {
                    // Extend subword by this char.
                    subword += c;
                }
            }
            return list;
        }
        return null; // If failed (empty string for example).
    }

    // Finish reading.
    public static void endReading() {
        try {
            reader.close();
        } catch (IOException e) {
            System.err.println("File close exception!");
        }
        msg("Reading end.");
        name = "<empty_name>";
    }

    // Debug msg.
    public static void msg(String what) {
        System.out.println("[File-IO] (" + name + "): " + what);
    }
}