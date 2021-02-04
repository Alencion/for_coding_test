package sovled.class4.p1967;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        List<List<Edge>> adjList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            String[] split = br.readLine().split(" ");

            int parent = Integer.parseInt(split[0]) - 1;
            int child = Integer.parseInt(split[1]) - 1;
            int weight = Integer.parseInt(split[2]);

            adjList.get(parent).add(new Edge(child, weight));
            adjList.get(child).add(new Edge(parent, weight));
        }

        int[] distV1 = dijkstra(0, n, adjList);

        int maxIndex = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < distV1.length; i++) {
            if (max < distV1[i]) {
                maxIndex = i;
                max = distV1[i];
            }
        }

        int[] distV2 = dijkstra(maxIndex, n, adjList);
        max = Integer.MIN_VALUE;
        for (int i = 0; i < distV2.length; i++) {
            max = Math.max(max, distV2[i]);
        }
        System.out.println(max);
    }

    private static int[] dijkstra(int start, int n, List<List<Edge>> adjList) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Edge> queue = new PriorityQueue<>();
        boolean[] visited = new boolean[n];

        queue.add(new Edge(start, 0));
        while (!queue.isEmpty()) {
            Edge poll = queue.poll();

            visited[poll.to] = true;

            for (Edge edge : adjList.get(poll.to)) {
                if (!visited[edge.to] && dist[edge.to] > poll.weight + edge.weight) {
                    dist[edge.to] = poll.weight + edge.weight;
                    queue.add(new Edge(edge.to, dist[edge.to]));
                }
            }
        }

        return dist;
    }

    static class Edge implements Comparable<Edge> {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(weight, o.weight);
        }
    }
}
