package kakao_code.four_speed_high_note;

import org.junit.Assert;
import org.junit.Test;

public class Solution {
    public int solution(int n) {
        return findSol(n);
    }

    public int findSol(int n) {
        int m = (int) (Math.log(n) / Math.log(3));
        return findNum(n - 2, m, 2 * m - 2);
    }

    public int findNum(int num, int m, int p) {
        if (num == 1 && m == 0 && p == 0) return 1;
        if (num <= 1) return 0;
        if (m < 0 || p < 0) return 0;
        if (p > m * 2) return 0;
        if (m == 0) return 0;

        if (num % 3 == 0) {
            return findNum(num / 3, m - 1, p) + findNum(num - 3, m, p - 3);
        } else {
            return findNum(num - num % 3, m, p - num % 3);
        }
    }

    @Test
    public void test(){
        int n = 41;
        Assert.assertEquals(1, new Solution().solution(n));
    }
}