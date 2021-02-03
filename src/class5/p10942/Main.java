package class5.p10942;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 펠린드롬?
 * 2021-02-03
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];

        String[] split = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(split[i]);
        }

        boolean[][] isPalindrome = new boolean[n][n];

        for (int k = 0; k < n; k++) {
            for (int i = 0; i + k < n; i++) {
                int j = i + k;

                if (i == j) {
                    isPalindrome[i][j] = true;
                } else if (j - i == 1) {
                    if (nums[i] == nums[j]) {
                        isPalindrome[i][j] = true;
                    }
                } else {
                    if (isPalindrome[i + 1][j - 1] && nums[i] == nums[j]) {
                        isPalindrome[i][j] = true;
                    }
                }
            }
        }

        int m = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            split = br.readLine().split(" ");
            int begin = Integer.parseInt(split[0]) - 1;
            int end = Integer.parseInt(split[1]) - 1;

            if (isPalindrome[begin][end]) sb.append(1).append(System.lineSeparator());
            else sb.append(0).append(System.lineSeparator());
        }
        System.out.println(sb.toString());
    }
}
