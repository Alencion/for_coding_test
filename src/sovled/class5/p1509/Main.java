package sovled.class5.p1509;

import java.io.*;

/**
 * 팰린드롬 분할
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = br.readLine();
        int size = str.length();
        int[] dp = new int[size + 1];
        boolean[][] palindrome = new boolean[size + 1][size + 1];

        for (int i = 1; i < size; i++) {
            palindrome[i][i] = true;

            if (str.charAt(i - 1) == str.charAt(i))
                palindrome[i][i + 1] = palindrome[i + 1][i] = true;
        }
        palindrome[size][size] = true;

        int min;
        for (int i = 1; i <= size; i++) {
            min = dp[i - 1];
            for (int j = 1; j < i; j++) {

                if (str.charAt(j - 1) == str.charAt(i - 1)) {
                    if (palindrome[j + 1][i - 1]) {
                        palindrome[j][i] = true;
                        min = Math.min(dp[j - 1], min);
                    }
                }
            }
            dp[i] = min + 1;
        }
        bw.write(dp[size] + "\n");
        bw.flush();
    }
}