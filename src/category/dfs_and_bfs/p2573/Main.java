package category.dfs_and_bfs.p2573;

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

        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            split = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(split[j]);
            }
        }

        int answer = solution(n, m, map);
        System.out.println(answer);
    }

    private static int solution(int n, int m, int[][] map) {
        int time = 1;

        while (true) {
            map = updateMap(map);
            boolean[][] visited = new boolean[n][m];

            int[] position = new int[2];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 0) visited[i][j] = true;
                    else {
                        position[0] = i;
                        position[1] = j;
                    }
                }
            }

            if (position[0] == 0 && position[1] == 0) break;

            bfs(position[0], position[1], n, m, map, visited);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (!visited[i][j]) {
                        return time;
                    }
                }
            }
            time++;
        }

        return 0;
    }

    private static void bfs(int y, int x, int n, int m, int[][] map, boolean[][] visited) {
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{y, x});
        visited[y][x] = true;
        while(!queue.isEmpty()){
            int[] position = queue.poll();

            for (int i = 0; i < 4; i++) {
                int newY = position[0] + dy[i];
                int newX = position[1] + dx[i];

                if (map[newY][newX] != 0 && !visited[newY][newX]){
                    queue.add(new int[]{newY, newX});
                    visited[newY][newX] = true;
                }
            }
        }

    }

    private static int[][] updateMap(int[][] map) {
        int[][] updateMap = new int[map.length][map[0].length];

        for (int i = 1; i < map.length - 1; i++) {
            for (int j = 1; j < map[0].length - 1; j++) {
                if (map[i][j] != 0) {
                    int count = 0;

                    for (int k = 0; k < 4; k++) {
                        int newY = i + dy[k];
                        int newX = j + dx[k];

                        if (map[newY][newX] == 0) count++;
                    }
                    if (map[i][j] <= count) updateMap[i][j] = 0;
                    else updateMap[i][j] = map[i][j] - count;
                }
            }
        }
        return updateMap;
    }
}
