package sovled.class2.p1654;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    static long max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");

        int K = Integer.parseInt(split[0]);
        int N = Integer.parseInt(split[1]);

        List<Integer> lanCables = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            lanCables.add(Integer.parseInt(br.readLine()));
        }

        long answer = solution(N, lanCables);
        System.out.println(answer);
    }

    private static long solution(long n, List<Integer> lanCables) {
        long end = Collections.max(lanCables);
        biSearch(1, end, lanCables, n);
        return max;
    }

    private static void biSearch(long start, long end, List<Integer> lanCables, long n) {
        if (start > end) return;
        long mid = (start + end) / 2;
        long sum = 0;

        for (long lanCable : lanCables) {
            sum += lanCable / mid;
        }

        if (sum >= n) {
            max = Math.max(max, mid);
            biSearch(mid + 1, end, lanCables, n);
        }
        else biSearch(start, mid - 1, lanCables, n);
    }
}
