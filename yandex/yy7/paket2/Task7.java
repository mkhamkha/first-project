package yandex.yy7.paket2;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Task7 {

    static class Node {
        final int len, pref, suf, best;
        Node(int len, int pref, int suf, int best) {
            this.len = len; this.pref = pref; this.suf = suf; this.best = best;
        }
        static Node of(int isZero) {
            return new Node(1, isZero, isZero, isZero);
        }
        static Node empty() {
            return new Node(0, 0, 0, 0);
        }
        static Node merge(Node L, Node R) {
            if (L.len == 0) return R;
            if (R.len == 0) return L;
            int len  = L.len + R.len;
            int pref = (L.pref == L.len) ? L.len + R.pref : L.pref;
            int suf  = (R.suf == R.len) ? R.len + L.suf : R.suf;
            int best = Math.max(Math.max(L.best, R.best), L.suf + R.pref);
            return new Node(len, pref, suf, best);
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(br.readLine().trim());
            int[] a = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }

            // build tree
            int size = 1;
            while (size < n) size <<= 1;
            List<Node> tree = new ArrayList<>(Collections.nCopies(2 * size, Node.empty()));
            int finalSize = size;
            IntStream.range(0, n).forEach(i ->
                    tree.set(finalSize + i, Node.of(a[i] == 0 ? 1 : 0))
            );
            IntStream.iterate(size - 1, v -> v > 0, v -> v - 1)
                    .forEach(v ->
                            tree.set(v, Node.merge(tree.get(v<<1), tree.get(v<<1|1)))
                    );

            int M = Integer.parseInt(br.readLine().trim());
            StringBuilder out = new StringBuilder();

            while (M-- > 0) {
                st = new StringTokenizer(br.readLine());
                String cmd = st.nextToken();
                if ("UPDATE".equals(cmd)) {
                    int pos = Integer.parseInt(st.nextToken()) - 1;
                    int isZero = Integer.parseInt(st.nextToken()) == 0 ? 1 : 0;
                    int idx = size + pos;
                    tree.set(idx, Node.of(isZero));
                    while ((idx >>= 1) > 0) {
                        tree.set(idx, Node.merge(tree.get(idx<<1), tree.get(idx<<1|1)));
                    }
                } else {
                    int l = Integer.parseInt(st.nextToken()) - 1 + size;
                    int r = Integer.parseInt(st.nextToken()) - 1 + size;
                    List<Node> left  = new ArrayList<>();
                    List<Node> right = new ArrayList<>();

                    while (l <= r) {
                        if ((l & 1) == 1) left.add(tree.get(l++));
                        if ((r & 1) == 0) right.add(tree.get(r--));
                        l >>= 1; r >>= 1;
                    }
                    Collections.reverse(right);

                    Node ans = Stream.concat(left.stream(), right.stream())
                            .reduce(Node::merge)
                            .orElse(Node.empty());
                    out.append(ans.best).append('\n');
                }
            }

            System.out.print(out);
        }
    }
}
