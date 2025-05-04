package yandex.yy7.paket4;

import java.io.*;
import java.util.*;

public class Task7 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader  reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter  writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            StringTokenizer st = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            DisjointSet dsu = new DisjointSet(n);
            int answer = 0;

            for (int i = 1; i <= m; i++) {
                st = new StringTokenizer(reader.readLine());
                int u = Integer.parseInt(st.nextToken()) - 1;
                int v = Integer.parseInt(st.nextToken()) - 1;

                if (dsu.union(u, v) && dsu.count() == 1) {
                    answer = i;
                    for (int j = i + 1; j <= m; j++) {
                        reader.readLine();
                    }
                    break;
                }
            }

            writer.write(String.valueOf(answer));
            writer.newLine();
            writer.flush();
        }
    }

    static class DisjointSet {
        private final int[] parent, rank;
        private int components;

        DisjointSet(int n) {
            parent = new int[n];
            rank   = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i]   = 0;
            }
            components = n;
        }

        int find(int x) {
            return parent[x] == x ? x : (parent[x] = find(parent[x]));
        }

        boolean union(int a, int b) {
            int ra = find(a), rb = find(b);
            if (ra == rb) return false;

            if (rank[ra] < rank[rb]) {
                parent[ra] = rb;
            } else if (rank[rb] < rank[ra]) {
                parent[rb] = ra;
            } else {
                parent[rb] = ra;
                rank[ra]++;
            }
            components--;
            return true;
        }

        int count() {
            return components;
        }
    }
}