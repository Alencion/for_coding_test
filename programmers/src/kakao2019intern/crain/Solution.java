package kakao2019intern.crain;

import java.util.Stack;

public class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer>[] convertBoard = convertBoard(board);
        Stack<Integer> popDollStack = new Stack<>();

        for (int move : moves) {
            move = move - 1;
            if (!convertBoard[move].isEmpty()) {
                int pop = convertBoard[move].pop();
                if (!popDollStack.isEmpty() && popDollStack.peek() == pop) {
                    popDollStack.pop();
                    answer += 2;
                } else {
                    popDollStack.push(pop);
                }
            }
        }

        return answer;
    }

    public Stack[] convertBoard(int[][] board) {
        Stack[] stacks = new Stack[board.length];

        for (int i = board.length - 1; i >= 0; i--) {
            for (int j = 0; j < board[i].length; j++) {
                if (i == board.length - 1) stacks[j] = new Stack<Integer>();
                if (board[i][j] != 0)
                    stacks[j].push(board[i][j]);
            }
        }
        return stacks;
    }

    public static void main(String[] args) {
        int[][] board = new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 3},
                {0, 2, 5, 0, 1},
                {4, 2, 4, 4, 2},
                {3, 5, 1, 3, 1}};
        int[] moves = new int[]{1, 5, 3, 5, 1, 2, 1, 4};

        new Solution().solution(board, moves);
    }
}