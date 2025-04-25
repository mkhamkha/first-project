package yandex.yy7.paket3;

import java.io.*;

public class Task4 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = Integer.parseInt(reader.readLine().trim());
            String bin = Integer.toBinaryString(n);
            int len = bin.length();
            int best = 0;

            String s = bin;
            for (int i = 0; i < len; i++) {
                if (i > 0) {
                    s = s.substring(1) + s.charAt(0);
                }
                int val = Integer.parseInt(s, 2);
                if (val > best) {
                    best = val;
                }
            }

            writer.write(String.valueOf(best));
            writer.newLine();
        }
    }
}