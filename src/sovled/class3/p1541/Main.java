package sovled.class3.p1541;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/***
 * 잃어버린 괄호
 * 2020-12-18
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String readLine = br.readLine();

        List<Integer> operand = new ArrayList<>();
        List<Character> operator = new ArrayList<>();

        int prevIndex = 0;
        for (int i = 0; i < readLine.length(); i++) {
            if (!Character.isDigit(readLine.charAt(i))){
                operand.add(Integer.parseInt(readLine.substring(prevIndex, i)));
                operator.add(readLine.charAt(i));
                prevIndex = i + 1;
            }
        }
        operand.add(Integer.parseInt(readLine.substring(prevIndex)));

        int n = operand.size();
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = operand.get(i);
        }

        for (int diagonal = 1; diagonal < n; diagonal++) {
            for (int i = 0; i < n - diagonal; i++) {
                dp[i][i + diagonal] = Integer.MAX_VALUE;

                for (int j = i; j < i + diagonal; j++) {
                    int cost = calculate(dp[i][j], dp[j + 1][i + diagonal], operator.get(j));

                    dp[i][i + diagonal] = Math.min(dp[i][i + diagonal], cost);
                }
            }
        }

        System.out.println(dp[0][n - 1]);
    }

    private static int calculate(int a, int b, char op) {
        if (op == '-') {
            return a - b;
        }
        return a + b;
    }
}
