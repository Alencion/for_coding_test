package sovled.class2.p9012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 2020-12-10 괄호
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            String str = br.readLine();
            if (solution(str)) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    private static boolean solution(String str) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') stack.add('(');
            else if(!stack.isEmpty()) stack.pop();
            else return false;
        }
        return stack.isEmpty();
    }
}
