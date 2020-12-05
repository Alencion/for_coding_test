package back_tracking.p1987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    // 우 하 좌 상
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int r = Integer.parseInt(split[0]);
        int c = Integer.parseInt(split[1]);

        char[][] board = new char[r][c];
        for (int i = 0; i < r; i++) {
            String boardData = br.readLine();
            for (int j = 0; j < c; j++) {
                board[i][j] = boardData.charAt(j);
            }
        }

        int answer = solution(r, c, board);
        System.out.println(answer);
    }

    private static int solution(int r, int c, char[][] board) {
        Set<Character> visited = new HashSet<>();

        char value = board[0][0];

        visited.add(value);

        dfs(0, 0, r, c, board, visited, 1);

        return max;
    }

    private static void dfs(int y, int x, int r, int c, char[][] board, Set<Character> visited, int count) {
        boolean flag = false;
        for (int i = 0; i < 4; i++) {
            int newY = y + dy[i];
            int newX = x + dx[i];

            if (valid(newY, r) && valid(newX, c)) {
                char value = board[newY][newX];

                if (!visited.contains(board[newY][newX])) {
                    flag = true;
                    visited.add(value);
                    dfs(newY, newX, r, c, board, visited, count + 1);
                    visited.remove(value);
                }
            }
        }

        if (!flag) {
            max = Math.max(count, max);
        }
    }

    private static boolean valid(int newY, int r) {
        return newY >= 0 && newY < r;
    }
}
