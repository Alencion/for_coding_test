package sovled.class3.p10026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 적록색약 n == 100
 * 2020-12-23 dfs
 */
public class Main {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        char[][] map = new char[n][n];

        for (int i = 0; i < n; i++) {
            String readLine = br.readLine();
            for (int j = 0; j < readLine.length(); j++) {
                map[i][j] = readLine.charAt(j);
            }
        }

        int normal = solution(map, false);
        int colorWeek = solution(map, true);
        System.out.println(normal + " " + colorWeek);
    }

    private static int solution(char[][] map, boolean colorWeek) {
        boolean[][] visited = new boolean[map.length][map[0].length];

        if (colorWeek) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    if (map[i][j] == 'G') {
                        map[i][j] = 'R';
                    }
                }
            }
        }

        int count = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (!visited[i][j]) {
                    dfs(i, j, map, visited, map[i][j]);
                    count++;
                }
            }
        }
        return count;
    }

    private static void dfs(int i, int j, char[][] map, boolean[][] visited, char value) {
        visited[i][j] = true;

        for (int k = 0; k < 4; k++) {
            int newY = i + dy[k];
            int newX = j + dx[k];

            if (0 <= newY && newY < map.length && 0 <= newX && newX < map[0].length && map[newY][newX] == value && !visited[newY][newX]) {
                dfs(newY, newX, map, visited, map[i][j]);
            }
        }
    }
}
