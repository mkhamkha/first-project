package yandex.yy7.paket2;

import java.io.*;
import java.util.*;

public class Task1 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int n = Integer.parseInt(reader.readLine());
            long[] array = new long[n];

            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                array[i] = Long.parseLong(tokenizer.nextToken());
            }

            SegmentTree segmentTree = new SegmentTree(array);
            int k = Integer.parseInt(reader.readLine());
            StringBuilder output = new StringBuilder();
            for (int i = 0; i < k; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                int l = Integer.parseInt(tokenizer.nextToken()) - 1;
                int r = Integer.parseInt(tokenizer.nextToken()) - 1;

                Result result = segmentTree.query(l, r);
                output.append(result.max).append(" ").append(result.count).append("\n");
            }

            System.out.print(output);
        }
    }

    static class Result {
        long max;
        long count;

        Result(long max, long count) {
            this.max = max;
            this.count = count;
        }
    }

    static class SegmentTree {
        private final Result[] tree;
        private final long[] array;

        public SegmentTree(long[] array) {
            this.array = array;
            int n = array.length;
            tree = new Result[4 * n];
            build(1, 0, n - 1);
        }

        private void build(int node, int start, int end) {
            if (start == end) {
                tree[node] = new Result(array[start], 1);
                return;
            }
            int mid = (start + end) / 2;
            int leftNode = 2 * node;
            int rightNode = 2 * node + 1;
            build(leftNode, start, mid);
            build(rightNode, mid + 1, end);
            tree[node] = merge(tree[leftNode], tree[rightNode]);
        }

        private Result merge(Result left, Result right) {
            if (left.max > right.max) {
                return new Result(left.max, left.count);
            } else if (right.max > left.max) {
                return new Result(right.max, right.count);
            } else {
                return new Result(left.max, left.count + right.count);
            }
        }

        public Result query(int l, int r) {
            return query(1, 0, array.length - 1, l, r);
        }

        private Result query(int node, int start, int end, int l, int r) {
            if (l > end || r < start) {
                return null;
            }
            if (l <= start && end <= r) {
                return tree[node];
            }
            int mid = (start + end) / 2;
            int leftNode = 2 * node;
            int rightNode = 2 * node + 1;
            Result leftResult = query(leftNode, start, mid, l, r);
            Result rightResult = query(rightNode, mid + 1, end, l, r);
            if (leftResult == null) return rightResult;
            if (rightResult == null) return leftResult;
            return merge(leftResult, rightResult);
        }
    }
}