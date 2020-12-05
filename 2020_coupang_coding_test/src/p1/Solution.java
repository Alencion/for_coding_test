package p1;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class Solution {
    public int[] solution(int N) {

        int max = 0;
        int maxIndex = 0;
        for (int i = 2; i < 10; i++) {
            String result = makeNumber(N, i);
            int mul = multiplication(result);

            if (max <= mul) {
                maxIndex = i;
                max = mul;
            }
        }

        return new int[]{maxIndex, max};
    }

    private int multiplication(String result) {
        int mul = 1;

        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) - '0' != 0) mul *= result.charAt(i) - '0';
        }

        return mul;
    }

    private String makeNumber(int n, int i) {
        StringBuilder sb = new StringBuilder();

        while (n != 0) {
            sb.append(n % i);
            n /= i;
        }

        sb.reverse();

        return sb.toString();
    }

    @Test
    public void test() {
        int N = 10;

        Assert.assertEquals(Arrays.toString(new int[]{6, 4}), Arrays.toString(new Solution().solution(N)));
    }
}
