package skillTest.level_three.tower_of_hanoi;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public int[][] solution(int n) {
        List<int[]> seq = new ArrayList<>();

        hanoi(n, 1, 3, 2, seq);

        int[][] answer = new int[seq.size()][2];
        for (int i = 0; i < seq.size(); ++i) {
            int[] cmd = seq.get(i);
            answer[i][0] = cmd[0];
            answer[i][1] = cmd[1];
        }

        return answer;
    }
    private void hanoi(int n, int from, int to, int via, List<int[]> seq) {
        int[] move = {from, to};

        if (n == 1) {
            seq.add(move);
        } else {
            hanoi(n - 1, from, via, to, seq);
            seq.add(move);
            hanoi(n - 1, via, to, from, seq);
        }
    }

    @Test
    public void test1() {
        int n = 2;
        System.out.println(Arrays.deepToString(new Solution().solution(n)));
    }
}