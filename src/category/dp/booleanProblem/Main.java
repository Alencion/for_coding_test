package category.dp.booleanProblem;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    @Test
    public void test() {
        String expression = "0&0&0&1^1|0";

        int answer = solution(expression, true);
        System.out.println(answer);
    }

    private int solution(String expression, boolean target) {
        List<Character> operands = new ArrayList<>();
        List<Character> operations = new ArrayList<>();

        extract(operands, operations, expression);
        int size = operands.size();

        int[][][] dp = new int[size][size][2];

        for (int i = 0; i < size; i++) {
            char c = operands.get(i);
            if (c == '1') dp[i][i][0] = 1;
            else dp[i][i][1] = 1;
        }

        for (int diagonal = 1; diagonal < size; diagonal++) {
            for (int i = 0; i < size - diagonal; i++) {
                for (int j = i; j < i + diagonal; j++) {
                    char op = operations.get(j);
                    calc(dp[i][i + diagonal], dp[i][j], dp[j + 1][i + diagonal], op);
                }
            }
        }

        return target ? dp[0][size - 1][0] : dp[0][size - 1][1];
    }

    private void calc(int[] object, int[] start, int[] end, char op) {
        if (op == '^') {
            object[0] += start[0] * end[1] + start[1] * end[0];
            object[1] += start[0] * end[0] + start[1] * end[1];
            return;
        }

        if (op == '|') {
            object[0] += start[0] * end[1] + start[1] * end[0] + start[0] * end[0];
            object[1] += start[1] * end[1];
            return;
        }

        if (op == '&') {
            object[0] += start[0] * end[0];
            object[1] += start[0] * end[1] + start[1] * end[0] + start[1] * end[1];
        }
    }

    private void extract(List<Character> operands, List<Character> operations, String expression) {
        if (expression.length() < 1) return;

        char c = expression.charAt(0);
        operands.add(c);

        for (int i = 1; i < expression.length(); i += 2) {
            operations.add(expression.charAt(i));
            operands.add(expression.charAt(i + 1));
        }
    }

    @Test
    public void test2() {
        long[] dp = new long[36];

        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }

        System.out.println(Arrays.toString(dp));
    }
}
