package sovled.class4.p1918;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();

        Stack<Character> stack = new Stack<>();
        Map<Character, Integer> map = new HashMap<>();
        init(map);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            if (!map.containsKey(expression.charAt(i))){
                sb.append(expression.charAt(i));
            } else if (expression.charAt(i) == '('){
                stack.add('(');
            } else if (expression.charAt(i) == ')'){
                while(!stack.isEmpty()){
                    char op = stack.pop();
                    if (op == '(') break;
                    sb.append(op);
                }
            } else {
                while(!stack.isEmpty() && map.get(stack.peek()) >= map.get(expression.charAt(i))){
                    sb.append(stack.pop());
                }
                stack.push(expression.charAt(i));
            }
        }

        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }

        System.out.println(sb.toString());
    }

    private static void init(Map<Character, Integer> map) {
        map.put('+', 1);
        map.put('-', 1);
        map.put('*', 2);
        map.put('/', 2);
        map.put('(', 0);
        map.put(')', 0);
    }
}