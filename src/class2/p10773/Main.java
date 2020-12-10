package class2.p10773;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 2020-12-10 제로
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());

        int[] array = new int[k];
        for (int i = 0; i < k; i++) {
            array[i] = Integer.parseInt(br.readLine());
        }

        int answer = solution(k, array);
        System.out.println(answer);
    }

    private static int solution(int k, int[] array) {
        Stack<Integer> stack = new Stack<>();
        for (int number : array) {
            if (number == 0) stack.pop();
            else stack.add(number);
        }

        return stack.stream().mapToInt(number -> number).sum();
    }
}
