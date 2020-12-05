package skillTest.level_three.pedestrian_heaven;

import org.junit.Assert;
import org.junit.Test;

public class Solution {
    int MOD = 20170805;

    public int solution(int m, int n, int[][] cityMap) {
        int answer = 0;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            if (cityMap[i][0] == 1) {
                break;
            }
            if (cityMap[i][0] == 2) {
                dp[i][0] = 0;
            } else {
                dp[i][0] = 1;
            }
        }

        for (int i = 0; i < n; i++) {
            if (cityMap[0][i] == 1) {
                break;
            }
            if (cityMap[0][i] == 2) {
                dp[0][i] = 0;
            } else {
                dp[0][i] = 1;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int sign = cityMap[i][j];
                int leftSign = cityMap[i][j - 1];

                int up, left;

                if (sign == 1 || sign == 2) {
                    dp[i][j] = 0;
                } else {
                    up = getValue(i - 1, j, dp, cityMap, 0);
                    left = getValue(i, j - 1, dp, cityMap, 1);
                    dp[i][j] = (up + left) % MOD;
                }
            }
        }
        answer = dp[m - 1][n - 1];
        return answer;
    }

    private int getValue(int i, int j, int[][] dp, int[][] cityMap, int direct) {
        if (i < 0) return 0;
        if (j < 0) return 0;
        if (direct == 0 && cityMap[i][j] == 2) return getValue(i - 1, j, dp, cityMap, 0);
        if (direct == 1 && cityMap[i][j] == 2) return getValue(i, j - 1, dp, cityMap, 1);
        return dp[i][j];
    }

    @Test
    public void test() {
        int m = 3;
        int n = 3;
        int[][] cityMap = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};

        Assert.assertEquals(6, new Solution().solution(m, n, cityMap));
    }

    @Test
    public void test2() {
        int m = 3;
        int n = 6;
        int[][] cityMap = {{0, 2, 0, 0, 0, 2}, {0, 0, 2, 0, 1, 0}, {1, 0, 0, 2, 2, 0}};

        Assert.assertEquals(2, new Solution().solution(m, n, cityMap));
    }
}
