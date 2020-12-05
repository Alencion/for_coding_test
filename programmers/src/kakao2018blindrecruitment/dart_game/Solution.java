package kakao2018blindrecruitment.dart_game;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class Solution {
    public int solution(String dartResult) {
        int answer = 0;

        Queue<Character> queue = new LinkedList<>();

        for (char c : dartResult.toCharArray()) {
            queue.add(c);
        }

        int prevScore = 0;
        while (!queue.isEmpty()) {
            char poll = queue.poll();
            StringBuilder scoreStr = new StringBuilder();
            while (!queue.isEmpty() && Character.isDigit(poll)) {
                scoreStr.append(poll);
                poll = queue.poll();
            }
            int score = Integer.parseInt(scoreStr.toString());

            if (poll == 'D') score = score * score;
            else if (poll == 'T') score = score * score * score;

            if (!queue.isEmpty() && queue.peek() == '*') {
                queue.poll();
                score *= 2;
                answer += prevScore;
            } else if (!queue.isEmpty() && queue.peek() == '#') {
                score *= -1;
                queue.poll();
            }
            prevScore = score;
            answer += score;
        }

        return answer;
    }


    @Test
    public void test() {
        String dartResult = "1D2S#10S";

        Assert.assertEquals(37, new Solution().solution(dartResult));
    }
}
