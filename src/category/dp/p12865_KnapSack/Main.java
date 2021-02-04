package category.dp.p12865_KnapSack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] splits = br.readLine().split(" ");
        int n = Integer.parseInt(splits[0]);
        int k = Integer.parseInt(splits[1]);

        int[][] products = new int[n][2];
        for (int i = 0; i < n; i++) {
            splits = br.readLine().split(" ");
            products[i][0] = Integer.parseInt(splits[0]);
            products[i][1] = Integer.parseInt(splits[1]);
        }

        int answer = solution(n, k, products);
        System.out.println(answer);
    }

    private static int solution(int n, int k, int[][] products) {
        int[][] dp = new int[n+1][k+1];

        for (int i = 1; i <= n; i++) {
            int[] product = products[i-1];
            for (int j = 1; j <= k; j++) {
                if (j < product[0]) dp[i][j] = dp[i-1][j];
                else {
                    dp[i][j] = Math.max(dp[i-1][j - product[0]] + product[1], dp[i - 1][j]);
                }
            }
        }

        return dp[n][k];
    }
}
