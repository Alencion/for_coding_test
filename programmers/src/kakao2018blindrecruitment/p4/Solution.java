package kakao2018blindrecruitment.p4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        int count = -1;
        List<List<Character>> lists = convertBoard(board);
        boolean[][] canRemove = new boolean[m][n];

        while (count != 0) {
            removeBlock(canRemove, lists);
            count = countBlock(canRemove);
            answer += count;
            updateBoard(canRemove, lists);
            canRemove = new boolean[m][n];
        }

        return answer;
    }

    public List<List<Character>> convertBoard(String[] board) {
        List<List<Character>> listBoard = new ArrayList<>();
        for (int i = 0; i < board[0].length(); i++) {
            listBoard.add(new LinkedList<>());
        }
        for (String s : board) {
            for (int j = 0; j < s.length(); j++) {
                listBoard.get(j).add(s.charAt(j));
            }
        }
        return listBoard;
    }

    public void removeBlock(boolean[][] canRemove, List<List<Character>> board) {
        for (int i = 0; i < board.size() - 1; i++) {
            for (int j = 0; j < board.get(i).size() - 1; j++) {
                char current = board.get(i).get(j);
                if (current != ' '
                        && current == board.get(i).get(j + 1)
                        && current == board.get(i + 1).get(j)
                        && current == board.get(i + 1).get(j + 1)) {
                    canRemove[j][i] = true;
                    canRemove[j][i + 1] = true;
                    canRemove[j + 1][i] = true;
                    canRemove[j + 1][i + 1] = true;
                }
            }
        }
    }

    public int countBlock(boolean[][] canRemove) {
        int count = 0;
        for (boolean[] booleans : canRemove) {
            for (boolean aBoolean : booleans) {
                if (aBoolean) count++;
            }
        }
        return count;
    }

    public void updateBoard(boolean[][] canRemove, List<List<Character>> board) {
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).size(); j++) {
                if (canRemove[j][i]){
                    board.get(i).remove(j);
                    board.get(i).add(0,' ');
                }
            }
        }
    }

    public static void main(String[] args) {
        String[] board = new String[]{"CCCB", "AAAB", "AAAB", "BBBB"};
        System.out.println(new Solution().solution(4, 4, board));
    }
}
