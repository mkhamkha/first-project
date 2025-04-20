package yandex.yy7.paket2;

import java.io.*;
import java.util.*;

public class Task3 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(br.readLine().trim());
            int[] a = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) a[i] = Integer.parseInt(st.nextToken());

            int log = 32 - Integer.numberOfLeadingZeros(n);
            int[][] sp = new int[log][n];
            for (int i = 0; i < n; i++) sp[0][i] = i;
            for (int p = 1; p < log; p++) {
                int len = 1 << p, h = len >> 1;
                for (int i = 0; i + len <= n; i++) {
                    int x = sp[p - 1][i], y = sp[p - 1][i + h];
                    sp[p][i] = a[x] > a[y] ? x : y;
                }
            }

            int k = Integer.parseInt(br.readLine().trim());
            StringBuilder builder = new StringBuilder();
            while (k-- > 0) {
                st = new StringTokenizer(br.readLine());
                int l = Integer.parseInt(st.nextToken()) - 1;
                int r = Integer.parseInt(st.nextToken()) - 1;
                int len = r - l + 1, p = 31 - Integer.numberOfLeadingZeros(len), h = 1 << p;
                int i1 = sp[p][l], i2 = sp[p][r - h + 1];
                int idx = a[i1] > a[i2] ? i1 : i2;
                builder.append(a[idx]).append(' ').append(idx + 1).append('\n');
            }
            System.out.print(builder);
        }
    }
}