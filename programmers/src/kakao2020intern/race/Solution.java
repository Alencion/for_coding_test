package kakao2020intern.race;

public class Solution {
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};
    int[][] board;
    boolean[][] visit;
    int min = Integer.MAX_VALUE;

    public int solution(int[][] board) {
        int answer = 0;
        this.board = board;
        this.visit = new boolean[board.length][board.length];

        bfs(0, 0, -1, -1, 0, 0);
        answer = min;
        return answer;
    }

    private void bfs(int x, int y, int prevX, int prevY, int straightCount, int cornerCount) {
        int n = board.length;
        if (x == n - 1 && y == n - 1) {
            min = Math.min(min, straightCount * 100 + cornerCount * 500);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int ux = x + dx[i];
            int uy = y + dy[i];
            if (ux >= 0 && ux < n && uy >= 0 && uy < n) {
                if (!visit[ux][uy] && board[ux][uy] == 0) {
                    visit[ux][uy] = true;
                    if (!(prevX == -1 && prevY == -1) && prevX != ux && prevY != uy) {
                        bfs(ux, uy, x, y, straightCount + 1, cornerCount + 1);
                    } else {
                        bfs(ux, uy, x, y, straightCount + 1, cornerCount);
                    }
                    visit[ux][uy] = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] board = {{0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}};
        new Solution().solution(board);
    }
}
