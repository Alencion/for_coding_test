package sovled.class5.p1007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReMain {
    static double answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());

            Point[] points = new Point[n];

            for (int j = 0; j < n; j++) {
                String[] split = br.readLine().split(" ");
                points[j] = new Point(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
            }

            answer = Integer.MAX_VALUE;
            boolean[] visited = new boolean[n];
            dfs(n, 0, 0, points, visited);
            sb.append(answer).append(System.lineSeparator());
        }
        System.out.println(sb.toString());
    }

    private static void dfs(int n, int index, int depth, Point[] points, boolean[] visited) {
        if (depth == n / 2) {
            double x = 0;
            double y = 0;
            for (int i = 0; i < n; i++) {
                if (visited[i]) {
                    x += points[i].x;
                    y += points[i].y;
                } else {
                    x -= points[i].x;
                    y -= points[i].y;
                }
            }
            double dis = Math.sqrt(x * x + y * y);
            answer = Math.min(answer, dis);
            return;
        }

        for (int i = index + 1; i < n; i++) {
            visited[i] = true;
            dfs(n, i, depth+1, points, visited);
            visited[i] = false;
        }
    }

    static class Point {
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}