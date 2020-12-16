package class3.p1389;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");

        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);

        int[][] adjMatrix = new int[n][n];

        for (int i = 0; i < m; i++) {
            split = br.readLine().split(" ");

            int first = Integer.parseInt(split[0]) - 1;
            int second = Integer.parseInt(split[1]) - 1;
            adjMatrix[first][second] = 1;
            adjMatrix[second][first] = 1;
        }

        int[][] dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (adjMatrix[i][j] != 0) {
                    dist[i][j] = 1;
                }
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

        int[] result = new int[n];
        int min = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < n; i++) {
            int subSum = 0;
            for (int j = 0; j < n; j++) {
                subSum += dist[i][j];
            }
            result[i] = subSum;
            if (min > subSum){
                min = subSum;
                index = i;
            }
        }

        System.out.println(index + 1);
    }
}
