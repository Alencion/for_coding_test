package kakao2018blindrecruitment.p1;

import java.util.Arrays;

class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];

        for (int i = 0; i < n; i++) {
            int combineElement = arr1[i] | arr2[i];
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                boolean isWall = combineElement % 2 == 1;
                if (isWall) {
                    sb.insert(0, "#");
                } else {
                    sb.insert(0, " ");
                }
                combineElement /= 2;
            }

            answer[i] = sb.toString();
        }
        return answer;
    }

    public static void main(String[] args) {
//        String[] solution = new Solution().solution(5, new int[]{9, 20, 28, 18, 11}, new int[]{30, 1, 21, 17, 28});
        String[] solution = new Solution().solution(6, new int[]{46, 33, 33, 22, 31, 50}, new int[]{27, 56, 19, 14, 14, 10});
        System.out.println(Arrays.toString(solution));
    }
}