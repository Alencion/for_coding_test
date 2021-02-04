package sovled.class2.p11049;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int length = Integer.parseInt(br.readLine());
        int[][] matrices = new int[length][2];

        String[] split;
        for (int i = 0; i < length; i++) {
            split = br.readLine().split(" ");

            matrices[i][0] = Integer.parseInt(split[0]);
            matrices[i][1] = Integer.parseInt(split[1]);
        }

        int answer = solution(length, matrices);
        System.out.println(answer);
    }

    private static int solution(int length, int[][] matrices) {
        int[][] dp = new int[length][length];

        for (int diagonal = 1; diagonal < length; diagonal++) {
            for (int i = 0; i < length - diagonal; i++) {
                dp[i][i + diagonal] = Integer.MAX_VALUE;

                for (int j = i; j < i + diagonal; j++) {
                    int cost = dp[i][j] + dp[j + 1][i + diagonal] + matrices[i][0] * matrices[j][1] * matrices[i + diagonal][1];
                    dp[i][i + diagonal] = Math.min(dp[i][i + diagonal], cost);
                }
            }
        }

        return dp[0][length - 1];
    }
}
