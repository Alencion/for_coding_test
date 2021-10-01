package tonny9402.data_structure.p10799;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 쇠막대기
 * 2021-10-02
 */
public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String readline = br.readLine();

        Stack<Character> stack = new Stack<>();

        int result = 0;

        for (int i = 0; i < readline.length(); i++) {
            char c = readline.charAt(i);
            if(c == '(') {
                stack.push('(');
            } else {
                stack.pop();
                if(readline.charAt(i-1) == '('){
                    result += stack.size();
                }else {
                    result += 1;
                }
            }
        }

        System.out.println(result);
    }
}
