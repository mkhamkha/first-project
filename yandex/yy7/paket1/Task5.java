package yandex.yy7.paket1;

import java.io.*;
import java.util.*;

public class Task5 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer st = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(st.nextToken());
            int maxWeight = Integer.parseInt(st.nextToken());

            int[] weights = new int[n];
            int[] values = new int[n];

            st = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                weights[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                values[i] = Integer.parseInt(st.nextToken());
            }

            int[] dp = new int[maxWeight + 1];
            Arrays.fill(dp, -1);
            dp[0] = 0;

            for (int i = 0; i < n; i++) {
                for (int w = maxWeight; w >= weights[i]; w--) {
                    if (dp[w - weights[i]] != -1) {
                        dp[w] = Math.max(dp[w], dp[w - weights[i]] + values[i]);
                    }
                }
            }

            int maxValue = 0;
            for (int val : dp) {
                maxValue = Math.max(maxValue, val);
            }

            System.out.println(maxValue);
        }
    }
}