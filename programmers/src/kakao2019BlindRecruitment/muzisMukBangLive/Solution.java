package kakao2019BlindRecruitment.muzisMukBangLive;

import java.util.Arrays;

class Solution {
    public int solution(int[] food_times, long k) {
        int n = food_times.length;
        int[] sortFood = sort(n, food_times);

        int food = n; // 섭취할 수 있는 음식의 수
        int index = 0, number = 0;
        long time = 0; // 총 걸린 시간

        while (time + food <= k) {
            time += food;
            number++;

            for (int i = index; i < n; i++) {
                if (sortFood[i] == number) {
                    index++;
                    food--;
                } else break;
            }
            if (food == 0) return -1; // 섭취할 수 있는 음식이 없다면
        }

        long count = k - time + 1;
        for (int i = 0; i < n; i++) {
            if (food_times[i] - number > 0) {
                if (--count == 0) {
                    return i + 1;
                }
            }
        }
        return -1;
    }

    private int[] sort(int n, int[] food_times) {
        int[] result = new int[n];
        System.arraycopy(food_times, 0, result, 0, n);
        Arrays.sort(result);
        return result;
    }

    public static void main(String[] args) {
        int[] food_times = {5, 2, 1, 1, 2};
        int k = 8;
        new Solution().solution(food_times, k);
    }
}