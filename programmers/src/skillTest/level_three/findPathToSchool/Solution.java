package skillTest.level_three.findPathToSchool;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;

        int[][] dp = new int[n][m];


        for(int[] puddle: puddles){
            dp[puddle[1] - 1][puddle[0] - 1] = Integer.MAX_VALUE;
        }

        boolean isPuddle = false;
        for (int i = 0; i < m; i++) {
            if (dp[0][i] == Integer.MAX_VALUE) isPuddle = true;
            if (!isPuddle)
                dp[0][i] = 1;
            else
                dp[0][i] = Integer.MAX_VALUE;
        }

        isPuddle = false;
        for (int i = 0; i < n; i++) {
            if (dp[i][0] == Integer.MAX_VALUE) isPuddle = true;
            if (!isPuddle)
            dp[i][0] = 1;
            else
                dp[i][0] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dp[i][j] == 0){
                    int up = dp[i - 1][j] == Integer.MAX_VALUE ? 0 : dp[i-1][j];
                    int left = dp[i][j - 1] == Integer.MAX_VALUE ? 0 : dp[i][j-1];
                    dp[i][j] = (up + left) % 1000000007;
                }
            }
        }
        answer = dp[n-1][m-1];
        return answer;
    }

    public static void main(String[] args) {
        int m = 5;
        int n = 4;
        int[][] puddles = {{2, 2}};
        System.out.println(new Solution().solution(m, n, puddles));
    }
}