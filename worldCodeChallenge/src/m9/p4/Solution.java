package m9.p4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Solution {
    public int solution(int[][] a) {
        int MOD = 10000019;

        int r = a.length;
        int c = a[0].length;

        long[][] combination = new long[r + 1][r + 1];
        combination[0][0] = 1;

        for (int i = 1; i <= r; i++) {
            combination[i][0] = 1;
            for (int j = 1; j < r; j++)
                combination[i][j] = (combination[i - 1][j - 1] + combination[i - 1][j]);
            combination[i][i] = 1;
        }

        int[] aSum = new int[c];

        for (int j = 0; j < c; j++) {
            for (int i = 0; i < r; i++) {
                aSum[j] += a[i][j];
            }
        }

        long[][] dp = new long[r + 1][c];

        dp[aSum[0]][0] = combination[r][aSum[0]];

        for (int j = 1; j < c; j++) {
            int n = aSum[j];

            for (int i = 0; i <= r; i++) {
                int p = Math.min(n, i);
                int q = r - Math.max(n, i);
                int s = r - p - q;

                for (int k = 0; k <= Math.min(p, q); k++) {
                    dp[s + 2 * k][j] += (dp[i][j - 1] * (combination[i][p - k] * combination[r - i][n - (p - k)] % MOD)) % MOD;
                }
            }
        }

        return (int) (dp[0][c - 1] % MOD);
    }

    @Test
    public void test(){
        int[][] a = {{0,1,0,1},{1,1,1,0},{1,1,0,1},{0,1,1,0}};

        Assertions.assertEquals(24, solution(a));
    }
}