package sovled.class4.p1786;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 찾기
 * 2020-12-26
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String text = br.readLine();
        String pattern = br.readLine();

        int[] table = makeTable(pattern);

        List<Integer> result = new ArrayList<>();

        int j = 0;
        for (int i = 0; i < text.length(); i++) {
            while (j > 0 && pattern.charAt(j) != text.charAt(i)) {
                j = table[j - 1];
            }
            if (pattern.charAt(j) == text.charAt(i)) {
                if (j + 1 == pattern.length()) {
                    result.add(i - pattern.length() + 2);
                    j = table[j];
                } else {
                    j++;
                }
            }
        }

        System.out.println(result.size());
        result.forEach(System.out::println);
    }

    private static int[] makeTable(String pattern) {
        int[] table = new int[pattern.length()];

        int j = 0;
        for (int i = 1; i < pattern.length(); i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = table[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                table[i] = ++j;
            }
        }

        return table;
    }
}