package category.dfs_and_bfs.p2644;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int peopleCount = Integer.parseInt(br.readLine());

        String[] split = br.readLine().split(" ");
        int[] find = new int[]{Integer.parseInt(split[0]), Integer.parseInt(split[1])};

        int edgeCount = Integer.parseInt(br.readLine());
        int[][] edges = new int[edgeCount][2];

        for (int i = 0; i < edgeCount; i++) {
            split = br.readLine().split(" ");
            edges[i][0] = Integer.parseInt(split[0]);
            edges[i][1] = Integer.parseInt(split[1]);
        }

        int kinship = solution(peopleCount, find, edges);
        System.out.println(kinship);
    }

    private static int solution(int peopleCount, int[] find, int[][] edges) {
        Graph graph = new Graph(peopleCount, edges);

        int kinship = graph.findShortestDistance(find[0], find[1]);
        return kinship;
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

        private int findShortestDistance(int src, int dest) {
            boolean[] visited = new boolean[vertexCount];
            Queue<Vertex> queue = new LinkedList<>();

            queue.add(new Vertex(src - 1, 0));
            visited[src - 1] = true;

            while (!queue.isEmpty()) {
                Vertex vertex = queue.poll();
                if (vertex.vertex == dest - 1) return vertex.kinship;

                for (int i = 0; i < vertexCount; i++) {
                    if (adjMatrix[vertex.vertex][i] == 1 && !visited[i]) {
                        visited[i] = true;
                        queue.add(new Vertex(i, vertex.kinship + 1));
                    }
                }
            }

            return -1;
        }
    }

    private static class Vertex {
        int vertex;
        int kinship;

        public Vertex(int vertex, int kinship) {
            this.vertex = vertex;
            this.kinship = kinship;
        }
    }
}
