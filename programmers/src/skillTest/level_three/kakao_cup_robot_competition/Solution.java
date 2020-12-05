package skillTest.level_three.kakao_cup_robot_competition;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    private final int[] dx = {1, 0, -1, 0};
    private final int[] dy = {0, -1, 0, 1};
    private final int[] rotateX = {-1, -1, 1, 1};
    private final int[] rotateY = {1, -1, -1, 1};

    public int solution(int[][] board) {
        int answer = 0;
        int n = board.length;
        boolean visited[][][] = new boolean[n][n][4];

        visited[0][0][0] = true;

        answer = bfs(n, visited, board);

        return answer;
    }

    private int bfs(int n, boolean[][][] visited, int[][] board) {
        Queue<Robot> queue = new LinkedList<>();
        queue.add(new Robot(0, 0, 0, 0));

        while (!queue.isEmpty()) {
            Robot robot = queue.poll();
            if (isArrive(n, robot)) return robot.time;

            for (int i = 0; i < 4; i++) {
                int newX = robot.x + dx[i];
                int newY = robot.y + dy[i];
                int newOtherX = robot.otherX() + dx[i];
                int newOtherY = robot.otherY() + dy[i];

                if (!isValid(newX, newY, n) || !isValid(newOtherX, newOtherY, n)) continue;
                if (board[newY][newX] == 1 || board[newOtherY][newOtherX] == 1) continue;
                if (visited[newY][newX][robot.direction]) continue;

                visited[newY][newX][robot.direction] = true;

                queue.add(new Robot(newX, newY, robot.direction, robot.time + 1));
            }

            for (int i = 1; i < 4; i += 2) {
                int newDirection = (robot.direction + i) % 4 ;
                int newOtherX = robot.x + dx[newDirection];
                int newOtherY = robot.y + dy[newDirection];
                int tempDirection = (i == 1) ? robot.direction : newDirection;
                int rotateOtherX = robot.x + rotateX[tempDirection];
                int rotateOtherY = robot.y + rotateY[tempDirection];

                if (!isValid(newOtherX, newOtherY, n) || !isValid(rotateOtherX, rotateOtherY, n)) continue;
                if (board[newOtherY][newOtherX] == 1 || board[rotateOtherY][rotateOtherX] == 1) continue;
                if (visited[robot.y][robot.x][newDirection]) continue;

                visited[robot.y][robot.x][newDirection] = true;

                queue.add(new Robot(robot.x, robot.y, newDirection, robot.time + 1));
            }

            for (int i = 1; i < 4; i += 2) {
                int newDirection = (robot.direction + 2 + i) % 4 ;
                int newX = robot.otherX() + dx[newDirection];
                int newY = robot.otherY() + dy[newDirection];
                int tempDirection = (i == 1) ? robot.direction : newDirection;
                int rotateOtherX = robot.otherX() + rotateX[tempDirection];
                int rotateOtherY = robot.otherY() + rotateY[tempDirection];
//                newDirection = (newDirection + 2) % 4;

                if (!isValid(newX, newY, n) || !isValid(rotateOtherX, rotateOtherY, n)) continue;
                if (board[newY][newX] == 1 || board[rotateOtherY][rotateOtherX] == 1) continue;
                if (visited[robot.otherX()][robot.otherY()][newDirection]) continue;

                visited[robot.otherX()][robot.otherY()][newDirection] = true;

                queue.add(new Robot(robot.otherX(), robot.otherY(), newDirection, robot.time + 1));
            }

        }

        return -1;
    }

    private boolean isValid(int x, int y, int n) {
        return x >= 0 && y >=0 && x < n && y < n;
    }

    private boolean isArrive(int n, Solution.Robot robot) {
        return robot.x == n - 1 && robot.y == n - 1
                || robot.otherX() == n - 1 && robot.otherY() == n - 1;
    }

    @Test
    public void test1() {
        int[][] board = {
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 0},
                {0, 1, 0, 1, 1},
                {1, 1, 0, 0, 1},
                {0, 0, 0, 0, 0}};

        Assert.assertEquals(7, new Solution().solution(board));
    }

    class Robot {
        int x;
        int y;
        int direction;
        int time;

        public Robot(int x, int y, int direction, int time) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.time = time;
        }

        public int otherX() {
            return x + dx[direction];
        }

        public int otherY() {
            return y + dy[direction];
        }
    }
}