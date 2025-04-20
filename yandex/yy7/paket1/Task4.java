package yandex.yy7.paket1;

import java.io.*;
import java.util.*;

public class Task4 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(st.nextToken());
            int maxWeight = Integer.parseInt(st.nextToken());

            int[] weights = new int[n];
            st = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                weights[i] = Integer.parseInt(st.nextToken());
            }

            boolean[] dp = new boolean[maxWeight + 1];
            dp[0] = true;

            for (int i = 0; i < n; i++) {
                for (int w = maxWeight; w >= weights[i]; w--) {
                    if (dp[w - weights[i]]) {
                        dp[w] = true;
                    }
                }
            }

            for (int w = maxWeight; w >= 0; w--) {
                if (dp[w]) {
                    System.out.println(w);
                    break;
                }
            }
        }
    }
}
