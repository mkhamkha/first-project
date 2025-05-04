package yandex.yy7.paket4;

import java.io.*;
import java.util.*;

public class Task3 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            Deque<Integer> deque = new ArrayDeque<>();
            String line;
            while ((line = reader.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line);
                String command = st.nextToken();
                switch (command) {
                    case "push_front":
                        deque.addFirst(Integer.parseInt(st.nextToken()));
                        writer.write("ok");
                        writer.newLine();
                        break;
                    case "push_back":
                        deque.addLast(Integer.parseInt(st.nextToken()));
                        writer.write("ok");
                        writer.newLine();
                        break;
                    case "pop_front":
                        if (deque.isEmpty()) {
                            writer.write("error");
                        } else {
                            writer.write(deque.removeFirst().toString());
                        }
                        writer.newLine();
                        break;
                    case "pop_back":
                        if (deque.isEmpty()) {
                            writer.write("error");
                        } else {
                            writer.write(deque.removeLast().toString());
                        }
                        writer.newLine();
                        break;
                    case "front":
                        if (deque.isEmpty()) {
                            writer.write("error");
                        } else {
                            writer.write(deque.peekFirst().toString());
                        }
                        writer.newLine();
                        break;
                    case "back":
                        if (deque.isEmpty()) {
                            writer.write("error");
                        } else {
                            writer.write(deque.peekLast().toString());
                        }
                        writer.newLine();
                        break;
                    case "size":
                        writer.write(String.valueOf(deque.size()));
                        writer.newLine();
                        break;
                    case "clear":
                        deque.clear();
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