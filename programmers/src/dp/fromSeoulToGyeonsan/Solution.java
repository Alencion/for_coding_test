package dp.fromSeoulToGyeonsan;

class Solution {
    public int solution(int K, int[][] travel) {
        int answer = 0;

        int[][] dp = new int[travel.length][K + 1];
        dp[0][travel[0][0]] = travel[0][1];
        dp[0][travel[0][2]] = travel[0][3];

        for (int i = 1; i < travel.length; i++) {
            for (int k = 0; k <= K; k++) {
                if (dp[i - 1][k] == 0) {
                    continue;
                }

                if (travel[i][0] + k <= K) {
                    dp[i][k + travel[i][0]] = Math.max(dp[i][k + travel[i][0]], dp[i - 1][k] + travel[i][1]);
                    answer = Math.max(answer, dp[i][k + travel[i][0]]);
                }

                if (travel[i][2] + k <= K) {
                    dp[i][k + travel[i][2]] = Math.max(dp[i][k + travel[i][2]], dp[i - 1][k] + travel[i][3]);
                    answer = Math.max(answer, dp[i][k + travel[i][2]]);
                }

            }
        }


        return answer;
    }

    public static void main(String[] args) {
        int K = 1650;
        int[][] travel = {{500, 200, 200, 100}
                , {800, 370, 300, 120}
                , {700, 250, 300, 90}};
        System.out.println(new Solution().solution(K, travel));
    }
}