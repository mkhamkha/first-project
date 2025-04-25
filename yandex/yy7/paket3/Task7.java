package yandex.yy7.paket3;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Task7 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()), q = Integer.parseInt(st.nextToken());

            List<Long> arr = new ArrayList<>(Collections.nCopies(n + 1, 0L));
            Fenwick fenwick = new Fenwick(n);

            StringBuilder out = new StringBuilder();
            while (q-- > 0) {
                st = new StringTokenizer(br.readLine());
                String cmd = st.nextToken();
                if (cmd.equals("A")) {
                    int i = Integer.parseInt(st.nextToken());
                    long x = Long.parseLong(st.nextToken());
                    long delta = x - arr.get(i);
                    arr.set(i, x);
                    fenwick.update(i, delta);
                } else {
                    int l = Integer.parseInt(st.nextToken());
                    int r = Integer.parseInt(st.nextToken());
                    long sum = fenwick.prefixSum(r) - fenwick.prefixSum(l - 1);
                    out.append(sum).append('\n');
                }
            }
            bw.write(out.toString());
        }
    }

    static class Fenwick {
        private final int n;
        private final long[] f;
        Fenwick(int size) {
            this.n = size;
            this.f = new long[n + 1];
        }

        void update(int i, long d) {
            IntStream.iterate(i, idx -> idx <= n, idx -> idx + (idx & -idx))
                    .forEach(idx -> f[idx] += d);
        }

        long prefixSum(int i) {
            return IntStream.iterate(i, idx -> idx > 0, idx -> idx - (idx & -idx))
                    .mapToLong(idx -> f[idx])
                    .sum();
        }
    }
}