package yandex.yy7.paket2;

import java.io.*;
import java.util.*;

public class Task4 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(br.readLine().trim());
            int[] a = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }

            int size = 1 << (32 - Integer.numberOfLeadingZeros(n - 1));
            int[] seg = new int[2 * size];

            System.arraycopy(a, 0, seg, size, n);
            for (int i = size - 1; i > 0; i--) {
                seg[i] = Math.max(seg[i << 1], seg[(i << 1) | 1]);
            }

            int m = Integer.parseInt(br.readLine().trim());
            StringBuilder builder = new StringBuilder();

            while (m-- > 0) {
                st = new StringTokenizer(br.readLine());
                String type = st.nextToken();
                if (type.equals("s")) {
                    int l = Integer.parseInt(st.nextToken()) - 1 + size;
                    int r = Integer.parseInt(st.nextToken()) - 1 + size;
                    int ans = 0;
                    while (l <= r) {
                        if ((l & 1) == 1) ans = Math.max(ans, seg[l++]);
                        if ((r & 1) == 0) ans = Math.max(ans, seg[r--]);
                        l >>= 1;
                        r >>= 1;
                    }
                    builder.append(ans).append(' ');
                } else {
                    int pos = Integer.parseInt(st.nextToken()) - 1 + size;
                    int val = Integer.parseInt(st.nextToken());
                    seg[pos] = val;
                    for (pos >>= 1; pos > 0; pos >>= 1) {
                        seg[pos] = Math.max(seg[pos << 1], seg[(pos << 1) | 1]);
                    }
                }
            }

            System.out.print(builder);
        }
    }
}