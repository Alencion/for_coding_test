package tonny9402.data_structure.p1935;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Character, Integer> map = new HashMap<>();

        int n = Integer.parseInt(br.readLine());

        String expression = br.readLine();

        char c = 'A';
        for (int i = 0; i < n; i++) {
            int value = Integer.parseInt(br.readLine());

            map.put((char) (c + i), value);
        }

        Stack<Double> stack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            c = expression.charAt(i);
            if (isOp(c)) {
                double operand1 = stack.pop();
                double operand2 = stack.pop();

                stack.push(calculate(c, operand1, operand2));
            } else {
                int value = map.get(c);
                stack.push((double) value);
            }
        }
        System.out.printf("%.2f%n", stack.peek());
    }

    private static Double calculate(char operator, double operand1, double operand2) {
        if (operator == '+') {
            return operand2 + operand1;
        }
        if (operator == '-') {
            return operand2 - operand1;
        }
        if (operator == '*') {
            return operand2 * operand1;
        }
        if (operator == '/') {
            return ((double) operand2) / operand1;
        }

        return 0.0;
    }

    public static boolean isOp(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
}
