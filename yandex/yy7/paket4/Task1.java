package yandex.yy7.paket4;

import java.io.*;
import java.util.*;
public class Task1 {
    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            Deque<Integer> stack = new ArrayDeque<>();
            String line;
            while ((line = reader.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line);
                String command = st.nextToken();
                switch (command) {
                    case "push":
                        stack.push(Integer.parseInt(st.nextToken()));
                        writer.write("ok");
                        writer.newLine();
                        break;
                    case "pop":
                        if (stack.isEmpty()) {
                            writer.write("error");
                        } else {
                            writer.write(stack.pop().toString());
                        }
                        writer.newLine();
                        break;
                    case "back":
                        if (stack.isEmpty()) {
                            writer.write("error");
                        } else {
                            writer.write(stack.peek().toString());
                        }
                        writer.newLine();
                        break;
                    case "size":
                        writer.write(String.valueOf(stack.size()));
                        writer.newLine();
                        break;
                    case "clear":
                        stack.clear();
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
