package yandex.yy7.paket3;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Task8 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = Integer.parseInt(br.readLine().trim());
            Fenwick3D fenwick = new Fenwick3D(n);
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line);
                int t = Integer.parseInt(st.nextToken());
                if (t == 1) {
                    int x = Integer.parseInt(st.nextToken()) + 1;
                    int y = Integer.parseInt(st.nextToken()) + 1;
                    int z = Integer.parseInt(st.nextToken()) + 1;
                    long v = Long.parseLong(st.nextToken());
                    fenwick.update(x, y, z, v);
                } else if (t == 2) {
                    int x1 = Integer.parseInt(st.nextToken()) + 1;
                    int y1 = Integer.parseInt(st.nextToken()) + 1;
                    int z1 = Integer.parseInt(st.nextToken()) + 1;
                    int x2 = Integer.parseInt(st.nextToken()) + 1;
                    int y2 = Integer.parseInt(st.nextToken()) + 1;
                    int z2 = Integer.parseInt(st.nextToken()) + 1;
                    long res = fenwick.rangeSum(x1, y1, z1, x2, y2, z2);
                    bw.write(Long.toString(res));
                    bw.newLine();
                } else break;
            }
            bw.flush();
        }
    }

    static class Fenwick3D {
        private final int n;
        private final List<List<Fenwick>> bit;

        Fenwick3D(int size) {
            this.n = size;
            this.bit = IntStream.rangeClosed(0, n)
                    .mapToObj(i -> IntStream.rangeClosed(0, n)
                            .mapToObj(j -> new Fenwick(n))
                            .collect(Collectors.toList()))
                    .collect(Collectors.toList());
        }

        void update(int x, int y, int z, long v) {
            IntStream.iterate(x, i -> i <= n, i -> i + (i & -i))
                    .forEach(i -> IntStream.iterate(y, j -> j <= n, j -> j + (j & -j))
                            .forEach(j -> bit.get(i).get(j).add(z, v)));
        }

        long prefixSum(int x, int y, int z) {
            return IntStream.iterate(x, i -> i > 0, i -> i - (i & -i))
                    .mapToLong(i -> IntStream.iterate(y, j -> j > 0, j -> j - (j & -j))
                            .mapToLong(j -> bit.get(i).get(j).sum(z))
                            .sum())
                    .sum();
        }

        long rangeSum(int x1, int y1, int z1, int x2, int y2, int z2) {
            return prefixSum(x2,y2,z2)
                    - prefixSum(x1-1,y2,z2)
                    - prefixSum(x2,y1-1,z2)
                    - prefixSum(x2,y2,z1-1)
                    + prefixSum(x1-1,y1-1,z2)
                    + prefixSum(x1-1,y2,z1-1)
                    + prefixSum(x2,y1-1,z1-1)
                    - prefixSum(x1-1,y1-1,z1-1);
        }
    }

    static class Fenwick {
        private final int n;
        private final long[] f;

        Fenwick(int size) {
            this.n = size;
            this.f = new long[n+1];
        }

        void add(int i, long v) {
            IntStream.iterate(i, idx -> idx <= n, idx -> idx + (idx & -idx))
                    .forEach(idx -> f[idx] += v);
        }

        long sum(int i) {
            return IntStream.iterate(i, idx -> idx > 0, idx -> idx - (idx & -idx))
                    .mapToLong(idx -> f[idx])
                    .sum();
        }
    }
}
