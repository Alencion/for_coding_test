package sovled.class4.p2638;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 치즈
 * 2020-12-29
 */
public class Main {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int m, n, count;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        n = Integer.parseInt(split[0]);
        m = Integer.parseInt(split[1]);

        map = new int[n][m];
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            split = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(split[j]);

                if (map[i][j] == 1) count++;
            }
        }


        checkExternalAir(0, 0, visited);

        int answer = 0;
        while (count != 0) {
            visited = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 1 && !visited[i][j])
                        dfs(i, j, visited);
                }
            }

            visited = new boolean[n][m];
            checkExternalAir(0, 0, visited);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    map[i][j] = map[i][j] == 3 ? 2 : map[i][j];
                }
            }

            answer++;
        }
        System.out.println(answer);
    }


    private static void checkExternalAir(int y, int x, boolean[][] visited) {
        visited[y][x] = true;
        map[y][x] = 2;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
            if (visited[nx][ny] || map[nx][ny] == 1) continue;

            map[nx][ny] = 2;
            checkExternalAir(ny, nx, visited);
        }

    }

    static void dfs(int x, int y, boolean[][] visited) {
        visited[x][y] = true;

        if (map[x][y] == 1 && isMelt(x, y)) {
            --count;
            map[x][y] = 3; // 녹은 치즈는 3으로 바꿔준다 (0으로 바꾸면 언제 녹았는지 구분 불가)
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
            if (visited[nx][ny] || map[nx][ny] == 0) continue;

            dfs(nx, ny, visited);
        }
    }

    static boolean isMelt(int x, int y) {
        int air = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
            if (map[nx][ny] == 2) ++air;
        }

        return air >= 2;
    }
}
