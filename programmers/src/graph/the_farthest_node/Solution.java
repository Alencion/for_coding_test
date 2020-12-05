package graph.the_farthest_node;

import java.util.*;

class Solution {
    public int solution(int n, int[][] edges) {
        int answer = 0;

        List<List<Integer>> adjList = new ArrayList<>();
        setAdjList(adjList, n, edges);

        int[] edgeWeights = new int[n];
        Arrays.fill(edgeWeights, Integer.MAX_VALUE);

        edgeWeights[0] = 0;

        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);

        while (!queue.isEmpty()) {
            int index = queue.poll();
            if (visited[index]) continue;
            List<Integer> edgeList = adjList.get(index);
            visited[index] = true;

            for (int vertex : edgeList) {
                queue.add(vertex);
                if (edgeWeights[vertex] != Integer.MAX_VALUE) {
                    edgeWeights[vertex] = Math.min(edgeWeights[index] + 1, edgeWeights[vertex]);
                } else {
                    edgeWeights[vertex] = edgeWeights[index] + 1;
                }
            }
        }

        int max = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int weight : edgeWeights) {
            max = Math.max(max, weight);
            map.put(weight, map.getOrDefault(weight, 0) + 1);
        }

        answer = map.get(max);
        return answer;
    }

    private void setAdjList(List<List<Integer>> adjList, int n, int[][] edges) {
        for (int i = 0; i < n; i++)
            adjList.add(new ArrayList<>());

        for (int[] edge : edges) {
            adjList.get(edge[0] - 1).add(edge[1] - 1);
            adjList.get(edge[1] - 1).add(edge[0] - 1);
        }
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};

        System.out.println(new Solution().solution(n, edge));
    }
}