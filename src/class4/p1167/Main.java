package class4.p1167;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 트리의 지름
 * 2020-12-25 다익스트라
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int v = Integer.parseInt(br.readLine());

        Map<Integer, List<Edge>> adjList = new HashMap<>();
        for (int i = 0; i < v; i++) {
            String[] split = br.readLine().split(" ");

            adjList.put(Integer.parseInt(split[0]) - 1, new ArrayList<>());
            List<Edge> list = adjList.get(Integer.parseInt(split[0]) - 1);
            for (int j = 1; j < split.length - 1; j += 2) {
                list.add(new Edge(Integer.parseInt(split[j]) - 1, Integer.parseInt(split[j + 1])));
            }
        }

        int[] dist = dijkstra(0, v, adjList);
        int max = Integer.MIN_VALUE;
        int index = 0;
        for (int i = 0; i < dist.length; i++) {
            if (dist[i] != Integer.MAX_VALUE && max < dist[i]) {
                max = dist[i];
                index = i;
            }
        }

        dist = dijkstra(index, v, adjList);
        max = Integer.MIN_VALUE;
        for (int value : dist) {
            if (value != Integer.MAX_VALUE && max < value) {
                max = value;
            }
        }
        System.out.println(max);
    }

    private static int[] dijkstra(int i, int v, Map<Integer, List<Edge>> adjList) {
        boolean[] visited = new boolean[v];
        int[] dist = new int[v];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[i] = 0;

        Queue<Edge> queue = new PriorityQueue<>();

        queue.add(new Edge(i, 0));
        while (!queue.isEmpty()) {
            Edge current = queue.poll();

            visited[current.to] = true;

            List<Edge> edges = adjList.get(current.to);
            for (Edge edge : edges) {

                if (!visited[edge.to] && dist[edge.to] > dist[current.to] + edge.distance) {
                    dist[edge.to] = dist[current.to] + edge.distance;
                    queue.add(new Edge(edge.to, dist[edge.to]));
                }
            }
        }

        return dist;
    }

    static class Edge implements Comparable<Edge> {
        int to;
        int distance;

        public Edge(int to, int distance) {
            this.to = to;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(distance, o.distance);
        }
    }
}
