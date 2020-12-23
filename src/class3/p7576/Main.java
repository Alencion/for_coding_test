package class3.p7576;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 토마토
 * 2020-12-23
 */
public class Main {
    //                 상 우 하 좌
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[1]);
        int m = Integer.parseInt(split[0]);

        int[][] box = new int[n][m];
        for (int i = 0; i < n; i++) {
            split = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                box[i][j] = Integer.parseInt(split[j]);
            }
        }

        int answer = solution(n, m, box);
        System.out.println(answer);
    }

    private static int solution(int n, int m, int[][] box) {
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (box[i][j] == 0) continue;
                if (box[i][j] == 1) queue.add(new int[]{i,j});
                visited[i][j] = true;
            }
        }

        int day = 0;

        while(!queue.isEmpty()){
            bfs(n,m, visited, queue);
            day++;
        }

        if (check(n,m,visited)) return day - 1;
        else return -1;
    }

    private static void bfs(int n, int m, boolean[][] visited, Queue<int[]> queue) {
        Queue<int[]> subQueue = new LinkedList<>();

        while(!queue.isEmpty()){
            int[] current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int newY = current[0] + dy[i];
                int newX = current[1] + dx[i];

                if (valid(newY, n) && valid(newX, m) && !visited[newY][newX]){
                    subQueue.add(new int[]{newY, newX});
                    visited[newY][newX] = true;
                }
            }
        }

        queue.addAll(subQueue);
    }

    private static boolean valid(int newY, int n) {
        return 0 <= newY && newY < n;
    }

    private static boolean check(int n, int m, boolean[][] visited) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j]) return false;
            }
        }
        return true;
    }
}
