package class4.p14502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 연구소
 * 2021-01-01
 */
public class Main {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int answer = Integer.MIN_VALUE;
    static int n, m;
    static Queue<int[]> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");

        n = Integer.parseInt(split[0]);
        m = Integer.parseInt(split[1]);

        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            split = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(split[j]);
                if (map[i][j] == 2) queue.add(new int[]{i, j});
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    dfs(1, map);
                    map[i][j] = 0;
                }
            }
        }

        System.out.println(answer);
    }

    private static void dfs(int depth, int[][] map) {
        if (depth == 3) {
            int[][] temp = new int[n][m];

            for (int i = 0; i < map.length; i++) {
                temp[i] = map[i].clone();
            }

            answer = Math.max(answer, bfs(temp));
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    dfs(depth + 1, map);
                    map[i][j] = 0;
                }
            }
        }
    }

    private static int bfs(int[][] map) {

        Queue<int[]> queue = new LinkedList<>();
        queue.addAll(Main.queue);

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int newY = current[0] + dy[i];
                int newX = current[1] + dx[i];

                if (newY < 0 || newX < 0 || newY > n - 1 || newX > m - 1) continue;
                if (map[newY][newX] != 0) continue;
                map[newY][newX] = 2;
                queue.add(new int[]{newY, newX});
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) count++;
            }
        }

        return count;
    }
}
