package skillTest.level_three.n_tiling;

import org.junit.Assert;
import org.junit.Test;

public class Solution {
    public int solution(int n) {
        int answer = 0;
        int[] dp = new int[n];

        dp[0] = 1;
        dp[1] = 2;

        for (int i = 2; i < n; i++) {
            dp[i] =  (dp[i-1] + dp[i-2]) % 1000000007;
        }
        answer = dp[n-1];
        return answer;
    }

    @Test
    public void test(){
        int n = 4;
        Assert.assertEquals(5, new Solution().solution(n));
    }
}
