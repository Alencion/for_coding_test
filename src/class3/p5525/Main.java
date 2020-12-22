package class3.p5525;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * IOIOI
 * 2020-12-22 - KMP 알고리즘으로 풀었다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        String s = br.readLine();

        String target = makeTarget(n);
        int[] table = makeTable(target);

        int count = 0;
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            while (j > 0 && s.charAt(i) != target.charAt(j)) {
                j = table[j - 1];
            }
            if (s.charAt(i) == target.charAt(j)) {
                if (j == target.length() - 1) {
                    count++;
                    j = table[j];
                } else {
                    j++;
                }
            }
        }

        System.out.println(count);
    }

    private static int[] makeTable(String target) {
        int[] table = new int[target.length()];

        int j = 0;
        for (int i = 1; i < target.length(); i++) {
            while (j > 0 && target.charAt(i) != target.charAt(j)) {
                j = table[j - 1];
            }
            if (target.charAt(j) == target.charAt(i)) {
                table[i] = ++j;
            }
        }

        return table;
    }

    private static String makeTarget(int n) {
        StringBuilder sb = new StringBuilder();
        sb.append("I");
        for (int i = 0; i < n; i++) {
            sb.append("OI");
        }
        return sb.toString();
    }
}
