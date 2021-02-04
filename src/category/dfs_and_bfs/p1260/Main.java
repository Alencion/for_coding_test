package category.dfs_and_bfs.p1260;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");

        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);
        int v = Integer.parseInt(split[2]);

        int[][] edge = new int[m][2];
        String[] edgeData;
        for (int i = 0; i < m; i++) {
            edgeData = br.readLine().split(" ");
            edge[i][0] = Integer.parseInt(edgeData[0]);
            edge[i][1] = Integer.parseInt(edgeData[1]);
        }

        String[] answer = solution(n, edge, v);

        System.out.println(answer[0]);
        System.out.println(answer[1]);
    }

    private static String[] solution(int n, int[][] edge, int v) {
        Graph graph = new Graph(n, edge);

        int[] dfs = graph.dfs(v);
        int[] bfs = graph.bfs(v);

        String dfsResult = Arrays.stream(dfs)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));

        String bfsResult = Arrays.stream(bfs)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));

        return new String[]{dfsResult, bfsResult};
    }

    private static class Graph {
        int vertexCount;
        int[][] adjMatrix;

        public Graph(int n, int[][] edge) {
            this.vertexCount = n;
            this.adjMatrix = makeAdjMatrix(edge);
        }

        private int[][] makeAdjMatrix(int[][] edge) {
            int[][] adjMatrix = new int[vertexCount][vertexCount];

            for (int i = 0; i < edge.length; i++) {
                adjMatrix[edge[i][0] - 1][edge[i][1] - 1] = 1;
                adjMatrix[edge[i][1] - 1][edge[i][0] - 1] = 1;
            }

            return adjMatrix;
        }

        public int[] dfs(int start) {
            List<Integer> result = new ArrayList<>();
            boolean[] visited = new boolean[vertexCount];
            start = start - 1;

            dfs(start, visited, result);
            return result.stream().mapToInt(i -> i).toArray();
        }

        private void dfs(int vertex, boolean[] visited, List<Integer> result) {
            result.add(vertex + 1);
            visited[vertex] = true;

            for (int i = 0; i < vertexCount; i++) {
                if (this.adjMatrix[vertex][i] == 1 && !visited[i]) {
                    dfs(i, visited, result);
                }
            }
        }

        public int[] bfs(int start) {
            List<Integer> result = new ArrayList<>();
            boolean[] visited = new boolean[vertexCount];
            start = start - 1;

            Queue<Integer> queue = new LinkedList<>();
            queue.add(start);
            visited[start] = true;

            while (!queue.isEmpty()) {
                int vertex = queue.poll();
                result.add(vertex + 1);

                for (int i = 0; i < vertexCount; i++) {
                    if (this.adjMatrix[vertex][i] == 1 && !visited[i]) {
                        visited[i] = true;
                        queue.add(i);
                    }
                }
            }

            return result.stream().mapToInt(i -> i).toArray();
        }
    }


}
