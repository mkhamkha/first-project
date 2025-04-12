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

            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < 31; i++) {
                if (a[i] > 0) {
                    list.add(i);
                }
            }

            list.sort((x, y) -> {
                double ratioY = (double) a[y] / pow2(y);
                double ratioX = (double) a[x] / pow2(x);
                return Double.compare(ratioY, ratioX);
            });

            long totalSeconds = 0L;
            long totalCost = 0L;

            for (int i : list) {
                if (totalSeconds >= M) break;
                long maxK = (M - totalSeconds) / a[i];
                if (maxK <= 0) continue;

                totalSeconds += maxK * a[i];
                totalCost += maxK * pow2(i);
            }

            while (totalSeconds < M) {
                boolean added = false;
                for (int i : list) {
                    totalSeconds += a[i];
                    totalCost += pow2(i);
                    added = true;
                    break;
                }
                if (!added) {
                    System.out.println("Impossible");
                    return;
                }
            }

            System.out.println(totalCost);
        }
    }

    private static long pow2(int i) {
        return (long) Math.pow(2, i);
    }
}