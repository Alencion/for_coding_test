package sovled.class4.p17070;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 파이프 옮기기 1
 * 2021-01-02
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] split = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(split[j]);
            }
        }

        int[][][] dp = new int[n][n][3];

        dp[0][1][0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 2; j < n; j++) {
                if (map[i][j] == 1) continue;
                dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][1];

                if (i == 0) continue;
                dp[i][j][2] = dp[i - 1][j][1] + dp[i - 1][j][2];

                if (map[i - 1][j] == 1 || map[i][j - 1] == 1) continue;
                dp[i][j][1] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
            }
        }

        int answer = 0;
        for (int value : dp[n - 1][n - 1]) {
            answer += value;
        }

        System.out.println(answer);
    }
}
