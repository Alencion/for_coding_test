package class4.p18119;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 단어 암기
 * 2020-01-02
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);

        boolean[][] words = new boolean[n][26];
        int[] forgot = new int[n];
        for (int i = 0; i < n; i++) {
            String readLine = br.readLine();
            for (char c : readLine.toCharArray()) {

                words[i][c - 'a'] = true;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            split = br.readLine().split(" ");
            int op = Integer.parseInt(split[0]);
            char c = split[1].charAt(0);

            check(op, c, words, forgot);
            int answer = count(forgot);
            sb.append(answer).append(System.lineSeparator());
        }
        System.out.println(sb.toString());
    }

    private static int count(int[] forgot) {
        int count = 0;
        for (int i : forgot) {
            if (i == 0) count++;
        }
        return count;
    }

    private static void check(int op, char c, boolean[][] words, int[] forgot) {
        for (int i = 0; i < words.length; i++) {
            if (op == 1) {
                if (words[i][c - 'a']) forgot[i]++;
            } else {
                if (words[i][c - 'a']) forgot[i]--;
            }
        }
    }
}
