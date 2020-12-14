package class3.p1003;

import java.io.*;

public class Main {
    static int[][] dp = new int[41][2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        init();
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            int[] answer = dp[n];
            bw.write(answer[0] + " " + answer[1] + "\n");
        }
        bw.flush();
    }

    private static void init() {
        dp[0][0] = 1;
        dp[0][1] = 0;
        dp[1][0] = 0;
        dp[1][1] = 1;

        for (int i = 2; i < 41; i++) {
            dp[i][0] = dp[i - 1][0] + dp[i - 2][0];
            dp[i][1] = dp[i - 1][1] + dp[i - 2][1];
        }
    }
}
