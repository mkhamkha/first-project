package yandex.yy7.paket3;

import java.io.*;

public class Task1 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            long x = Long.parseLong(reader.readLine().trim());
            int y = Long.bitCount(x);
            writer.write(String.valueOf(y));
            writer.newLine();
        }
    }
}