package yandex.yy7.paket3;

import java.io.*;
import java.util.*;

public class Task5 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());

            long label = x ^ y;
            bw.write(String.valueOf(label));
            bw.newLine();

            st = new StringTokenizer(br.readLine());
            x = Long.parseLong(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            long d = x ^ c;
            bw.write(String.valueOf(d));
            bw.newLine();
        }
    }
}