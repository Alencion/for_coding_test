package sovled.class4.p1932;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] triangle = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] split = br.readLine().split(" ");

            for (int j = 0; j < split.length; j++) {
                triangle[i][j] = Integer.parseInt(split[j]);
            }
        }

        int answer = solution(n, triangle);
        System.out.println(answer);
    }

    private static int solution(int n, int[][] triangle) {

        int[][] dp = new int[n][n];

        dp[0][0] = triangle[0][0];

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i + 1; j++) {
                int left = j - 1 == -1 ? 0 : dp[i - 1][j - 1];
                int right = dp[i - 1][j];

                dp[i][j] = Math.max(left + triangle[i][j], right + triangle[i][j]);
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(dp[n-1][i], max);
        }

        return max;
    }


}
