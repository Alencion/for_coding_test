package dijkstra.p4485;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    // 우 하 좌 상
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int index = 1;
        while (n != 0) {
            int[][] graph = new int[n][n];

            for (int i = 0; i < n; i++) {
                String[] split = br.readLine().split(" ");
                for (int j = 0; j < n; j++) {
                    graph[i][j] = Integer.parseInt(split[j]);
                }
            }

            int answer = solution(n, graph);
            System.out.println("Problem " + index + ": " + answer);
            n = Integer.parseInt(br.readLine());
            index++;
        }
    }

    private static int solution(int n, int[][] graph) {
        int[][] dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        dist[0][0] = graph[0][0];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(i -> i[2]));
        pq.add(new int[]{0, 0, dist[0][0]});

        while (!pq.isEmpty()) {
            int[] point = pq.poll();

            for (int i = 0; i < 4; i++) {
                int newY = point[0] + dy[i];
                int newX = point[1] + dx[i];

                if (valid(newY, n) && valid(newX, n)) {
                    if (dist[newY][newX] > point[2] + graph[newY][newX]) {
                        dist[newY][newX] = point[2] + graph[newY][newX];
                        pq.add(new int[]{newY, newX, dist[newY][newX]});
                    }
                }
            }
        }

        return dist[n - 1][n - 1];
    }

    private static boolean valid(int newY, int n) {
        return newY >= 0 && newY < n;
    }
}
