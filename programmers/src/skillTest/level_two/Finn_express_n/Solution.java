package skillTest.level_two.Finn_express_n;

import org.junit.Assert;
import org.junit.Test;

public class Solution {
    public int solution(int n) {
        int answer = 0;

        for (int i = 1; i <= n; i++) {
            if (canMakeContinuedNumber(i, n))
                answer++;
        }

        return answer;
    }

    private boolean canMakeContinuedNumber(int i, int n) {
        int subTotal = 0;
        while(subTotal < n)
            subTotal += i++;
        return subTotal == n;
    }

    @Test
    public void test1(){
        int n = 15;
        Assert.assertEquals(4, new Solution().solution(n));
    }
}