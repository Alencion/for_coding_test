package category.dfs_and_bfs.p2667;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 우 하 좌 상
    static int[] dx = {1, 0 , -1, 0};
    static int[] dy = {0, 1 , 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            String mapData = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = mapData.charAt(j) - '0';
            }
        }

        int[] answer = solution(n, map);

        for (int value : answer) {
            System.out.println(value);
        }
    }

    private static int[] solution(int n, int[][] map) {
        List<Integer> result = new ArrayList<>();
        int complexCount = 0;

        boolean[][] visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    int area = bfs(i, j, n, map, visited);
                    result.add(area);
                    complexCount += 1;
                }
            }
        }

        int[] answer = new int[result.size() + 1];

        answer[0] = complexCount;
        Collections.sort(result);

        for (int i = 1; i < answer.length; i++) {
            answer[i] = result.get(i - 1);
        }
        return answer;
    }

    private static int bfs(int y, int x, int n, int[][] map, boolean[][] visited) {

        visited[y][x] = true;
        Queue<Point> queue = new LinkedList<>();

        queue.add(new Point(y, x));
        int area = 1;

        while(!queue.isEmpty()){
            Point point = queue.poll();

            for (int i = 0; i < 4; i++) {
                int newY = point.y + dy[i];
                int newX = point.x + dx[i];

                if (valid(newY, n) && valid(newX, n) && !visited[newY][newX] && map[newY][newX] == 1){
                    visited[newY][newX] = true;
                    queue.add(new Point(newY, newX));
                    area += 1;
                }
            }
        }

        return area;
    }

    private static boolean valid(int newY, int n) {
        return newY >= 0 && newY < n;
    }

    private static class Point{
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
