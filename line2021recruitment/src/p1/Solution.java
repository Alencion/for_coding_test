package p1;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int solution(int[][] boxes) {
        int answer = -1;

        int boxCount = boxes.length;

        Map<Integer, Integer> map = new HashMap<>();

        for (int[] box : boxes) {
            map.put(box[0], map.getOrDefault(box[0], 0 ) + 1);
            map.put(box[1], map.getOrDefault(box[1], 0 ) + 1);
        }

        for (int value : map.values()) {
            boxCount -= value / 2;
        }

        answer = boxCount;

        return answer;
    }

    @Test
    public void test(){
        int[][] boxes = {{1, 2}, {2, 1}, {3, 3}, {4, 5}, {5, 6}, {7, 8}};

        Assert.assertEquals(2, new Solution().solution(boxes));
    }
}
