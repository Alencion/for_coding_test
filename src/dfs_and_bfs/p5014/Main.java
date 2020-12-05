package dfs_and_bfs.p5014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");

        int f = Integer.parseInt(split[0]);
        int s = Integer.parseInt(split[1]);
        int g = Integer.parseInt(split[2]);
        int u = Integer.parseInt(split[3]);
        int d = Integer.parseInt(split[4]);

        int answer = solution(f, s, g, u, d);
        if (answer == -1) System.out.println("use the stairs");
        else System.out.println(answer);
    }

    private static int solution(int f, int s, int g, int u, int d) {
        boolean[] visited = new boolean[f];
        s -= 1;

        visited[s] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        int time = 0;

        while (!queue.isEmpty()) {
            Queue<Integer> next = new LinkedList<>();

            while (!queue.isEmpty()) {
                int floor = queue.poll();
                if (floor == g - 1) return time;

                if (floor + u < f && !visited[floor + u]) {
                    visited[floor + u] = true;
                    next.add(floor + u);
                }
                if (floor - d >= 0 && !visited[floor - d]) {
                    visited[floor - d] = true;
                    next.add(floor - d);
                }
            }
            queue = next;
            time++;
        }


        return -1;
    }
}
