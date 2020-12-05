package dijkstra.p1238;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);
        int x = Integer.parseInt(split[2]);

        int[][] adjMatrix = new int[n][n];

        for (int i = 0; i < m; i++) {
            split = br.readLine().split(" ");
            int src = Integer.parseInt(split[0]);
            int dest = Integer.parseInt(split[1]);
            int time = Integer.parseInt(split[2]);
            adjMatrix[src - 1][dest - 1] = time;
        }

        int answer = solution(n, adjMatrix, x);
        System.out.println(answer);
    }

    private static int solution(int n, int[][] adjMatrix, int x) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (adjMatrix[i][j] == 0) adjMatrix[i][j] = Integer.MAX_VALUE;
            }
        }

        int[][] minDistances = new int[n][n];
        for (int i = 0; i < n; i++) {
            minDistances[i] = dijkstra(i, adjMatrix);
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, minDistances[i][x - 1] + minDistances[x - 1][i]);
        }

        return max;
    }

    private static int[][] floydWarshall(int[][] adjMatrix) {
        int n = adjMatrix.length;
        int[][] result = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) result[i][i] = 0;
                else if (adjMatrix[i][j] == 0) result[i][j] = Integer.MAX_VALUE;
                else result[i][j] = adjMatrix[i][j];
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (result[i][k] == Integer.MAX_VALUE || result[k][j] == Integer.MAX_VALUE) continue;
                    if (result[i][j] > result[i][k] + result[k][j]) {
                        result[i][j] = result[i][k] + result[k][j];
                    }
                }
            }
        }
        return result;
    }

    private static int[] dijkstra(int src, int[][] adjMatrix) {
        int n = adjMatrix.length;
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(i -> i[1]));
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] visited = new boolean[n];

        queue.add(new int[]{src, 0});

        dist[src] = 0;

        while (!queue.isEmpty()) {
            int vertex = queue.poll()[0];

            if (visited[vertex]) continue;
            visited[vertex] = true;

            for (int i = 0; i < n; i++) {
                if (adjMatrix[vertex][i] != Integer.MAX_VALUE && !visited[i] && dist[i] > dist[vertex] + adjMatrix[vertex][i]) {
                    dist[i] = dist[vertex] + adjMatrix[vertex][i];
                    queue.add(new int[]{i, dist[i]});
                }
            }
        }

        return dist;
    }
}
