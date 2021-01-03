package class4.p9251;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReMain {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String readLine = br.readLine();
        String readLine2 = br.readLine();

        int[][] dp = new int[readLine2.length() + 1][readLine.length() + 1];

        for (int i = 1; i < readLine.length() + 1; i++) {
            for (int j = 1; j < readLine2.length() + 1; j++) {
                if (readLine.charAt(i - 1) == readLine2.charAt(j - 1)) {
                    dp[j][i] = dp[j - 1][i - 1] + 1;
                } else {
                    dp[j][i] = Math.max(dp[j - 1][i], dp[j][i - 1]);
                }
            }
        }

        System.out.println(dp[readLine2.length()][readLine.length()]);

    }
}
