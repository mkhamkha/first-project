package yandex.yy7.paket3;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Task3 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = Integer.parseInt(br.readLine().trim());
            StringTokenizer st = new StringTokenizer(br.readLine());
            List<Long> a = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                a.add(Long.parseLong(st.nextToken()));
            }

            List<Integer> p = a.stream()
                    .map(Long::bitCount)
                    .toList();
            int total = p.stream().mapToInt(Integer::intValue).sum();
            if ((total & 1) == 1) {
                bw.write("impossible\n");
                return;
            }

            List<Integer> reps = new ArrayList<>(total);
            IntStream.range(0, n).forEach(i -> {
                for (int k = 0; k < p.get(i); k++) {
                    reps.add(i);
                }
            });

            List<Long> b = IntStream.range(0, n)
                    .mapToObj(i -> 0L)
                    .collect(Collectors.toList());

            int col = 0;
            for (int j = 0; j < reps.size(); j += 2) {
                int u = reps.get(j), v = reps.get(j + 1);
                b.set(u, b.get(u) | (1L << col));
                b.set(v, b.get(v) | (1L << col));
                col++;
            }

            for (Long x : b) {
                bw.write(x + " ");
            }
            bw.newLine();
        }
    }
}
