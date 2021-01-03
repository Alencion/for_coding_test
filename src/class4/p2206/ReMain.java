package class4.p2206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 벽 부수고 이동하기
 * 2021-01-03
 */
public class ReMain {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int n;
    static int m;

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
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(0, 0, 0, 1));
        visited[0][0] = 0;

        while (!queue.isEmpty()) {
            Position current = queue.poll();

            if (current.y == n - 1 && current.x == m -1){
                System.out.println(current.value);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int newY = current.y + dy[i];
                int newX = current.x + dx[i];

                if (newY < 0 || newX < 0 || newY > n - 1 || newX > m - 1 ||
                        visited[newY][newX] <= current.breakCount) continue;
                if (map[newY][newX] == '0') {
                    visited[newY][newX] = current.breakCount;
                    queue.add(new Position(newY, newX, current.breakCount, current.value + 1));
                } else if (current.breakCount == 0) {
                    visited[newY][newX] = current.breakCount + 1;
                    queue.add(new Position(newY, newX, current.breakCount + 1, current.value + 1));
                }
            }
        }

        System.out.println(-1);
    }

    static class Position {
        int y;
        int x;
        int breakCount;
        int value;


        public Position(int y, int x, int breakCount, int value) {
            this.y = y;
            this.x = x;
            this.breakCount = breakCount;
            this.value = value;
        }
    }
}
