package dfs_and_bfs.p14503;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    // 상 우 하 좌
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);

        split = br.readLine().split(" ");
        int robotY = Integer.parseInt(split[0]);
        int robotX = Integer.parseInt(split[1]);
        int robotDir = Integer.parseInt(split[2]);

        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            split = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(split[j]);
            }
        }

        int answer = solution(n, m, robotX, robotY, robotDir, map);
        System.out.println(answer);
    }

    private static int solution(int n, int m, int robotX, int robotY, int robotDir, int[][] map) {
        Robot robot = new Robot(robotY, robotX, robotDir);
        boolean[][] visited = new boolean[n][m];

        Queue<Robot> queue = new LinkedList<>();
        queue.add(robot);
        visited[robot.y][robot.x] = true;

        int time = 1;

        while (!queue.isEmpty()) {
            robot = queue.poll();

            if (isFinish(robot, map, visited)) {
                int newY = robot.y + dy[(robot.dir + 2) % 4];
                int newX = robot.x + dx[(robot.dir + 2) % 4];

                if (valid(newY, map.length) && valid(newX, map[0].length) && map[newY][newX] == 1) break;
                queue.add(new Robot(newY, newX, robot.dir));
                continue;
            }

            time++;
            for (int i = 0; i < 4; i++) {
                robot.nextDir();
                int newY = robot.y + dy[robot.dir];
                int newX = robot.x + dx[robot.dir];

                if (valid(newY, n) && valid(newX, m) && map[newY][newX] == 0 && !visited[newY][newX]) {
                    visited[newY][newX] = true;
                    queue.add(new Robot(newY, newX, robot.dir));
                    break;
                }

            }
        }

        return time;
    }

    private static boolean isFinish(Robot robot, int[][] map, boolean[][] visited) {
        for (int i = 0; i < 4; i++) {
            int newY = robot.y + dy[(robot.dir + i) % 4];
            int newX = robot.x + dx[(robot.dir + i) % 4];

            if (valid(newY, map.length) && valid(newX, map[0].length) && map[newY][newX] == 0 && !visited[newY][newX]) {
                return false;
            }
        }

        return true;
    }

    private static boolean valid(int newY, int n) {
        return newY >= 0 && newY < n;
    }

    private static class Robot {
        int y;
        int x;
        int dir;

        public Robot(int y, int x, int dir) {
            this.y = y;
            this.x = x;
            this.dir = dir;
        }

        public void nextDir() {
            this.dir = (this.dir + 3) % 4;
        }
    }
}
