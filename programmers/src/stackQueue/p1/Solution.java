package stackQueue.p1;

import java.util.Stack;

public class Solution {
    public int[] solution(int[] heights) {
        int[] answer = new int[heights.length];

        Stack<Integer> queue = new Stack<>();
        int currentTop;

        for (int i = heights.length - 1; i >= 0; i--) {
            currentTop = heights[i];
            while (!queue.isEmpty() && heights[queue.peek()] < currentTop) {
                answer[queue.pop()] = i + 1;
            }
            queue.push(i);
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] heights = {6, 9, 5, 7, 4};
        new Solution().solution(heights);
    }
}

