package category.back_tracking.p1405;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    // 우 좌 상 하
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        double east = Integer.parseInt(split[1]) * 0.01;
        double west = Integer.parseInt(split[2]) * 0.01;
        double north = Integer.parseInt(split[3]) * 0.01;
        double south = Integer.parseInt(split[4]) * 0.01;

        double answer = solution(n, new double[]{east, west, north, south});
        System.out.println(answer);
    }

    private static double solution(int n, double[] probability) {
        boolean[][] visited = new boolean[30][30];
        visited[14][14] = true;
        return dfs(0, n, 14, 14, probability, visited, 1.0);
    }

    private static double dfs(int depth, int n, int y, int x, double[] probability, boolean[][] visited, double percent) {
        if (depth == n) {
            return percent;
        }

        double result = 0;
        for (int i = 0; i < 4; i++) {
            int newY = y + dy[i];
            int newX = x + dx[i];

            if (!visited[newY][newX]) {
                visited[newY][newX] = true;
                result += dfs(depth + 1, n, newY, newX, probability, visited, percent * probability[i]);
                visited[newY][newX] = false;
            }
        }

        return result;
    }
}
