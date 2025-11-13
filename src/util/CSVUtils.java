package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {

    public static List<String[]> read(File file) {
        List<String[]> rows = new ArrayList<>();

        if (!file.exists()) return rows;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                rows.add(line.split(",", -1)); // tách theo dấu phẩy
            }
        } catch (IOException e) {
            System.err.println("[CSVUtils] read error: " + e.getMessage());
        }

        return rows;
    }

    public static void write(File file, List<String[]> rows) {
        try {
            if (file.getParentFile() != null)
                file.getParentFile().mkdirs();

            try (PrintWriter pw = new PrintWriter(new FileWriter(file, false))) {
                for (String[] row : rows) {
                    pw.println(String.join(",", row));
                }
            }
        } catch (IOException e) {
            System.err.println("[CSVUtils] write error: " + e.getMessage());
        }
    }
}