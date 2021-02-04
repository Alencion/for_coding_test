package sovled.class5.p1562;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 계단 수
 * 2021-01-05
 */
public class Main {
    public static int n, VISIT = 1 << 10, MOD = 1000000000;
    public static long dp[][][] = new long[101][10][VISIT];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        System.out.println(calc());
    }

    private static long calc() {
        long sum = 0;
        int i, j, k, bit;

        for (i = 1; i < 10; i++) {
            dp[1][i][1 << i] = 1;
        }

        for (i = 2; i <= n; i++)
            for (j = 0; j <= 9; j++)
                for (k = 0; k < VISIT; k++) {
                    bit = k | (1 << j);

                    dp[i][j][bit] += (((0 < j ? dp[i - 1][j - 1][k] : 0) +
                                    (j < 9 ? dp[i - 1][j + 1][k] : 0)) % MOD) % MOD;
                }

        for (i = 0; i < 10; i++) {
            sum = (sum + dp[n][i][VISIT - 1]) % MOD;
        }

        return sum;
    }
}
