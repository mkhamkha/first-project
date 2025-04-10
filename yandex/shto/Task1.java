package yandex.shto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Task1 {
    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine().trim());
            List<Cluster> clusters = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(reader.readLine());
                int a = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                clusters.add(new Cluster(a, x, i));
            }

            Collections.sort(clusters);

            int[] dp = new int[n];
            int[] prev = new int[n];

            dp[0] = clusters.get(0).duration;
            prev[0] = -1;

            for (int i = 1; i < n; i++) {
                int j = binarySearch(clusters, i);

                int option1 = dp[i - 1];
                int option2 = clusters.get(i).duration + (j != -1 ? dp[j] : 0);

                if (option1 > option2) {
                    dp[i] = option1;
                    prev[i] = prev[i - 1];
                } else {
                    dp[i] = option2;
                    prev[i] = j;
                }
            }

            int maxTotal = dp[n - 1];
            List<Cluster> chosen = new ArrayList<>();
            for (int i = n - 1; i >= 0;) {
                if (i > 0 && dp[i] == dp[i - 1]) {
                    i--;
                } else {
                    chosen.add(clusters.get(i));
                    i = prev[i];
                }
            }

            StringBuilder builder = new StringBuilder();
            for (Cluster cluster : chosen) {
                builder.append(cluster.index).append(" ");
            }
            System.out.println(maxTotal + "\n" + builder.toString().trim());
        }
    }

    private static int binarySearch(List<Cluster> clusters, int i) {
        int left = 0;
        int right = i - 1;
        int res = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (clusters.get(mid).end <= clusters.get(i).start) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }

    private static class Cluster implements Comparable<Cluster> {
        int start;
        int duration;
        int end;
        int index;

        public Cluster(int start, int duration, int index) {
            this.start = start;
            this.duration = duration;
            this.end = start + duration;
            this.index = index;
        }

        @Override
        public int compareTo(Cluster other) {
            return Integer.compare(this.end, other.end);
        }
    }
}