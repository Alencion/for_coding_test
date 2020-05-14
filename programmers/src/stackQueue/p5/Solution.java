package stackQueue.p5;

import java.util.Stack;

public class Solution {
    public int solution(String arrangement) {
        int answer = 0;

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < arrangement.length(); i++) {
            char bracket = arrangement.charAt(i);

            if (bracket == '(') stack.push(i);
            else if (bracket == ')') {
                if (stack.peek() + 1 == i) {
                    stack.pop();
                    answer += stack.size();
                } else {
                    stack.pop();
                    answer += 1;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        String arragnement = "()(((()())(())()))(())";
        new Solution().solution(arragnement);
    }
}
