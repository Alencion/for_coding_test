package class3.p1012;

import java.io.*;

public class Main {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        String[] split;

        for (int i = 0; i < t; i++) {
            split = br.readLine().split(" ");

            int m = Integer.parseInt(split[0]);
            int n = Integer.parseInt(split[1]);
            int k = Integer.parseInt(split[2]);

            boolean[][] map = new boolean[m][n];
            for (int j = 0; j < k; j++) {
                split = br.readLine().split(" ");
                map[Integer.parseInt(split[0])][Integer.parseInt(split[1])] = true;
            }

            bw.write(solution(map) +"\n");
        }
        bw.flush();
    }

    private static int solution(boolean[][] map) {
        boolean[][] visited = new boolean[map.length][map[0].length];

        int count = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] && !visited[i][j]) {
                    dfs(i, j, map, visited);
                    count++;
                }
            }
        }
        return count;
    }

    private static void dfs(int i, int j, boolean[][] map, boolean[][] visited) {
        visited[i][j] = true;

        for (int k = 0; k < 4; k++) {
            int newY = i + dy[k];
            int newX = j + dx[k];

            if (valid(newY, map.length) && valid(newX, map[0].length) && map[newY][newX] && !visited[newY][newX]){
                dfs(newY, newX, map, visited);
            }
        }
    }

    private static boolean valid(int newX, int length) {
        return 0 <= newX && newX < length;
    }
}
