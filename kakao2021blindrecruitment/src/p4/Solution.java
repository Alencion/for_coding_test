package p4;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class Solution {

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;

        List<List<Edge>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] fare : fares) {
            adjList.get(fare[0] - 1).add(new Edge(fare[1] - 1, fare[2]));
            adjList.get(fare[1] - 1).add(new Edge(fare[0] - 1, fare[2]));
        }

        int[][] results = floydWarshall(n, adjList);

        int[] sDist = results[s - 1];
        int total = sDist[a - 1] + sDist[b - 1];

        for (int i = 0; i < n; i++) {
            if (i == s - 1) continue;
            int[] dist = results[i];
            if (sDist[i] == Integer.MAX_VALUE || dist[a - 1] == Integer.MAX_VALUE || dist[b - 1] == Integer.MAX_VALUE)
                continue;
            total = Math.min(total, sDist[i] + dist[a - 1] + dist[b - 1]);
        }

        answer = total;
        return answer;
    }

    int[][] floydWarshall(int n, List<List<Edge>> adjList) {
        int[][] dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) dist[i][j] = 0;
                else {
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int i = 0; i < adjList.size(); i++) {
            for (int j = 0; j < adjList.get(i).size(); j++) {
                Edge edge = adjList.get(i).get(j);
                dist[i][edge.to] = edge.weight;
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE && dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        return dist;
    }

    private static class Edge implements Comparable<Edge> {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return weight - o.weight;
        }
    }

    @Test
    public void test() {
        int n = 6;
        int s = 4;
        int a = 6;
        int b = 2;
        int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};

        Assert.assertEquals(82, new Solution().solution(n, s, a, b, fares));
    }

    @Test
    public void test1() {
        int n = 6;
        int s = 4;
        int a = 5;
        int b = 6;
        int[][] fares = {{2, 6, 6}, {6, 3, 7}, {4, 6, 7}, {6, 5, 11}, {2, 5, 12}, {5, 3, 20}, {2, 4, 8}, {4, 3, 9}};

        Assert.assertEquals(18, new Solution().solution(n, s, a, b, fares));
    }
}
