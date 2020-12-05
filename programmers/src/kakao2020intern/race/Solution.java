package kakao2020intern.race;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    //순서: 우, 하, 좌, 상
    int[] dc = {1, 0, -1, 0};
    int[] dr = {0, 1, 0, -1};

    public int solution(int[][] board) {
        int n = board.length;
        int[][] visit = new int[n][n];
        int answer = bfs(board, visit);
        return answer;
    }

    private int bfs(int[][] board, int[][] visit) {
        int answer = Integer.MAX_VALUE;
        int n = board.length;
        Queue<History> queue = new LinkedList<>();

        if (board[0][1] != 1)
            queue.add(new History(0, 1, 100, 0));
        if (board[1][0] != 1)
            queue.add(new History(1, 0, 100, 1));

        visit[1][0] = 100;
        visit[0][1] = 100;

        while (!queue.isEmpty()) {
            History element = queue.poll();

            if (visit[element.y][element.x] != 0 && (element.pay > visit[element.y][element.x]))
                continue;

            if (element.y == (n - 1) && element.x == (n - 1)) {
                answer = Math.min(answer, element.pay);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int newY = element.y + dr[i];
                int newX = element.x + dc[i];

                int cost = i == element.dir ? 100 : 600; // 되돌아 갈 일은 없으니까 .. 이렇게 만 비교를 해도 방향을 잡을 수 있다 ...
                if (0 > newY || newY >= n || 0 > newX || newX >= n || board[newY][newX] == 1) continue;

                if (visit[newY][newX] == 0 || visit[newY][newX] >= element.pay + cost) {
                    visit[newY][newX] = element.pay + cost;
                    queue.add(new History(newY, newX, element.pay + cost, i));
                }
            }
        }

        return answer;
    }

    final static class History {
        int y;
        int x;
        int pay;
        int dir;

        History(int y, int x, int pay, int dir) {
            this.y = y;
            this.x = x;
            this.pay = pay;
            this.dir = dir;
        }
    }

    @Test
    public void test() {
        int[][] board = {{0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}};
        Assert.assertEquals(900, new Solution().solution(board));
    }

    @Test
    public void test1() {
        int[][] board = {{0, 0, 1, 0},
                {0, 0, 0, 0},
                {0, 1, 0, 1},
                {1, 0, 0, 0}};
        Assert.assertEquals(2100, new Solution().solution(board));
    }

    @Test
    public void test2() {
        int[][] board = {{0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 0, 1, 0},
                {0, 1, 0, 0, 0, 1, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0}};
        Assert.assertEquals(900, new Solution().solution(board));
    }
}
