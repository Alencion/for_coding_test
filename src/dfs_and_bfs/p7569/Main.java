package dfs_and_bfs.p7569;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    // 우 하 좌 상 위 아래
    static int[] dx = {1, 0, -1, 0, 0, 0};
    static int[] dy = {0, 1, 0, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int m = Integer.parseInt(split[0]);
        int n = Integer.parseInt(split[1]);
        int h = Integer.parseInt(split[2]);

        int[][][] box = new int[h][n][m];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                split = br.readLine().split(" ");
                for (int k = 0; k < m; k++) {
                    box[i][j][k] = Integer.parseInt(split[k]);
                }
            }
        }

        int spendTime = solution(h, n, m, box);
        System.out.println(spendTime);
    }

    private static int solution(int h, int n, int m, int[][][] box) {
        int spendTime = bfs(h, n, m, box);
        return spendTime;
    }

    private static int bfs(int h, int n, int m, int[][][] box) {
        boolean[][][] visited = new boolean[h][n][m];
        Queue<Point> queue = new LinkedList<>();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (box[i][j][k] != 0) {
                        visited[i][j][k] = true;
                        if (box[i][j][k] == 1) {
                            queue.add(new Point(i, j, k, 0));
                        }
                    }
                }
            }
        }
        Point point = null;
        while (!queue.isEmpty()) {
            point = queue.poll();

            for (int i = 0; i < 6; i++) {
                int newZ = point.z + dz[i];
                int newY = point.y + dy[i];
                int newX = point.x + dx[i];

                if (valid(newZ, h) && valid(newY, n) && valid(newX, m) && box[newZ][newY][newX] == 0 && !visited[newZ][newY][newX]) {
                    visited[newZ][newY][newX] = true;
                    queue.add(new Point(newZ, newY, newX, point.time + 1));
                }
            }
        }

        if (point == null) return 0;

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (!visited[i][j][k]) {
                        return -1;
                    }
                }
            }
        }

        return point.time;
    }

    private static boolean valid(int point, int h) {
        return point >= 0 && point < h;
    }

    private static class Point {
        int z;
        int y;
        int x;
        int time;

        public Point(int z, int y, int x, int time) {
            this.z = z;
            this.y = y;
            this.x = x;
            this.time = time;
        }
    }
}
