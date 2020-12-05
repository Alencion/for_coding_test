package dijkstra.p5719;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int vertex = Integer.parseInt(split[0]);
        int edge = Integer.parseInt(split[1]);

        while (vertex != 0) {
            split = br.readLine().split(" ");

            int src = Integer.parseInt(split[0]);
            int dest = Integer.parseInt(split[1]);

            int[][] edges = new int[edge][3];

            for (int i = 0; i < edge; i++) {
                split = br.readLine().split(" ");
                edges[i][0] = Integer.parseInt(split[0]);
                edges[i][1] = Integer.parseInt(split[1]);
                edges[i][2] = Integer.parseInt(split[2]);
            }

            int answer = solution(src, dest, vertex, edges);
            System.out.println(answer);

            split = br.readLine().split(" ");

            vertex = Integer.parseInt(split[0]);
            edge = Integer.parseInt(split[1]);
        }
    }

    private static int solution(int src, int dest, int vertex, int[][] edges) {
        int[][] adjMatrix = new int[vertex][vertex];
        List[] paths = new ArrayList[vertex];
        for (int i = 0; i < vertex; i++) {
            Arrays.fill(adjMatrix[i], Integer.MAX_VALUE);
            paths[i] = new ArrayList<Integer>();
        }

        for (int[] edge : edges) {
            adjMatrix[edge[0]][edge[1]] = edge[2];
        }

        int[] dist = dijkstra(src, vertex, adjMatrix, paths);

        int minDistance = dist[dest];

        List<int[]> shortestPath = findPath(minDistance, src, dest, paths, adjMatrix);

        removePath(shortestPath, adjMatrix);

        dist = dijkstra(src, vertex, adjMatrix, paths);

        return dist[dest] == Integer.MAX_VALUE ? -1 : dist[dest];
    }

    private static void removePath(List<int[]> shortestPath, int[][] adjMatrix) {
        shortestPath.forEach(edge -> adjMatrix[edge[0]][edge[1]] = Integer.MAX_VALUE);
    }

    private static List<int[]> findPath(int minDistance, int src, int dest, List[] paths, int[][] adjMatrix) {
        List<int[]> results = new ArrayList<>();

        List<int[]> result = new ArrayList<>();
        dfs(dest, src, minDistance, paths, 0, result, results, adjMatrix);

        return results;
    }

    private static void dfs(int dest, int src, int minDistance, List[] paths, int total, List<int[]> result, List<int[]> results, int[][] adjMatrix) {
        if (total > minDistance) return;
        if (dest == src && total == minDistance) {
            results.addAll(result);
            return;
        }

        for (int i = 0; i < paths[dest].size(); i++) {
            int vertex = (int) paths[dest].get(i);
            result.add(new int[]{vertex, dest});
            dfs(vertex, src, minDistance, paths, total + adjMatrix[vertex][dest], result, results, adjMatrix);
            result.remove(result.size()-1);
        }
    }

    private static int[] dijkstra(int src, int vertex, int[][] adjMatrix, List[] lists) {
        int[] dist = new int[vertex];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(i -> i[1]));

        dist[src] = 0;
        pq.add(new int[]{src, 0});

        while (!pq.isEmpty()) {
            int[] point = pq.poll();

            if (dist[point[0]] < point[1]) continue;

            for (int i = 0; i < vertex; i++) {
                if (adjMatrix[point[0]][i] != Integer.MAX_VALUE && dist[i] >= point[1] + adjMatrix[point[0]][i]) {
                    dist[i] = point[1] + adjMatrix[point[0]][i];
                    pq.add(new int[]{i, dist[i]});
                    lists[i].add(point[0]);
                }
            }
        }

        return dist;
    }
}
