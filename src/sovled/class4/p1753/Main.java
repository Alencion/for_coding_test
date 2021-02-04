package sovled.class4.p1753;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 최단경로
 * 2020-12-26
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int v = Integer.parseInt(split[0]);
        int e = Integer.parseInt(split[1]);

        int start = Integer.parseInt(br.readLine()) - 1;

        List<List<Edge>> adjList = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            split = br.readLine().split(" ");

            adjList.get(Integer.parseInt(split[0]) - 1)
                    .add(new Edge(Integer.parseInt(split[1]) - 1, Integer.parseInt(split[2])));
        }

        int[] dist = dijkstra(start, v, adjList);
        for (int i = 0; i < v; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(dist[i]);
            }
        }
    }

    private static int[] dijkstra(int start, int v, List<List<Edge>> adjList) {
        int[] dist = new int[v];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        boolean[] visited = new boolean[v];
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.add(new Edge(start, 0));
        while(!queue.isEmpty()){
            Edge current = queue.poll();

            visited[current.to] = true;

            List<Edge> edges = adjList.get(current.to);
            for (Edge edge : edges) {
                if (!visited[edge.to] && dist[edge.to] > current.weight + edge.weight){
                    dist[edge.to] = current.weight + edge.weight;
                    queue.add(new Edge(edge.to, dist[edge.to]));
                }
            }
        }
        return dist;
    }

    static class Edge implements Comparable<Edge>{
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}
