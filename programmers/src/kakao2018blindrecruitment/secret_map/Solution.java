package kakao2018blindrecruitment.secret_map;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = {};

        boolean[][] checked = new boolean[n][n];

        decode(n, arr1, checked);
        decode(n, arr2, checked);

        answer = makeMap(checked);

        return answer;
    }

    private String[] makeMap(boolean[][] checked) {
        String[] results = new String[checked.length];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < checked.length; i++) {
            for (int j = 0; j < checked[i].length; j++) {
                if (checked[i][j]){
                    sb.append("#");
                } else {
                    sb.append(" ");
                }
            }
            results[i] = sb.toString();
            sb.setLength(0);
        }

        return results;
    }

    private void decode(int n, int[] arr1, boolean[][] checked) {
        for (int i = 0; i < checked.length; i++) {
            int divide = (int) Math.pow(2, n - 1);
            int code = arr1[i];
            for (int j = 0; j < checked.length - 1; j++) {
                int result = code / divide;
                if (result == 1) {
                    checked[i][j] = true;
                }
                code -= result * divide;
                divide /= 2;
            }
            if (code % 2 == 1) {
                checked[i][checked.length-1] = true;
            }
        }

    }

    @Test
    public void test() {
        int n = 5;
        int[] arr1 = {9, 20, 28, 18, 11};
        int[] arr2 = {30, 1, 21, 17, 28};

        Assert.assertEquals(
                Arrays.toString(new String[]{"#####", "# # #", "### #", "#  ##", "#####"}),
                Arrays.toString(new Solution().solution(n, arr1, arr2))
        );
    }
}
