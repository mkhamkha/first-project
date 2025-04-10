package yandex.shto;

import java.io.*;
import java.util.*;
public class Task2 {
    private static Map<Long, Integer> idToIndex = new HashMap<>();
    private static Map<Integer, Long> indexToId = new HashMap<>();
    private static Map<Integer, List<Integer>> edges = new HashMap<>();
    private static List<String> color;
    private static int index = 0;
    private static int[] component;
    private static int componentNum = 0;

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine().trim());

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(reader.readLine());
                long a = Long.parseLong(st.nextToken());
                long b = Long.parseLong(st.nextToken());

                int idxA = register(a);
                int idxB = register(b);

                edges.computeIfAbsent(idxA, k -> new ArrayList<>()).add(idxB);
                edges.computeIfAbsent(idxB, k -> new ArrayList<>()).add(idxA);
            }

            initializeColor(index);
            component = new int[index];

            for (int i = 0; i < index; i++) {
                if (color.get(i).equals("white")) {
                    DFS(i);
                    componentNum++;
                }
            }

            int q = Integer.parseInt(reader.readLine().trim());

            for (int i = 0; i < q; i++) {
                StringTokenizer st = new StringTokenizer(reader.readLine());
                long x = Long.parseLong(st.nextToken());
                int k = Integer.parseInt(st.nextToken());

                StringTokenizer yLine = new StringTokenizer(reader.readLine());
                long[] sources = new long[k];
                for (int j = 0; j < k; j++) {
                    sources[j] = Long.parseLong(yLine.nextToken());
                    register(sources[j]);
                }
                register(x);

                int idxX = idToIndex.get(x);
                int compX = component[idxX];

                List<Long> result = new ArrayList<>();
                for (long src : sources) {
                    int idx = idToIndex.get(src);
                    if (component[idx] == compX) {
                        result.add(src);
                    }
                }

                StringBuilder builder = new StringBuilder();
                builder.append(result.size());
                for (long server : result) {
                    builder.append(" ").append(server);
                }
                System.out.println(builder.toString().trim());
            }
        }
    }

    private static int register(long id) {
        if (!idToIndex.containsKey(id)) {
            idToIndex.put(id, index);
            indexToId.put(index, id);
            index++;
        }
        return idToIndex.get(id);
    }

    private static void initializeColor(int numVertices) {
        color = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            color.add("white");
        }
    }

    private static void DFS(int startVertex) {
        Stack<Integer> stack = new Stack<>();
        stack.push(startVertex);

        while (!stack.isEmpty()) {
            int v = stack.pop();

            if (color.get(v).equals("white")) {
                color.set(v, "gray");
                component[v] = componentNum;
                stack.push(v);

                List<Integer> neighbors = edges.getOrDefault(v, new ArrayList<>());
                for (int i = neighbors.size() - 1; i >= 0; i--) {
                    int w = neighbors.get(i);
                    if (color.get(w).equals("white")) {
                        stack.push(w);
                    }
                }
            } else if (color.get(v).equals("gray")) {
                color.set(v, "black");
            }
        }
    }
}
