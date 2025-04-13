package yandex.yy7;

import java.io.*;
import java.util.*;

public class Task8 {
    // Класс для хранения заказа с нечётной длиной
    static class OddOrder implements Comparable<OddOrder> {
        int oddCount;    // Количество символов 'S' на нечётных позициях
        int evenCount;   // Количество символов 'S' на чётных позициях
        int difference;  // Разница

        OddOrder(int oddCount, int evenCount) {
            this.oddCount = oddCount;
            this.evenCount = evenCount;
            this.difference = oddCount - evenCount;
        }

        @Override
        public int compareTo(OddOrder other) {
            return Integer.compare(other.difference, this.difference);
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine().trim());

            long evenSum = 0;
            List<OddOrder> oddOrders = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                String order = reader.readLine().trim();
                int L = order.length();
                int oddCount = 0, evenCount = 0;
                for (int j = 0; j < L; j++) {
                    if (order.charAt(j) == 'S') {
                        if (j % 2 == 0) {
                            oddCount++;
                        } else {
                            evenCount++;
                        }
                    }
                }
                if (L % 2 == 0) {
                    evenSum += Math.max(oddCount, evenCount);
                } else {
                    oddOrders.add(new OddOrder(oddCount, evenCount));
                }
            }

            long oddSum = 0;
            for (OddOrder order : oddOrders) {
                oddSum += order.evenCount;
            }

            int k = oddOrders.size();
            int p = (k + 1) / 2;

            Collections.sort(oddOrders);

            long extra = 0;
            for (int i = 0; i < p && i < oddOrders.size(); i++) {
                extra += oddOrders.get(i).difference;
            }
            oddSum += extra;

            System.out.println(evenSum + oddSum);
        }
    }
}