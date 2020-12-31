package class4.p9935;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 문자열 폭발
 * 2020-12-30
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String readLine = br.readLine();
        String bomb = br.readLine();

        String answer = solution(readLine, bomb);


        if (answer.equals("")) System.out.println("FRULA");
        else System.out.println(answer);
    }

    private static String solution(String readLine, String bomb) {
        Stack<Character> stack = new Stack<>();
        int length = bomb.length();

        loop:
        for (int i = 0; i < readLine.length(); i++) {
            stack.push(readLine.charAt(i));

            if (stack.size() >= length) {
                for (int j = 0; j < length; j++) {
                    if (stack.get(stack.size() - length + j) != bomb.charAt(j)) {
                        continue loop;
                    }
                }
                for (int j = 0; j < length; j++) {
                    stack.pop();
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Character c : stack) {
            sb.append(c);
        }
        return sb.toString();
    }
}
