package sovled.class4.p11779;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 최소비용 구하기 2
 * 2020-12-31
 */
public class Main {
    static int[] parent;

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

            int from = Integer.parseInt(split[0]) - 1;
            int to = Integer.parseInt(split[1]) - 1;

            adjList.get(from).add(new Edge(to, Integer.parseInt(split[2])));
        }

        String[] split = br.readLine().split(" ");
        int from = Integer.parseInt(split[0]) - 1;
        int to = Integer.parseInt(split[1]) - 1;

        parent = new int[n];
        int answer = dijkstra(n, from, to, adjList);
        System.out.println(answer);
        Stack<Integer> stack = new Stack<>();
        while (to != from) {
            stack.add(to);
            to = parent[to];
        }
        stack.add(from);
        System.out.println(stack.size());
        while (!stack.isEmpty()) {
            System.out.print((stack.pop() + 1) + " ");
        }
    }

    private static int dijkstra(int n, int from, int to, List<List<Edge>> adjList) {
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[from] = 0;
        PriorityQueue<Edge> queue = new PriorityQueue<>();

        queue.add(new Edge(from, 0));
        while (!queue.isEmpty()) {
            Edge current = queue.poll();

            if (visited[current.to]) continue;
            visited[current.to] = true;

            for (Edge edge : adjList.get(current.to)) {
                if (visited[edge.to]) continue;
                if (dist[edge.to] > current.weight + edge.weight) {
                    dist[edge.to] = current.weight + edge.weight;
                    queue.add(new Edge(edge.to, dist[edge.to]));
                    parent[edge.to] = current.to;
                }
            }
        }
        return dist[to];
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
            return Integer.compare(this.weight, o.weight);
        }
    }
}
