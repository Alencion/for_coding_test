package binary_search.immigration;

import java.util.Arrays;

class Solution {
    public long solution(int n, int[] times) {
        long answer = Long.MAX_VALUE;
        long start = 0, last, mid;

        long max = Arrays.stream(times).max().orElse(0);

        last = max * n;
        while (start <= last) {
            mid = (start + last) / 2;

            long done = canImmigrate(times, mid);

            if (done < n) {
                start = mid + 1;
            } else {
                if (mid < answer) {
                    answer = mid;
                }
                last = mid - 1;
            }
        }

        return answer;
    }


    private long canImmigrate(int[] times, long mid) {
        long sum = 0;
        for (int time : times) {
            sum += mid / time;
        }
        return sum;
    }

    public static void main(String[] args) {
        int n = 6;
        int[] times = {7, 10};
        System.out.println(new Solution().solution(n, times));
    }
}