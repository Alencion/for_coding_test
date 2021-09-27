package sovled.class4.p1916;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 최소비용 구하기
 * 2020-12-27
 * 2020-09-27
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        List<List<Edge>> adjList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            String[] split = br.readLine().split(" ");

            adjList.get(Integer.parseInt(split[0]) - 1)
                    .add(new Edge(Integer.parseInt(split[1]) - 1, Integer.parseInt(split[2])));
        }

        String[] split = br.readLine().split(" ");
        int from = Integer.parseInt(split[0]) - 1;
        int to = Integer.parseInt(split[1]) - 1;

        System.out.println(dijkstra(n, from, to, adjList));

    }

    private static int dijkstra(int n, int from, int to, List<List<Edge>> adjList) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[from] = 0;

        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.add(new Edge(from, 0));

        boolean[] visited = new boolean[n];
        while(!queue.isEmpty()){
            Edge current = queue.poll();

            visited[current.to] = true;
            if(dist[current.to] < current.cost) continue;
            for (Edge edge : adjList.get(current.to)) {
                if (!visited[edge.to] && dist[edge.to] > current.cost + edge.cost){
                    dist[edge.to] = current.cost + edge.cost;
                    queue.add(new Edge(edge.to, dist[edge.to]));
                }
            }
        }

        return dist[to];
    }

    static class Edge implements Comparable<Edge>{
        int to;
        int cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}
