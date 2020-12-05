package p3;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class Solution {
    public int[] solution(int n) {
        int[] answer = {};

        answer = binarySearch(String.valueOf(n));

        return answer;
    }

    private int[] binarySearch(String n) {
        int[] result = new int[2];
        int count = 0;

        while (Integer.parseInt(n) >= 10) {
            int start = 0;
            int end = n.length();
            int mid = (start + end) / 2;

            String front = n.substring(0, mid);
            String back = n.substring(mid);

            if (back.length() > 1 && back.charAt(0) != '0') {
                String nextFront = n.substring(0, mid + 1);
                String nextBack = n.substring(mid + 1);

                if (Integer.parseInt(front) + Integer.parseInt(back) > Integer.parseInt(nextFront) + Integer.parseInt(nextBack)) {
                    front = nextFront;
                    back = nextBack;
                }
            } else {
                int i = 0;
                while (back.length() - i > 1 && back.charAt(i) == '0') {
                    i++;
                }

                if (i != 0) {
                    front = n.substring(0, mid + i);
                    back = n.substring(mid + i);
                }
            }

            n = String.valueOf(Integer.parseInt(front) + Integer.parseInt(back));
            count++;
        }

        result[0] = count;
        result[1] = Integer.parseInt(n);
        return result;
    }

    @Test
    public void test() {
        int n = 73425;

        Assert.assertArrayEquals(new int[]{4, 3}, solution(n));
    }

    @Test
    public void test2() {
        int n = 10007;

        Assert.assertEquals(Arrays.toString(new int[]{4, 8}), Arrays.toString(new Solution().solution(n)));
    }

    @Test
    public void test3() {
        int n = 494;

        Assert.assertEquals(Arrays.toString(new int[]{2, 8}), Arrays.toString(new Solution().solution(n)));
    }
}
