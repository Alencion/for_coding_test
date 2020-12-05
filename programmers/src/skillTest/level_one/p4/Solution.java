package skillTest.level_one.p4;

import java.util.Arrays;

class Solution {
    public int[] solution(int[] arr) {
        int[] answer = {};
        if (arr.length == 1) return new int[]{-1};
        int asInt = Arrays.stream(arr).min().getAsInt();
        answer = Arrays.stream(arr).filter(value -> value != asInt).toArray();

        return answer;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] solution1 = solution.solution(new int[]{4, 3, 2, 1});
        System.out.println(Arrays.toString(solution1));
    }
}