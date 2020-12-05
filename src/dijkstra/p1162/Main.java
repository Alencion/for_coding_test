package dijkstra.p1162;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");

        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);
        int k = Integer.parseInt(split[2]);

        int[][] edges = new int[m][3];
        for (int i = 0; i < m; i++) {
            split = br.readLine().split(" ");
            edges[i][0] = Integer.parseInt(split[0]) - 1;
            edges[i][1] = Integer.parseInt(split[1]) - 1;
            edges[i][2] = Integer.parseInt(split[2]);
        }

        long answer = solution(n, k, edges);
        System.out.println(answer);
    }

    private static long solution(int n, int k, int[][] edges) {
        List[] adjList = new List[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<Edge>();
        }

        for (int[] edge : edges) {
            adjList[edge[0]].add(new Edge(edge[1], edge[2]));
            adjList[edge[1]].add(new Edge(edge[0], edge[2]));
        }

        long[][] dist = dijkstra(n, k, adjList);

        return dist[n-1][k];
    }

    private static long[][] dijkstra(int n, int k, List[] adjList) {
        long[][] dist = new long[n][k + 1];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }
        dist[0][0] = 0;

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(i -> i[2]));
        pq.add(new long[]{0, 0, 0});

        while (!pq.isEmpty()) {
            long[] poll = pq.poll();
            int vertex = (int) poll[0];
            int step = (int) poll[1];
            long weight = poll[2];

            if (dist[vertex][step] < poll[2]) continue;

            for (int i = 0; i < adjList[vertex].size(); i++) {
                Edge edge = (Edge) adjList[vertex].get(i);
                // 포장
                if (step + 1 <= k && dist[edge.vertex][step + 1] > dist[vertex][step]) {
                    dist[edge.vertex][step + 1] = dist[vertex][step];
                    pq.add(new long[]{edge.vertex, step + 1, dist[edge.vertex][step + 1]});
                }
                // 안포장
                if (dist[edge.vertex][step] > weight + edge.weight) {
                    dist[edge.vertex][step] = weight + edge.weight;
                    pq.add(new long[]{edge.vertex, step, dist[edge.vertex][step]});
                }

            }
        }

        return dist;
    }

    private static class Edge implements Comparable<Edge>{
        int vertex;
        long weight;

        public Edge(int vertex, long weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(weight, o.weight);
        }
    }
}
