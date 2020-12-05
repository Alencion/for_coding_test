package String.KMP;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Code {
    public String text = "ababacabacaabacaaba";
    public String pattern = "abacaaba";

    public int[] makeKMPTable(String pattern) {
        int size = pattern.length();
        int[] table = new int[size];

        for (int i = 1, j = 0; i < size; i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = table[j - 1];
            }

            if (pattern.charAt(i) == pattern.charAt(j)) {
                table[i] = ++j;
            }
        }

        return table;
    }

    public int[] findMatchingIndex() {
        List<Integer> result = new ArrayList<>();
        int[] table = makeKMPTable(pattern);

        for (int i = 0, j = 0; i < text.length(); i++) {
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                j = table[j - 1];
            }
            if (text.charAt(i) == pattern.charAt(j)) {
                if (j == pattern.length() - 1) {
                    result.add(i - pattern.length() + 2);
                    j = table[j];
                } else {
                    j++;
                }
            }
        }
        return result.stream().mapToInt(i -> i).toArray();
    }

    @Test
    void KMPTest() {
        int[] matchingIndex = findMatchingIndex();

        for (int index : matchingIndex) {
            System.out.print(index + " ");
        }
    }
}
