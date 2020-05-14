package kakao2019intern.steppingStrones;

import java.util.Arrays;

public class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;

        int start = Arrays.stream(stones).min().getAsInt();
        int end = Arrays.stream(stones).max().getAsInt();

        while (start <= end) {
            int mid = (start + end) / 2;
            if (cross(mid, stones.clone(), k)) {
                answer = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return answer;
    }

    public boolean cross(int num, int[] stones, int k) {
        Arrays.setAll(stones, i -> (stones[i] - num + 1));

        int max = 0, cnt = 0;
        for (int stone : stones) {
            if (cnt > 0 && stone > 0) {
                max = Math.max(max, cnt);
                cnt = 0;
            } else if (stone <= 0) {
                cnt++;
            }
        }

        return Math.max(max, cnt) < k;
    }

    public static void main(String[] args) {
        int[] stones = new int[]{2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        int k = 3;
        int solution = new Solution().solution(stones, k);
    }
}