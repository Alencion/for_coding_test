package kakao2021BlindRecruitment.combined_taxi_fare;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;

        List<List<Node>> adjList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] fare : fares) {
            adjList.get(fare[0] - 1).add(new Node(fare[1] - 1, fare[2]));
            adjList.get(fare[1] - 1).add(new Node(fare[0] - 1, fare[2]));
        }

        int[][] dist = floydWarshal(n, adjList);

        answer = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (dist[s - 1][i] == Integer.MAX_VALUE
                    || dist[i][a - 1] == Integer.MAX_VALUE
                    || dist[i][b - 1] == Integer.MAX_VALUE) continue;
            int subTotal = dist[s - 1][i];
            subTotal += dist[i][a - 1];
            subTotal += dist[i][b - 1];

            answer = Math.min(answer, subTotal);
        }
        return answer;
    }

    private int[][] floydWarshal(int n, List<List<Node>> adjList) {
        int[][] dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0;
        }

        for (int i = 0; i < adjList.size(); i++) {
            for (Node node : adjList.get(i)) {
                dist[i][node.from] = node.value;
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

    static class Node {
        int from;
        int value;

        public Node(int from, int value) {
            this.from = from;
            this.value = value;
        }
    }

    @Test
    public void test1() {
        int n = 6;
        int s = 4;
        int a = 6;
        int b = 2;

        int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};

        Assert.assertEquals(82, solution(n, s, a, b, fares));
    }
}
