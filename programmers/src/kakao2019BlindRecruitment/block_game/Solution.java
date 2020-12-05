package kakao2019BlindRecruitment.block_game;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int solution(int[][] board) {
        int answer = 0;
        int n = board.length;

        Set<Integer> set = new HashSet<>();
        Set<Integer>  a = new HashSet<>();

        boolean[] canBlack = new boolean[n];
        Arrays.fill(canBlack, true);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0 && canBlack[j]) board[i][j] = -1;
                if (canRemove(i, j, board, set)) {
                    set.add(board[i][j]);
                    answer++;
                }
            }
        }

        return answer;
    }

    private boolean canRemove(int y, int x, int[][] board, Set<Integer> set) {
        int n = board.length;
        int value = board[y][x];

        if (y + 2 < n && board[y + 1][x] == value && board[y + 2][x] == value) {
            if (x - 1 >= 0 && board[y + 2][x - 1] == value
                    && (set.contains(board[y][x - 1]) || board[y][x - 1] == -1)
                    && (set.contains(board[y + 1][x - 1]) || board[y + 1][x - 1] == -1)) return true;
            else return x + 1 < n && board[y + 2][x + 1] == value
                    && (set.contains(board[y][x + 1]) || board[y][x + 1] == -1)
                    && (set.contains(board[y + 1][x + 1]) || board[y + 1][x + 1] == -1);
        }

        if (y + 1 < n && board[y + 1][x] == value) {
            if (x - 2 >= 0 && board[y + 1][x - 1] == value && board[y + 1][x - 2] == value
                    && (set.contains(board[y][x - 1]) || board[y][x - 1] == -1)
                    && (set.contains(board[y][x - 2]) || board[y][x - 2] == -1)) return true;
            else return x + 2 < n && board[y + 1][x + 1] == value && board[y + 1][x + 2] == value
                    && (set.contains(board[y][x + 1]) || board[y][x + 1] == -1)
                    && (set.contains(board[y][x + 2]) || board[y][x + 2] == -1);
        }

        return y + 1 < n && x - 1 >= 0 && x + 1 < n && board[y + 1][x - 1] == value && board[y + 1][x] == value && board[y + 1][x + 1] == value
                && (set.contains(board[y][x - 1]) || board[y][x - 1] == -1)
                && (set.contains(board[y][x + 1]) || board[y][x + 1] == -1);
    }

    @Test
    public void test() {
        int[][] board = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                {0, 0, 0, 0, 0, 4, 4, 0, 0, 0},
                {0, 0, 0, 0, 3, 0, 4, 0, 0, 0},
                {0, 0, 0, 2, 3, 0, 0, 0, 5, 5},
                {1, 2, 2, 2, 3, 3, 0, 0, 0, 5},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 5}};

        Assert.assertEquals(2, new Solution().solution(board));
    }

    @Test
    public void test2() {

        int[][] board =
                {{0,0,0,0,0,0,0,0,0,0}
                        ,{0,0,0,0,0,0,0,0,0,0}
                        ,{0,0,0,0,0,0,0,5,0,0}
                        ,{0,0,0,0,0,0,5,5,5,0}
                        ,{0,0,0,2,2,0,0,0,0,0}
                        ,{0,0,0,2,1,0,0,0,3,0}
                        ,{0,0,0,2,1,0,0,0,3,0}
                        ,{0,0,0,0,1,1,0,3,3,0}
                        ,{0,0,0,0,0,0,0,0,0,0}
                        ,{0,0,0,0,0,0,0,0,0,0}};

        Assert.assertEquals(1, new Solution().solution(board));
    }
}
