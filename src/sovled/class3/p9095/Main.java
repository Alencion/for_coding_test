package sovled.class3.p9095;

import java.io.*;

/**
 * 1,2,3 더하기
 * 2020-12-23 category.dp
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        int[] dp = new int[11];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for (int i = 4; i < dp.length; i++) {
            dp[i] = dp[i - 3] + dp[i - 2] + dp[i - 1];
        }

        for (int i = 0; i < t; i++) {
            int inputNumber = Integer.parseInt(br.readLine());
            bw.write(dp[inputNumber] + "\n");
        }

        bw.flush();
    }
}
