package sovled.class4.p2096;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] dp = new int[2][3];
        int[][] dp2 = new int[2][3];

        String[] split;
        split = br.readLine().split(" ");
        for (int i = 0; i < 3; i++) {
            dp[0][i] = Integer.parseInt(split[i]);
            dp2[0][i] = Integer.parseInt(split[i]);
        }

        for (int i = 1; i < n; i++) {
            split = br.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                int left = j - 1 == -1 ? 0 : dp[(i - 1) % 2][j - 1];
                int leftMin = j - 1 == -1 ? 9 : dp2[(i - 1) % 2][j - 1];
                int center = dp[(i - 1) % 2][j];
                int centerMin = dp2[(i - 1) % 2][j];
                int right = j + 1 == 3 ? 0 : dp[(i - 1) % 2][j + 1];
                int rightMin = j + 1 == 3 ? 9 : dp2[(i - 1) % 2][j + 1];
                int value = Integer.parseInt(split[j]);

                dp[i % 2][j] = Math.max(Math.max(left + value, center + value), right + value);
                dp2[i % 2][j] = Math.min(Math.min(leftMin + value, centerMin + value), rightMin + value);
            }
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            max = Math.max(max, dp[(n - 1) % 2][i]);
            min = Math.min(min, dp2[(n - 1) % 2][i]);
        }

        System.out.println(max + " " + min);
    }
}

