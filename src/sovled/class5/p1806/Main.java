package sovled.class5.p1806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 부분합
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int s = Integer.parseInt(split[1]);

        int[] array = new int[n];

        split = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(split[i]);
        }

        int sum = 0;
        int start = 0;
        int end = 0;
        int answer = Integer.MAX_VALUE;

        while (true) {
            if (sum >= s) sum -=array[start++];
            else if (end == n) break;
            else {
                sum += array[end++];
            }
            if (sum >= s)
                answer = Math.min(answer, end - start);
        }

        if (answer == Integer.MAX_VALUE) System.out.println(0);
        else System.out.println(answer);
    }
}
