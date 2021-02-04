package category.dijkstra.p10473;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        double[] a = new double[]{Double.parseDouble(split[0]), Double.parseDouble(split[1])};

        split = br.readLine().split(" ");
        double[] b = new double[]{Double.parseDouble(split[0]), Double.parseDouble(split[1])};

        int n = Integer.parseInt(br.readLine());
        double[][] cannons = new double[n + 2][2];

        cannons[0] = a;
        cannons[n + 1] = b;

        for (int i = 1; i < n + 1; i++) {
            split = br.readLine().split(" ");
            cannons[i] = new double[]{Double.parseDouble(split[0]), Double.parseDouble(split[1])};
        }

        double answer = solution(a, b, n, cannons);

        System.out.println(answer);
    }

    private static double solution(double[] a, double[] b, int n, double[][] cannons) {
        double answer = calcDistance(a, b) / 5;

        double[][] adjMatrix = new double[n + 2][n + 2];

        for (int i = 0; i < n + 2; i++) {
            for (int j = 0; j < n + 2; j++) {
                if (i == j) adjMatrix[i][j] = 0;
                else
                    adjMatrix[i][j] = calcDistance(cannons[i], cannons[j]);
            }
        }

        double[] dist = dijkstra(n + 2, adjMatrix);

        return Math.min(answer, dist[n+1]);
    }

    private static double[] dijkstra(int n, double[][] adjMatrix) {

        PriorityQueue<Node> pq = new PriorityQueue<>();

        double[] dist = new double[n];
        Arrays.fill(dist, Double.MAX_VALUE);
        dist[0] = 0;
        pq.add(new Node(0,0));

        while(!pq.isEmpty()){
            Node vertex = pq.poll();

            for (int i = 0; i < n; i++) {
                if (vertex.from == 0){
                    if (dist[i] >  vertex.weight + (adjMatrix[vertex.from][i] / 5)){
                        dist[i] = vertex.weight + (adjMatrix[vertex.from][i] / 5);
                        pq.add(new Node(i, dist[i]));
                    }
                } else {
                    if (dist[i] >  2 + vertex.weight + (Math.abs(adjMatrix[vertex.from][i] - 50)) / 5){
                        dist[i] = 2 + vertex.weight + (Math.abs(adjMatrix[vertex.from][i] - 50)) / 5;
                        pq.add(new Node(i, dist[i]));
                    }
                }
            }
        }

        return dist;
    }

    private static double calcDistance(double[] a, double[] b) {
        return Math.sqrt(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2));
    }

    private static class Node implements Comparable<Node>{
        int from;
        double weight;

        public Node(int from, double weight) {
            this.from = from;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Double.compare(weight, o.weight);
        }
    }
}
