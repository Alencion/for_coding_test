package class2.p4949;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str;
        while((str = br.readLine()).length() != 1 && str.charAt(0) != '.') {
            if (solution(str)) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        }
    }

    private static boolean solution(String str) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') stack.push('(');
            else if (str.charAt(i) == '[') stack.push('[');

            if (str.charAt(i) == ')' || str.charAt(i) == ']'){
                if (stack.isEmpty()) return false;
                if (str.charAt(i) == ')' && stack.peek() == '(') stack.pop();
                else if (str.charAt(i) == ']' && stack.peek() == '[') stack.pop();
                else return false;
            }
        }
        return stack.isEmpty();
    }
}
