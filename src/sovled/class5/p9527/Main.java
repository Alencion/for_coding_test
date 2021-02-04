package sovled.class5.p9527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1의 개수 세기
 * 2021-01-28
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        long a = Long.parseLong(split[0]);
        long b = Long.parseLong(split[1]);

        long[] dp = new long[65];

        dp[0] = 1;
        for (int i = 0; i < dp.length; i++) {
            dp[i] = dp[i - 1] * 2;
        }

        long sumB = sum(b, dp);
        long sumA = sum(a - 1, dp);

        System.out.println(sumB - sumA);
    }

    private static long sum(long b, long[] dp) {
        long target = 1;
        while(b > target){
            target *= 2;
        }


        return 0;
    }
}

