package yandex.yy7.paket3;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Task6 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());

            List<BitSet> ay = IntStream.range(0, n).mapToObj(i -> new BitSet(n)).toList();
            List<BitSet> ax = IntStream.range(0, n).mapToObj(i -> new BitSet(n)).toList();
            List<BitSet> az = IntStream.range(0, n).mapToObj(i -> new BitSet(n)).toList();

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()) - 1;
                int y = Integer.parseInt(st.nextToken()) - 1;
                int z = Integer.parseInt(st.nextToken()) - 1;
                ay.get(x).set(z);
                ax.get(y).set(z);
                az.get(x).set(y);
            }

            List<BitSet> fy = ay.stream().map(bs -> {
                BitSet c = (BitSet) bs.clone();
                c.flip(0, n);
                return c;
            }).toList();
            List<BitSet> fx = ax.stream().map(bs -> {
                BitSet c = (BitSet) bs.clone();
                c.flip(0, n);
                return c;
            }).toList();
            List<BitSet> fz = az.stream().map(bs -> {
                BitSet c = (BitSet) bs.clone();
                c.flip(0, n);
                return c;
            }).toList();

            Optional<int[]> cell = IntStream.range(0, n)
                    .boxed()
                    .flatMap(i -> fz.get(i).stream().mapToObj(j -> new int[]{i, j}))
                    .map(pair -> {
                        int i = pair[0], j = pair[1];
                        BitSet kz = (BitSet) fy.get(i).clone();
                        kz.and(fx.get(j));
                        int k = kz.nextSetBit(0);
                        return k < 0 ? null : new int[]{i, j, k};
                    })
                    .filter(Objects::nonNull)
                    .findFirst();

            if (cell.isPresent()) {
                int[] c = cell.get();
                bw.write("NO\n");
                bw.write((c[0] + 1) + " " + (c[1] + 1) + " " + (c[2] + 1) + "\n");
            } else {
                bw.write("YES\n");
            }
        }
    }
}