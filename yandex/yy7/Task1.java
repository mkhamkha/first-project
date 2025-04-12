package yandex.yy7;

import java.io.*;
import java.util.*;
public class Task1 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(tokenizer.nextToken());
            int m = Integer.parseInt(tokenizer.nextToken());

            int[] groups = readIntArray(n, new StringTokenizer(reader.readLine()));
            int[] rooms = readIntArray(m, new StringTokenizer(reader.readLine()));

            List<Group> groupList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                groupList.add(new Group(groups[i], i));
            }

            List<Room> roomList = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                roomList.add(new Room(rooms[i], i + 1));
            }

            Collections.sort(groupList);
            Collections.sort(roomList);

            int[] assigned = new int[n];
            Arrays.fill(assigned, 0);

            int groupPtr = 0;
            int roomPtr = 0;
            int count = 0;

            while (groupPtr < n && roomPtr < m) {
                Group group = groupList.get(groupPtr);
                Room room = roomList.get(roomPtr);
                if (room.capacity >= group.size + 1) {
                    assigned[group.index] = room.index;
                    count++;
                    groupPtr++;
                }
                roomPtr++;
            }

            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < n; i++) {
                builder.append(assigned[i]).append(" ");
            }
            System.out.println(count + "\n" + builder.toString().trim());
        }
    }

    private static int[] readIntArray(int size, StringTokenizer tokenizer) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = Integer.parseInt(tokenizer.nextToken());
        }
        return array;
    }

    static class Group implements Comparable<Group> {
        int size;
        int index;

        public Group(int size, int index) {
            this.size = size;
            this.index = index;
        }

        @Override
        public int compareTo(Group other) {
            return Integer.compare(this.size, other.size);
        }
    }

    static class Room implements Comparable<Room> {
        int capacity;
        int index;

        public Room(int capacity, int index) {
            this.capacity = capacity;
            this.index = index;
        }

        @Override
        public int compareTo(Room other) {
            return Integer.compare(this.capacity, other.capacity);
        }
    }
}