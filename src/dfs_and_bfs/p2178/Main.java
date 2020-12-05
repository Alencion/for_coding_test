package dfs_and_bfs.p2178;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    // 우 하 좌 상
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");

        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);

        int[][] maze = new int[n][m];
        String mazeData;
        for (int i = 0; i < n; i++) {
            mazeData = br.readLine();
            for (int j = 0; j < m; j++) {
                maze[i][j] = mazeData.charAt(j) - '0';
            }
        }

        int answer = solution(n, m, maze);
        System.out.println(answer);
    }

    private static int solution(int n, int m, int[][] maze) {
        boolean[][] visited = new boolean[n][m];
        Queue<Position> queue = new LinkedList<>();
        visited[0][0] = true;

        queue.add(new Position(0, 0, 1));

        Position position = null;
        while (!queue.isEmpty()) {
            position = queue.poll();
            if (isFinish(position, n, m)) {
                break;
            }

            for (int i = 0; i < 4; i++) {
                int newY = position.y + dy[i];
                int newX = position.x + dx[i];
                if (valid(newY, n) && valid(newX, m) && maze[newY][newX] == 1 && !visited[newY][newX]) {
                    visited[newY][newX] = true;
                    queue.add(new Position(newY, newX, position.time + 1));
                }
            }
        }

        return position.time;
    }

    private static boolean isFinish(Position position, int n, int m) {
        return position.y == n - 1 && position.x == m - 1;
    }

    private static boolean valid(int x, int n) {
        return x >= 0 && x < n;
    }

    private static class Position {
        int y;
        int x;
        int time;

        public Position(int y, int x, int time) {
            this.y = y;
            this.x = x;
            this.time = time;
        }
    }
}
