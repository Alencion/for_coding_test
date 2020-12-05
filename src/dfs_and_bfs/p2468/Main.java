package dfs_and_bfs.p2468;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Main {
    // 우 하 좌 상
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] map = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] split = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(split[j]);
            }
        }

        int answer = solution(n, map);
        System.out.println(answer);
    }

    private static int solution(int n, int[][] map) {
        int max = 1;
        int[][] visited = new int[n][n];

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                set.add(map[i][j]);
            }
        }

        for (int value : set) {
            int areaCount = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] > value && visited[i][j] != value) {
                        bfs(n, i, j, visited, map, value);
                        areaCount++;
                    }
                }
            }
            max = Math.max(max, areaCount);
        }

        return max;
    }

    private static void bfs(int n, int i, int j, int[][] visited, int[][] map, int value) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        visited[i][j] = value;

        while(!queue.isEmpty()){
            int[] position = queue.poll();

            for (int k = 0; k < 4; k++) {
                int newY = position[0] + dy[k];
                int newX = position[1] + dx[k];
                if (valid(newY, n) && valid(newX, n) && map[newY][newX] > value && visited[newY][newX] != value){
                    visited[newY][newX] = value;
                    queue.add(new int[]{newY, newX});
                }
            }
        }
    }

    private static boolean valid(int newY, int n) {
        return newY >= 0 && newY < n;
    }


}
