package yandex.yy7.paket1;

import java.io.*;
import java.util.*;

public class Task6 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            int maxW = Integer.parseInt(st.nextToken());

            int[] weights = new int[n];
            int[] costs = new int[n];

            st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                weights[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                costs[i] = Integer.parseInt(st.nextToken());
            }

            int[][] dp = new int[n + 1][maxW + 1];

            for (int i = 1; i <= n; i++) {
                for (int w = 0; w <= maxW; w++) {
                    dp[i][w] = dp[i - 1][w];
                    if (w >= weights[i - 1]) {
                        dp[i][w] = Math.max(dp[i][w], dp[i - 1][w - weights[i - 1]] + costs[i - 1]);
                    }
                }
            }
            int w = maxW;
            ArrayList<Integer> chosen = new ArrayList<>();
            for (int i = n; i >= 1; i--) {
                if (w >= weights[i - 1] && dp[i][w] == dp[i - 1][w - weights[i - 1]] + costs[i - 1]) {
                    chosen.add(i);
                    w -= weights[i - 1];
                }
            }

            Collections.sort(chosen);
            for (int item : chosen) {
                System.out.println(item);
            }
        }
    }
}