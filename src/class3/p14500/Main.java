package class3.p14500;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 테트로미노
 * 2020-12-24
 */
public class Main {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static boolean[][] check;
    static int result, n,m;
    static int[][] map;
    private static int[][] ex = {{0, 0, 0, 1}, {0, 1, 2, 1}, {0, 0, 0, -1}, {0, -1, 0, 1}};
    private static int[][] ey = {{0, 1, 2, 1}, {0, 0, 0, 1}, {0, 1, 2, 1}, {0, 1, 1, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");

        n = Integer.parseInt(split[0]);
        m = Integer.parseInt(split[1]);

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            split = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(split[j]);
            }
        }

        check = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                check[i][j] = true;

                dfs(i, j, map[i][j],1);
                check[i][j] = false;
                checkExtraShape(i, j);
            }
        }

        System.out.println(result);
    }

    private static void checkExtraShape(int x, int y) {
        for(int i=0; i<4; i++) {

            Boolean isOut = false;
            int sum_value = 0;

            for (int j = 0; j < 4; j++) {
                int nx = x + ex[i][j];
                int ny = y + ey[i][j];

                if (nx < 0 || nx > n - 1 || ny < 0 || ny > m - 1) {
                    isOut = true;
                    break;
                } else {
                    sum_value += map[nx][ny];
                }
            }
            if (!isOut) {
                result = Math.max(result, sum_value);
            }
        }
    }

    private static void dfs(int x, int y, int sum_value, int length) {
        if(length >= 4){
            result = Math.max(result, sum_value);
            return;
        }

        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || nx > n - 1 || ny < 0 || ny > m - 1) continue;

            if(!check[nx][ny]) {
                check[nx][ny] = true;
                dfs(nx, ny, sum_value + map[nx][ny], length + 1);
                check[nx][ny] = false;
            }
        }
    }
}

