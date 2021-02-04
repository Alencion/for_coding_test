package sovled.class2.p1874;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            list.add(Integer.parseInt(br.readLine()));
        }

        List<Character> results = new ArrayList<>();
        boolean isPossible = solution(n, list, results);

        if (!isPossible) System.out.println("NO");
        else {
            for (char result : results) {
                System.out.println(result);
            }
        }
    }

    private static boolean solution(int n, List<Integer> list, List<Character> results) {
        Stack<Integer> stack = new Stack<>();

        int startIndex = 0;
        for (int i = 1; i <= n; i++) {
            stack.add(i);
            results.add('+');

            while (!stack.isEmpty() && stack.peek().equals(list.get(startIndex))) {
                stack.pop();
                results.add('-');
                startIndex++;
            }
        }

        return stack.isEmpty();
    }
}
