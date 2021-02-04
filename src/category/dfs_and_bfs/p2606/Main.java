package category.dfs_and_bfs.p2606;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int computerCount = Integer.parseInt(br.readLine());
        int edgeCount = Integer.parseInt(br.readLine());

        int[][] edge = new int[edgeCount][2];

        for (int i = 0; i < edgeCount; i++) {
            String[] split = br.readLine().split(" ");
            edge[i][0] = Integer.parseInt(split[0]);
            edge[i][1] = Integer.parseInt(split[1]);
        }

        int answer = solution(computerCount, edge);
        System.out.println(answer);
    }

    private static int solution(int computerCount, int[][] edges) {
        Graph graph = new Graph(computerCount, edges);

        int count = graph.bfs(1);

        return count - 1;
    }

    private static class Graph {
        int vertexCount;
        int[][] adjMatrix;

        public Graph(int vertexCount, int[][] edges) {
            this.vertexCount = vertexCount;
            this.adjMatrix = makeAdjMatrix(edges);
        }

        private int[][] makeAdjMatrix(int[][] edges) {
            int[][] adjMatrix = new int[vertexCount][vertexCount];

            for (int[] edge : edges) {
                adjMatrix[edge[0] - 1][edge[1] - 1] = 1;
                adjMatrix[edge[1] - 1][edge[0] - 1] = 1;
            }

            return adjMatrix;
        }

        public int bfs(int vertex) {
            Queue<Integer> queue = new LinkedList<>();
            vertex = vertex - 1;
            int count = 1;

            boolean[] visited = new boolean[vertexCount];
            visited[vertex] =true;

            queue.add(vertex);
            while(!queue.isEmpty()){
                vertex = queue.poll();

                for (int i = 0; i < vertexCount; i++) {
                    if (this.adjMatrix[vertex][i] == 1 && !visited[i]){
                        visited[i] = true;
                        queue.add(i);
                        count++;
                    }
                }
            }


            return count;
        }
    }
}
