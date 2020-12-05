package graph.floyd_warshall;

import graph.source.Graph;
import org.junit.jupiter.api.Test;

import java.util.List;

public class Code {
    public int[][] floydWarshallWithAdjMatrix(){
        int[][] adjMatrix = Graph.adjMatrix();
        int size = adjMatrix.length;

        int[][] dist = new int[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(adjMatrix[i], 0, dist[i], 0, size);
        }

        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (dist[i][k] == Graph.INF || dist[k][j] == Graph.INF) continue;
                    if (dist[i][j] > dist[i][k] + dist[k][j]){
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        return dist;
    }

    @Test
    public void floydWarshallWithAdjMatrixTest(){
        int[][] minimumDistances = floydWarshallWithAdjMatrix();

        for (int[] minimumDistance : minimumDistances) {
            for (int distance : minimumDistance) {
                System.out.print(distance + " ");
            }
            System.out.println();
        }
    }

    public int[][] floydWarshallWithAdjList(){
        List<List<Graph.Edge>> adjList = Graph.adjList();
        int size = adjList.size();

        int[][] dist = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) dist[i][i] = 0;
                else dist[i][j] = Graph.INF;
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < adjList.get(i).size(); j++) {
                Graph.Edge edge = adjList.get(i).get(j);
                dist[i][edge.to] = edge.weight;
            }
        }
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (dist[i][k] == Graph.INF || dist[k][j] == Graph.INF) continue;
                    if (dist[i][j] > dist[i][k] + dist[k][j]){
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        return dist;
    }

    @Test
    public void floydWarshallWithAdjListTest(){
        int[][] minimumDistances = floydWarshallWithAdjList();

        for (int[] minimumDistance : minimumDistances) {
            for (int distance : minimumDistance) {
                System.out.print(distance + " ");
            }
            System.out.println();
        }
    }
}
