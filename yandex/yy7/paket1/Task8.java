package yandex.yy7.paket1;

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

            long evenOrdersTotal = 0;
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
                    evenOrdersTotal += Math.max(oddCount, evenCount);
                } else {
                    oddOrders.add(new OddOrder(oddCount, evenCount));
                }
            }

            long oddBaseline = 0;
            for (OddOrder order : oddOrders) {
                oddBaseline += order.evenCount;
            }
            int k = oddOrders.size();
            int oddStateSlots = (k + 1) / 2;

            Collections.sort(oddOrders);

            long oddExtra = 0;
            for (int i = 0; i < oddStateSlots && i < oddOrders.size(); i++) {
                oddExtra += oddOrders.get(i).difference;
            }
            long oddOrdersTotal = oddBaseline + oddExtra;

            System.out.println(evenOrdersTotal + oddOrdersTotal);
        }
    }
}
