package kakao2018blindrecruitment.friends_four_block;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        int removeBlockCount = -1;
        List<List<Character>> newBoard = convertBoard(board);
        boolean[][] canRemove = new boolean[m][n];


        while (removeBlockCount != 0) {
            checkRemove(newBoard, canRemove);
            removeBlockCount = countCanRemove(canRemove);
            answer += removeBlockCount;
            updateBoard(newBoard, canRemove);
            canRemove = new boolean[m][n];
        }
        return answer;
    }

    private void updateBoard(List<List<Character>> newBoard, boolean[][] canRemove) {
        for (int i = 0; i < newBoard.size(); i++) {
            for (int j = 0; j < newBoard.get(0).size(); j++) {
                if (canRemove[j][i]){
                    newBoard.get(i).remove(j);
                    newBoard.get(i).add(0,' ');
                }
            }
        }
    }

    private int countCanRemove(boolean[][] canRemove) {
        int count =0;

        for (boolean[] canRemoveRow: canRemove){
            for (boolean canRemoveEntry: canRemoveRow){
                if (canRemoveEntry) count++;
            }
        }

        return count;
    }

    private void checkRemove(List<List<Character>> newBoard, boolean[][] canRemove) {
        for (int i = 0; i < newBoard.size() - 1; i++) {
            for (int j = 0; j < newBoard.get(i).size() - 1; j++) {
                char value = newBoard.get(i).get(j);

                if (value != ' '
                        && newBoard.get(i).get(j + 1) == value
                        && newBoard.get(i + 1).get(j) == value
                        && newBoard.get(i + 1).get(j + 1) == value) {
                    canRemove[j][i] = true;
                    canRemove[j][i + 1] = true;
                    canRemove[j + 1][i] = true;
                    canRemove[j + 1][i + 1] = true;
                }
            }
        }
    }

    private List<List<Character>> convertBoard(String[] board) {
        List<List<Character>> lists = new ArrayList<>();

        for (int i = 0; i < board[0].length(); i++) {
            lists.add(new ArrayList<>());
        }

        for (String s : board) {
            for (int j = 0; j < s.length(); j++) {
                lists.get(j).add(s.charAt(j));
            }
        }

        return lists;
    }

    @Test
    public void test() {
        int m = 4;
        int n = 5;
        String[] board = {"CCBDE", "AAADE", "AAABF", "CCBBF"};

        Assert.assertEquals(14, new Solution().solution(m, n, board));
    }

    @Test
    public void test2() {
        int m = 6;
        int n = 6;
        String[] board = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};

        Assert.assertEquals(15, new Solution().solution(m, n, board));
    }
}
