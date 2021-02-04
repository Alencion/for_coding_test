package sovled.class5.p7579;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);

        int[] activeAppMemories = new int[n];
        int[] reactiveAppCosts = new int[n];

        String[] memoriesData = br.readLine().split(" ");
        String[] costsData = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            activeAppMemories[i] = Integer.parseInt(memoriesData[i]);
            reactiveAppCosts[i] = Integer.parseInt(costsData[i]);
        }

        int[][] dp = new int[n][100001];

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int cost = reactiveAppCosts[i];
            int memory = activeAppMemories[i];

            for (int j = 0; j <= 10000; j++) {
                // 앱이 하나일 경우 예외처리
                if (i == 0) {
                    if (j >= cost) dp[i][j] = memory;
                } else {
                    if (j >= cost) dp[i][j] = Math.max(dp[i - 1][j - cost] + memory, dp[i - 1][j]);
                    else dp[i][j] = dp[i - 1][j];
                }

                if (dp[i][j] >= m) answer = Math.min(answer, j);
            }
        }

        System.out.println(answer);
    }
}