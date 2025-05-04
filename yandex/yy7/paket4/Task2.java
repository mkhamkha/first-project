package yandex.yy7.paket4;

import java.io.*;
import java.util.*;

public class Task2 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            Deque<Integer> queue = new ArrayDeque<>();
            String line;
            while ((line = reader.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line);
                String command = st.nextToken();
                switch (command) {
                    case "push":
                        queue.addLast(Integer.parseInt(st.nextToken()));
                        writer.write("ok");
                        writer.newLine();
                        break;
                    case "pop":
                        if (queue.isEmpty()) {
                            writer.write("error");
                        } else {
                            writer.write(queue.removeFirst().toString());
                        }
                        writer.newLine();
                        break;
                    case "front":
                        if (queue.isEmpty()) {
                            writer.write("error");
                        } else {
                            writer.write(queue.peekFirst().toString());
                        }
                        writer.newLine();
                        break;
                    case "size":
                        writer.write(String.valueOf(queue.size()));
                        writer.newLine();
                        break;
                    case "clear":
                        queue.clear();
                        writer.write("ok");
                        writer.newLine();
                        break;
                    case "exit":
                        writer.write("bye");
                        writer.newLine();
                        writer.flush();
                        return;
                }
            }
        }
    }
}