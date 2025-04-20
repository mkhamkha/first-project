package yandex.yy7.paket2;

import java.io.*;
import java.util.*;

public class Task5 {
    public static void main(String[] args) throws IOException {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(br.readLine().trim());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] a = new int[n+1];
            FenwickTree fenw = new FenwickTree(n);
            for (int i = 1; i <= n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
                if (a[i] == 0) fenw.update(i, 1);
            }

            int m = Integer.parseInt(br.readLine().trim());
            StringBuilder out = new StringBuilder();
            while (m-- > 0) {
                st = new StringTokenizer(br.readLine());
                char cmd = st.nextToken().charAt(0);
                if (cmd == 'u') {
                    int pos = Integer.parseInt(st.nextToken());
                    int v   = Integer.parseInt(st.nextToken());
                    if ((a[pos] == 0) ^ (v == 0)) {
                        fenw.update(pos, v == 0 ? 1 : -1);
                    }
                    a[pos] = v;
                } else {
                    int l = Integer.parseInt(st.nextToken());
                    int r = Integer.parseInt(st.nextToken());
                    int k = Integer.parseInt(st.nextToken());
                    int zeros = fenw.prefixSum(r) - fenw.prefixSum(l - 1);
                    if (zeros < k) {
                        out.append(-1).append(' ');
                    } else {
                        int before = fenw.prefixSum(l - 1);
                        out.append(fenw.findByPrefix(before + k)).append(' ');
                    }
                }
            }
            System.out.print(out.toString().trim());
        }
    }

    static class FenwickTree {
        private final int size;
        private final int highestOneBit;
        private final int[] tree;

        public FenwickTree(int size) {
            this.size = size;
            this.tree = new int[size + 1];
            this.highestOneBit = Integer.highestOneBit(size);
        }

        public void update(int index, int delta) {
            while (index <= size) {
                tree[index] += delta;
                index += index & -index;
            }
        }

        public int prefixSum(int index) {
            int sum = 0;
            while (index > 0) {
                sum += tree[index];
                index -= index & -index;
            }
            return sum;
        }

        public int findByPrefix(int k) {
            int idx = 0, accumulated = 0;
            for (int bit = highestOneBit; bit > 0; bit >>= 1) {
                int candidate = idx + bit;
                if (candidate <= size && accumulated + tree[candidate] < k) {
                    accumulated += tree[candidate];
                    idx = candidate;
                }
            }
            return idx + 1;
        }
    }
}