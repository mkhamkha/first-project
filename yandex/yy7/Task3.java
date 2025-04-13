package yandex.yy7;
import java.io.*;
import java.util.*;

public class Task3 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            long M = Long.parseLong(reader.readLine().trim());
            long[] a = new long[31];
            StringTokenizer st = new StringTokenizer(reader.readLine());
            for (int i = 0; i < 31; i++) {
                a[i] = Long.parseLong(st.nextToken());
            }

            for (int i = 1; i < 31; i++) {
                a[i] = Math.max(a[i], 2 * a[i-1]);
            }

            long answer = Long.MAX_VALUE;
            long cost = 0;
            long remaining = M;

            for (int i = 30; i >= 0; i--) {
                long cnt = remaining / a[i];
                cost += cnt * (1L << i);
                remaining -= cnt * a[i];

                answer = Math.min(answer, cost + (remaining > 0 ? (1L << i) : 0));
            }

            System.out.println(answer);
        }
    }
}