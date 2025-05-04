package yandex.yy7.paket4;

import java.io.*;
import java.util.*;

public class Task5 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader  reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter  writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = Integer.parseInt(reader.readLine().trim());
            StringTokenizer st = new StringTokenizer(reader.readLine());
            List<Integer> rating = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                rating.add(Integer.parseInt(st.nextToken()));
            }
            int[] prev = new int[n];
            int[] next = new int[n];
            int[] leave = new int[n];
            boolean[] out = new boolean[n];
            for (int i = 0; i < n; i++) {
                prev[i] = (i + n - 1) % n;
                next[i] = (i + 1) % n;
            }


            List<Integer> curr = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int l = prev[i], r = next[i];
                if (rating.get(i) < rating.get(l) && rating.get(i) < rating.get(r)) {
                    curr.add(i);
                }
            }

            int round = 1, alive = n;
            while (!curr.isEmpty() && alive > 2) {

                for (int i : curr) {
                    if (!out[i]) {
                        out[i] = true;
                        leave[i] = round;
                        alive--;
                    }
                }

                for (int i : curr) {
                    int l = prev[i], r = next[i];
                    next[l] = r;
                    prev[r] = l;
                }

                List<Integer> nextRound = new ArrayList<>();
                Set<Integer> seen = new HashSet<>();
                for (int i : curr) {
                    for (int j : new int[]{ prev[i], next[i] }) {
                        if (out[j] || seen.contains(j)) continue;
                        seen.add(j);
                        int ll = prev[j], rr = next[j];
                        if (rating.get(j) < rating.get(ll) && rating.get(j) < rating.get(rr)) {
                            nextRound.add(j);
                        }
                    }
                }
                curr = nextRound;
                round++;
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append(leave[i]).append(' ');
            }
            writer.write(sb.toString().trim());
            writer.newLine();
            writer.flush();
        }
    }
}