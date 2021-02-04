package sovled.class4.p2206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 벽 뚫고 가기
 * 2020-12-29
 */
public class Main {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int n;
    static int m;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        n = Integer.parseInt(split[0]);
        m = Integer.parseInt(split[1]);

        char[][] map = new char[n][m];

        for (int i = 0; i < n; i++) {
            String readLine = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = readLine.charAt(j);
            }
        }

        int[][] visited = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }
        bfs(map, visited);

        if (result == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(result);
    }

    private static void bfs(char[][] map, int[][] visited) {

        Queue<Point> queue = new LinkedList<>();
        visited[0][0] = 0;

        queue.add(new Point(0, 0, 0, 1));
        while (!queue.isEmpty()) {
            Point current = queue.poll();

            if (current.y == n - 1 && current.x == m - 1) {
                result = current.value;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int newY = current.y + dy[i];
                int newX = current.x + dx[i];

                if (newY < 0 || newX < 0 || newY > n - 1 || newX > m - 1 || visited[newY][newX] <= current.breakCount)
                    continue;
                if (map[newY][newX] == '0') {
                    visited[newY][newX] = current.breakCount;
                    queue.add(new Point(newY, newX, current.breakCount, current.value + 1));
                } else if (current.breakCount == 0) {
                    visited[newY][newX] = current.breakCount + 1;
                    queue.add(new Point(newY, newX, current.breakCount + 1, current.value + 1));
                }
            }
        }
    }

    static class Point {
        int y;
        int x;
        int breakCount;
        int value;

        public Point(int y, int x, int breakCount, int value) {
            this.y = y;
            this.x = x;
            this.breakCount = breakCount;
            this.value = value;
        }
    }
}
