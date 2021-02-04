package category.dfs_and_bfs.p1697;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int k = Integer.parseInt(split[1]);

        int answer = solution(n, k);

        System.out.println(answer);
    }

    private static int solution(int n, int k) {

        boolean[] visited = new boolean[100001];

        Queue<Integer> queue = new LinkedList<>();

        visited[n] = true;
        queue.add(n);
        int time = 0;
        while (!queue.isEmpty()) {
            Queue<Integer> next = new LinkedList<>();
            while (!queue.isEmpty()) {
                int position = queue.poll();

                if (position == k) return time;

                if (position - 1 >= 0 && !visited[position - 1]) {
                    visited[position - 1] = true;
                    next.add(position - 1);
                }

                if (position + 1 < 100001 && !visited[position + 1]) {
                    visited[position + 1] = true;
                    next.add(position + 1);
                }

                if (position * 2 < 100001 && !visited[position * 2]) {
                    visited[position * 2] = true;
                    next.add(position * 2);
                }
            }
            queue = next;
            time += 1;
        }

        return time;
    }
}
