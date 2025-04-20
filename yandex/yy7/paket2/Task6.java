package yandex.yy7.paket2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Task6 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[] arr = new int[n + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            SegmentTree seg = new SegmentTree(arr);

            StringBuilder out = new StringBuilder();
            IntStream.range(0, m).forEach(_q -> {
                try {
                    StringTokenizer qs = new StringTokenizer(br.readLine());
                    int t = Integer.parseInt(qs.nextToken());
                    int i = Integer.parseInt(qs.nextToken());
                    int x = Integer.parseInt(qs.nextToken());
                    if (t == 0) {
                        seg.update(i, x);
                    } else {
                        int k = seg.queryFirst(i, x);
                        out.append(k).append('\n');
                    }
                } catch (IOException ignore) {}
            });

            System.out.print(out);
        }
    }

    static class SegmentTree {
        private final int n;
        private final int[] tree;

        SegmentTree(int[] arr) {
            this.n = arr.length - 1;
            int size = 1;
            while (size < n) size <<= 1;
            tree = new int[2 * size];
            for (int i = 1; i <= n; i++) {
                tree[size + i - 1] = arr[i];
            }
            for (int v = size - 1; v > 0; v--) {
                tree[v] = Math.max(tree[2 * v], tree[2 * v + 1]);
            }
        }

        void update(int pos, int value) {
            int idx = pos + (tree.length >> 1) - 1;
            tree[idx] = value;
            for (idx >>= 1; idx > 0; idx >>= 1) {
                tree[idx] = Math.max(tree[2 * idx], tree[2 * idx + 1]);
            }
        }

        int queryFirst(int i, int x) {
            return queryFirstRec(1, 1, tree.length >> 1, i, x);
        }

        private int queryFirstRec(int v, int l, int r, int i, int x) {
            if (r < i || tree[v] < x) return -1;
            if (l == r) return l;
            int m = (l + r) >> 1;
            int res = queryFirstRec(2 * v, l, m, i, x);
            return res != -1
                    ? res
                    : queryFirstRec(2 * v + 1, m + 1, r, i, x);
        }
    }
}