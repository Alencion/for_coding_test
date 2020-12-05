package p3;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {

    public int solution(int k, int[] score) {

        int answer = score.length;

        Map<Integer, Set<Integer>> map = new HashMap<>();
        Map<Integer, Integer> scoreCount = new HashMap<>();

        int prev = score[0];
        for (int i = 1; i < score.length; i++) {
            int gap = prev - score[i];
            prev = score[i];

            if (!map.containsKey(gap)) map.put(gap, new HashSet<>());
            scoreCount.put(gap, scoreCount.getOrDefault(gap, 0) + 1);

            Set<Integer> set = map.get(gap);
            set.add(i - 1);
            set.add(i);
        }

        Set<Integer> distinct = new HashSet<>();
        for (Map.Entry<Integer, Integer> entry : scoreCount.entrySet()) {
            if (entry.getValue() >= k) {
                distinct.addAll(map.get(entry.getKey()));
            }
        }

        if (distinct.isEmpty()) return 0;
        return answer - distinct.size();
    }

    @Test
    public void test() {
        int k = 3;
        int[] score = {24, 22, 20, 10, 5, 3, 2, 1};

        Assert.assertEquals(3, new Solution().solution(k, score));
    }
}
