package kakao2020intern.keypad;

import org.junit.Assert;
import org.junit.Test;

public class Solution {
    public String solution(int[] numbers, String hand) {
        String answer = "";

        for (int i = 0; i < numbers.length; i++) {

        }

        return answer;
    }

    @Test
    public void test() {
        int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
        String hand = "right";

        Assert.assertEquals("LRLLLRLLRRL", new Solution().solution(numbers, hand));
    }
}
