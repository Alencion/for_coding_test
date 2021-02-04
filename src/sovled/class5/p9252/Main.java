package sovled.class5.p9252;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String first = br.readLine();
        String second = br.readLine();

        String[][] stringDP = new String[first.length() + 1][second.length() + 1];
        Arrays.fill(stringDP[0], "");

        for (int i = 1; i < first.length() + 1; i++) {
            Arrays.fill(stringDP[i], "");
            for (int j = 1; j < second.length() + 1; j++) {
                if (first.charAt(i - 1) == second.charAt(j - 1)) {
                    stringDP[i][j] = stringDP[i - 1][j - 1] + first.charAt(i - 1);
                } else {
                    if (stringDP[i - 1][j].length() > stringDP[i][j - 1].length()) {
                        stringDP[i][j] = stringDP[i - 1][j];
                    } else {
                        stringDP[i][j] = stringDP[i][j - 1];
                    }
                }
            }
        }

        System.out.println(stringDP[first.length()][second.length()].length());
        System.out.println(stringDP[first.length()][second.length()]);
    }
}
