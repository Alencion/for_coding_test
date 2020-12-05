package dp.p1904_tile01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tilesNumber = Integer.parseInt(br.readLine());

        System.out.println(solution(tilesNumber));
    }

    private static int solution(int tilesNumber) {
        if (tilesNumber == 1) return 1;
        int[] dp = new int[tilesNumber + 1];

        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= tilesNumber; i++) {
            dp[i] = (dp[i-1] + dp[i-2]) % 15746;
        }

        return dp[tilesNumber];
    }
}
