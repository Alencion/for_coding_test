package sovled.class4.p1504;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 특정한 최단 경로
 * 2020-12-25
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);

        int[][] adjMatrix = new int[n][n];

        for (int i = 0; i < m; i++) {
            split = br.readLine().split(" ");

            adjMatrix[Integer.parseInt(split[0]) - 1][Integer.parseInt(split[1]) - 1] = Integer.parseInt(split[2]);
            adjMatrix[Integer.parseInt(split[1]) - 1][Integer.parseInt(split[0]) - 1] = Integer.parseInt(split[2]);
        }

        split = br.readLine().split(" ");
        int v1 = Integer.parseInt(split[0]) - 1;
        int v2 = Integer.parseInt(split[1]) - 1;

        int answer1;
        int answer2;

        boolean a1 = true, a2 = true;

        int[] dist = dijkstra(0, n, adjMatrix);
        int[] distV1 = dijkstra(v1, n, adjMatrix);
        int[] distV2 = dijkstra(v2, n, adjMatrix);

        if (dist[v1] == Integer.MAX_VALUE || distV1[v2] == Integer.MAX_VALUE || distV2[n - 1] == Integer.MAX_VALUE) {
            a1 = false;
        }
        if (dist[v2] == Integer.MAX_VALUE || distV2[v1] == Integer.MAX_VALUE || distV1[n - 1] == Integer.MAX_VALUE) {
            a2 = false;
        }
        answer1 = dist[v1] + distV1[v2] + distV2[n - 1];
        answer2 = dist[v2] + distV2[v1] + distV1[n - 1];

        if (a1 && a2) {
            System.out.println(Math.min(answer1, answer2));
        } else if (a1) {
            System.out.println(answer1);
        } else if (a2) {
            System.out.println(answer2);
        } else {
            System.out.println(-1);
        }
    }

    private static int[] dijkstra(int start, int n, int[][] adjMatrix) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        boolean[] visited = new boolean[n];

        queue.add(new int[]{start, 0});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            visited[poll[0]] = true;

            for (int i = 0; i < adjMatrix[poll[0]].length; i++) {
                int distance = adjMatrix[poll[0]][i];
                if (distance == 0) continue;
                if (!visited[i] && dist[i] > poll[1] + distance) {
                    dist[i] = dist[poll[0]] + distance;
                    queue.add(new int[]{i, dist[i]});
                }
            }
        }

        return dist;
    }
}
