package sovled.class4.p9465;

import java.io.*;

/**
 * 스티커
 * 2020-12-30
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());

            int[][] costs = new int[2][n];
            for (int j = 0; j < 2; j++) {
                String[] split = br.readLine().split(" ");
                for (int k = 0; k < n; k++) {
                    costs[j][k] = Integer.parseInt(split[k]);
                }
            }

            int[][] dp = new int[2][n];
            dp[0][0] = costs[0][0];
            dp[1][0] = costs[1][0];

            for (int j = 1; j < n; j++) {
                for (int k = 0; k < 2; k++) {
                    dp[k][j] = Math.max(dp[k][j - 1], dp[(k + 1) % 2][j - 1] + costs[k][j]);
                }
            }

            bw.append(Math.max(dp[0][n - 1], dp[1][n - 1]) + "\n");
        }
        bw.flush();
    }
}
