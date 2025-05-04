package yandex.yy7.paket4;

import java.io.*;
import java.util.*;

public class Task4 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = Integer.parseInt(reader.readLine().trim());
            List<String> windows = new ArrayList<>(n);

            for (int i = 0; i < n; i++) {
                String line = reader.readLine();
                if (line.startsWith("Run ")) {
                    String name = line.substring(4);
                    windows.add(0, name);
                    writer.write(name);
                } else {
                    if (windows.isEmpty()) {
                        writer.newLine();
                        continue;
                    }
                    int tabs = line.split("Tab", -1).length - 1;
                    int idx  = tabs % windows.size();
                    String app = windows.remove(idx);
                    windows.add(0, app);
                    writer.write(app);
                }
                writer.newLine();
            }

            writer.flush();
        }
    }
}