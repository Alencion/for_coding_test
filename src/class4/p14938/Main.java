package class4.p14938;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 서강 그라운드
 * 2021-01-01
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");

        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);
        int r = Integer.parseInt(split[2]);

        int[] items = new int[n];
        List<List<Edge>> adjList = new ArrayList<>();

        split = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            items[i] = Integer.parseInt(split[i]);
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < r; i++) {
            split = br.readLine().split(" ");

            int from = Integer.parseInt(split[0]) - 1;
            int to = Integer.parseInt(split[1]) - 1;
            int distance = Integer.parseInt(split[2]);

            adjList.get(from).add(new Edge(to, distance));
            adjList.get(to).add(new Edge(from, distance));
        }

        int[][] dist = floydWarshall(n, adjList);

        int answer = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if (m >= dist[i][j]) sum += items[j];
            }
            answer = Math.max(answer, sum);
        }

        System.out.println(answer);
    }

    private static int[][] floydWarshall(int n, List<List<Edge>> adjList) {
        int[][] dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0;
            for (Edge edge : adjList.get(i)) {
                dist[i][edge.to] = edge.distance;
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] == Integer.MAX_VALUE || dist[k][j] == Integer.MAX_VALUE) continue;
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        return dist;
    }

    static class Edge {
        int to;
        int distance;

        public Edge(int to, int distance) {
            this.to = to;
            this.distance = distance;
        }
    }
}
