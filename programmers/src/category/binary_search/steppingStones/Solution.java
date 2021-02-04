package category.binary_search.steppingStones;

import java.util.Arrays;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        int left = 1, right = distance, mid;

        Arrays.sort(rocks);

        while (left <= right) {
            mid = (left + right) / 2;
            int removeCount = 0;
            int prevLocation = 0;

            for (int rock : rocks) {
                if (rock - prevLocation < mid) {
                    removeCount++;
                } else {
                    prevLocation = rock;
                }
            }

            if (distance - prevLocation < mid) removeCount++;

            if (removeCount <= n) {
                answer = Math.max(mid, answer);
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }

        return answer;
    }

    public static void main(String[] args) {
        int distance = 25;
        int[] rocks = {2, 14, 11, 21, 17};
        int n = 2;
        System.out.println(new Solution().solution(distance, rocks, n));
    }
}