package kakao2019BlindRecruitment.muzisMukBangLive.anotherSolution;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

public class Solution {
    public int solution(int[] food_times, long k) {
        int answer = 0;

        int foodCount = food_times.length;
        int index = 0, number = 0;
        long time = 0;

        HashSet<Integer> set = new HashSet<>();

        while (foodCount + time <= k) {
            time += foodCount;
            number++;

            for (int i = 0; i < food_times.length; i++) {
                if (set.contains(i)) continue;
                if (food_times[i] > 1) {
                    food_times[i]--;
                } else {
                    food_times[i]--;
                    foodCount--;
                    set.add(i);
                }
            }

            if (foodCount == 0) return -1;
        }

        k = k - time + 1;
        for (int i = 0; i < food_times.length; i++) {
            if (food_times[i] == 0) continue;
            k--;
            if (k == 0) return i + 1;
        }


        return answer;
    }

    @Test
    public void test() {
        int[] food_times = {3, 1, 2};
        int k = 5;

        Assert.assertEquals(1, new Solution().solution(food_times, k));

        food_times = new int[]{5, 2, 1, 1, 2};
        k = 8;

        Assert.assertEquals(1, new Solution().solution(food_times, k));
    }
}
