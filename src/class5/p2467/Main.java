package class5.p2467;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 용액
 * 2021-01-08
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        String[] split = br.readLine().split(" ");
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(split[i]);
        }

        int start = 0;
        int end = n - 1;
        int toZero = array[start] + array[end];
        int left = array[start], right = array[end];

        while (start < end) {
            int ph = array[start] + array[end];

            if (Math.abs(toZero) > Math.abs(ph)) {
                toZero = Math.abs(ph);
                left = array[start];
                right = array[end];
            }

            if (ph > 0) {
                end--;
            } else {
                start++;
            }
        }

        System.out.println(left + " " + right);
    }
}
