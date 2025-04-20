package yandex.yy7.paket1;

import java.io.*;
import java.util.*;

public class Task2 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int t = Integer.parseInt(reader.readLine().trim());
            StringBuilder output = new StringBuilder();

            while (t-- > 0) {
                int n = Integer.parseInt(reader.readLine().trim());
                List<Integer> a = readIntList(reader, n);

                List<Integer> segments = getIntegerList(a);
                output.append(segments.size()).append("\n");

                StringJoiner joiner = new StringJoiner(" ");
                for (int len : segments) {
                    joiner.add(String.valueOf(len));
                }
                output.append(joiner).append("\n");
            }
            System.out.print(output);
        }
    }

    private static List<Integer> getIntegerList(List<Integer> a) {
        List<Integer> segments = new ArrayList<>();
        int currentPosition = 0;
        int n = a.size();

        while (currentPosition < n) {
            int maxLen = 0;
            int currentMin = Integer.MAX_VALUE;

            for (int len = 1; len <= n - currentPosition; len++) {
                currentMin = Math.min(currentMin, a.get(currentPosition + len - 1));
                if (currentMin >= len) {
                    maxLen = len;
                } else {
                    break;
                }
            }

            segments.add(maxLen);
            currentPosition += maxLen;
        }
        return segments;
    }

    private static List<Integer> readIntList(BufferedReader reader, int size) throws IOException {
        List<Integer> list = new ArrayList<>(size);
        StringTokenizer st = new StringTokenizer(reader.readLine());
        for (int i = 0; i < size; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }
        return list;
    }
}