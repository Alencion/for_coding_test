package p2;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class Solution {
    public int[] solution(int[] balls, int[] orders) {
        int[] answer = {};
        List<Integer> answerList = new ArrayList<>();

        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < balls.length; i++) {
            indexMap.put(balls[i], i);
        }

        int head = 0;
        int tail = balls.length - 1;
        boolean[] hold = new boolean[balls.length];

        for (int order : orders) {
            if (balls[head] != order && balls[tail] != order) {
                hold[indexMap.get(order)] = true;
                continue;
            }

            if (balls[head] == order) {
                answerList.add(balls[head]);
                head += 1;

                while (head < tail && hold[head]) {
                    answerList.add(balls[head]);
                    head += 1;
                }
            } else {
                answerList.add(balls[tail]);
                tail -= 1;

                while (head < tail && hold[tail]) {
                    answerList.add(balls[tail]);
                    tail -= 1;
                }
            }
        }

        answer = answerList.stream().mapToInt(i -> i).toArray();
        return answer;
    }

    @Test
    public void test() {
        int[] ball = {1, 2, 3, 4, 5, 6};
        int[] order = {6, 2, 5, 1, 4, 3};

        Assert.assertEquals(Arrays.toString(new int[]{6, 5, 1, 2, 4, 3}), Arrays.toString(new Solution().solution(ball, order)));
    }

    @Test
    public void test2() {
        int[] ball = {11, 2, 9, 13, 24};
        int[] order = {13, 2, 9, 11, 24};

        Assert.assertEquals(Arrays.toString(new int[]{11, 2, 9, 13, 24}), Arrays.toString(new Solution().solution(ball, order)));
    }
}
