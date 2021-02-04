package category.back_tracking.p2580;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    static int[][] answer = new int[9][9];
    static boolean isFinish = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] board = new int[9][9];

        String[] split;
        for (int i = 0; i < 9; i++) {
            split = br.readLine().split(" ");

            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(split[j]);
            }
        }

        solution(board);
        for (int i = 0; i < 9; i++) {
            System.out.print(answer[i][0]);
            for (int j = 1; j < 9; j++) {
                System.out.print(" " + answer[i][j]);
            }
            System.out.println();
        }
    }

    private static int[][] solution(int[][] board) {
        List<int[]> points = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) points.add(new int[]{i, j});
            }
        }

        int y = points.get(0)[0];
        int x = points.get(0)[1];

        for (int i = 1; i < 10; i++) {
            if (canFill(i, y, x, board)) {
                board[y][x] = i;
                dfs(1, points, board);
            }
        }

        return board;
    }

    private static void dfs(int index, List<int[]> points, int[][] board) {
        if (isFinish) return;
        if (index == points.size()) {
            copyAnswer(board);
            isFinish = true;
            return;
        }

        int y = points.get(index)[0];
        int x = points.get(index)[1];

        for (int i = 1; i < 10; i++) {
            if (canFill(i, y, x, board)) {
                board[y][x] = i;
                dfs(index + 1, points, board);
                board[y][x] = 0;
            }
        }
    }

    private static void copyAnswer(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                answer[i][j] = board[i][j];
            }
        }
    }

    private static boolean canFill(int value, int y, int x, int[][] board) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            set.add(board[y][i]);
        }

        if (set.contains(value)) return false;

        set.clear();
        for (int i = 0; i < 9; i++) {
            set.add(board[i][x]);
        }

        if (set.contains(value)) return false;

        y -= y % 3;
        x -= x % 3;

        set.clear();
        for (int i = y; i < y + 3; i++) {
            for (int j = x; j < x + 3; j++) {
                set.add(board[i][j]);
            }
        }

        return !set.contains(value);
    }
}
