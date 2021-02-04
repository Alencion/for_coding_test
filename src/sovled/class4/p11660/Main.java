package sovled.class4.p11660;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 구간합 구하기 5
 * 2020-12-31
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);

        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            split = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                int up = i - 1 < 0 ? 0 : dp[i - 1][j];
                int left = j - 1 < 0 ? 0 : dp[i][j - 1];
                int upLeft = i - 1 < 0 || j - 1 < 0 ? 0 : dp[i - 1][j - 1];
                dp[i][j] = up + left + Integer.parseInt(split[j]) - upLeft;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            split = br.readLine().split(" ");
            int y1 = Integer.parseInt(split[0]) - 1;
            int x1 = Integer.parseInt(split[1]) - 1;
            int y2 = Integer.parseInt(split[2]) - 1;
            int x2 = Integer.parseInt(split[3]) - 1;

            sb.append(solution(y1, x1, y2, x2, dp)).append(System.lineSeparator());
        }

        System.out.println(sb.toString());
    }

    private static int solution(int y1, int x1, int y2, int x2, int[][] dp) {
        int up = y1 - 1 < 0 ? 0 : dp[y1 - 1][x2];
        int left = x1 - 1 < 0 ? 0 : dp[y2][x1 - 1];
        int upLeft = y1 - 1 < 0 || x1 - 1 < 0 ? 0 : dp[y1 - 1][x1 - 1];

        return dp[y2][x2] - up - left + upLeft;
    }
}
