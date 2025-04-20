package yandex.yy7.paket1;

import java.io.*;
import java.util.*;

public class Task7 {

    static class Brick {
        int length, index;
        Brick(int length, int index) {
            this.length = length;
            this.index = index;
        }
    }

    static boolean[][] reachable;
    static int[][] prev;

    public static void main(String[] args) throws IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(rd.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Brick>[] colorBricks = new ArrayList[K+1];
        for (int c=0; c<=K; c++){
            colorBricks[c] = new ArrayList<>();
        }
        int[] sumColor = new int[K+1];

        for (int i=0; i<N; i++){
            st = new StringTokenizer(rd.readLine());
            int L = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            sumColor[C]+= L;
            colorBricks[C].add(new Brick(L, i+1));
        }

        int T = sumColor[1];
        for (int c=2; c<=K; c++){
            if (sumColor[c] != T){
                System.out.println("NO");
                return;
            }
        }
        if (T < 2) {
            System.out.println("NO");
            return;
        }

        reachable = new boolean[K+1][];
        prev = new int[K+1][];
        for (int c=1; c<=K; c++){
            reachable[c] = new boolean[T+1];
            prev[c] = new int[T+1];
            Arrays.fill(prev[c], -1);
            buildReachable(colorBricks[c], c, T);
        }

        for (int X=1; X <= T/2; X++){
            boolean ok = true;
            for (int c=1; c<=K; c++){
                if (!reachable[c][X]) {
                    ok=false;
                    break;
                }
            }
            if (ok) {
                System.out.println("YES");
                List<Integer> answer = new ArrayList<>();
                for (int c=1; c<=K; c++){
                    answer.addAll(recoverPath(colorBricks[c], c, X));
                }
                for (int idx : answer){
                    System.out.print(idx + " ");
                }
                System.out.println();
                return;
            }
            int Y = T - X;
            ok=true;
            for (int c=1; c<=K; c++){
                if (!reachable[c][Y]) {ok=false; break;}
            }
            if (ok){
                System.out.println("YES");
                List<Integer> answer = new ArrayList<>();
                for (int c=1; c<=K; c++){
                    answer.addAll(recoverPath(colorBricks[c], c, Y));
                }
                for (int idx: answer){
                    System.out.print(idx + " ");
                }
                System.out.println();
                return;
            }
        }

        System.out.println("NO");
    }

    static void buildReachable(List<Brick> bricks, int c, int T){
        reachable[c][0] = true;
        for (int i=0; i<bricks.size(); i++){
            int len= bricks.get(i).length;
            for (int s=T; s>=len; s--){
                if (reachable[c][s-len] && !reachable[c][s]){
                    reachable[c][s]=true;
                    prev[c][s]= i;
                }
            }
        }
    }

    static List<Integer> recoverPath(List<Brick> bricks, int c, int sum){
        List<Integer> ans = new ArrayList<>();
        while (sum>0){
            int i= prev[c][sum];
            ans.add(bricks.get(i).index);
            sum -= bricks.get(i).length;
        }
        return ans;
    }
}