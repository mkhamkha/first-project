package yandex.yy7.paket3;

import java.io.*;
import java.util.*;

public class Task2 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = Integer.parseInt(reader.readLine().trim());
            int[][] m = new int[n][n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(reader.readLine());
                for (int j = 0; j < n; j++) {
                    m[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                int v = 0;
                for (int j = 0; j < n; j++) {
                    if (i != j) v |= m[i][j];
                }
                a[i] = v;
            }
            for (int i = 0; i < n; i++) {
                writer.write(String.valueOf(a[i]));
                if (i + 1 < n) writer.write(' ');
            }
            writer.newLine();
        }
    }
}