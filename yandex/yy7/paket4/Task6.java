package yandex.yy7.paket4;

import java.io.*;
import java.util.*;

public class Task6 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader  reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter  writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = Integer.parseInt(reader.readLine().trim());
            List<Integer> p = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                p.add(Integer.parseInt(reader.readLine().trim()) - 1);
            }

            int[] state = new int[n];
            int cycles = 0;

            for (int i = 0; i < n; i++) {
                if (state[i] != 0) continue;
                int v = i;
                Deque<Integer> stack = new ArrayDeque<>();
                while (state[v] == 0) {
                    state[v] = 1;
                    stack.push(v);
                    v = p.get(v);
                }
                if (state[v] == 1) {
                    cycles++;
                }
                for (int u : stack) {
                    state[u] = 2;
                }
            }

            writer.write(String.valueOf(cycles));
            writer.newLine();
            writer.flush();
        }
    }
}