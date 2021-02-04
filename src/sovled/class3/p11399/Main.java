package sovled.class3.p11399;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ATM
 * 2020-12-24
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        String[] split = br.readLine().split(" ");
        List<Integer> times = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            times.add(Integer.parseInt(split[i]));
        }

        Collections.sort(times);

        int answer= times.get(0);
        int[] dp = new int[n];
        dp[0] = times.get(0);
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i-1] + times.get(i);
            answer += dp[i];
        }

        System.out.println(answer);
    }
}
