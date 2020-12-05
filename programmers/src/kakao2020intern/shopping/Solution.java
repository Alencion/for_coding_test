package kakao2020intern.shopping;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public int[] solution(String[] gems) {
        Queue<String> queue = new LinkedList<>();
        HashMap<String, Integer> map = new HashMap<>();
        int size = Arrays.stream(gems).collect(Collectors.toSet()).size();

        int start = 0;
        int end = Integer.MAX_VALUE;

        int startPoint = 0;

        for (String gem : gems) {
            map.put(gem, map.getOrDefault(gem, 0) + 1);

            queue.add(gem);

            while (true) {
                String temp = queue.peek();
                if (map.get(temp) > 1) {
                    queue.poll();
                    start++;
                    map.put(temp, map.get(temp) - 1);
                } else {
                    break;
                }
            }
            if (map.size() == size && end > queue.size()) {
                end = queue.size();
                startPoint = start;
            }


        }
        return new int[]{startPoint + 1, startPoint + end};
    }

    @Test
    public void test() {
        String[] gems = {"AA", "AB", "AB", "AC", "AA", "AC"};
        Assert.assertEquals(Arrays.toString(new int[]{2, 4}), Arrays.toString(new Solution().solution(gems)));
    }

    @Test
    public void test1() {
        String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
        Assert.assertEquals(Arrays.toString(new int[]{3, 7}), Arrays.toString(new Solution().solution(gems)));
    }
}
