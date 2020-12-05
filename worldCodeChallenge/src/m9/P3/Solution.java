package m9.P3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Solution {
    public int solution(int[] a) {
        int answer = 0;

        int l = 1000000000, r = 1000000000;

        for (int i = 0; i < a.length; i++) {
            if (a[i] < l) {
                answer++;
                l = a[i];
            }

            if (a[a.length - 1 - i] < r) {
                answer++;
                r = a[a.length - 1 - i];
            }

            if (l == r)
                break;
        }

        return answer + (l == r ? -1 : 0);
    }

    @Test
    public void test(){
        int[] a = {9, -1, -5};

        Assertions.assertEquals(3, solution(a));
    }
}
