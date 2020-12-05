package p2;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class Solution {
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};

        List<String> answerList = new ArrayList<>();

        for (int number : course) {
            Map<String, Integer> map = new HashMap<>();

            for (String order : orders) {
                char[] chars = order.toCharArray();
                Arrays.sort(chars);
                order = new String(chars);
                selectFood(number, order, map);
            }

            if (map.size() != 0) {
                int max = Collections.max(map.values());
                if (max > 1) {
                    map.forEach((k, v) -> {
                        if (max == v) {
                            answerList.add(k);
                        }
                    });
                }
            }
        }

        answer = answerList.stream().sorted().toArray(String[]::new);
        return answer;
    }

    private void selectFood(int number, String order, Map<String, Integer> map) {
        if (order.length() < number) return;

        boolean[] isSelected = new boolean[order.length()];

        for (int i = 0; i < order.length(); i++) {
            if (!isSelected[i]){
                isSelected[i] = true;
                dfs(1, i + 1, number, order, map, isSelected);
                 isSelected[i] = false;
            }
        }
    }

    private void dfs(int i, int index, int number, String order, Map<String, Integer> map, boolean[] isSelected) {
        if (i == number){
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < order.length(); j++) {
                if (isSelected[j]) sb.append(order.charAt(j));
            }

            map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
            return;
        }

        for (int j = index; j < order.length(); j++) {
            if (!isSelected[j]){
                isSelected[j] = true;
                dfs(i+1, j + 1, number, order, map, isSelected);
                isSelected[j] = false;
            }
        }
    }

    @Test
    public void test() {
        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course = {2, 3, 4};

        Assert.assertEquals(Arrays.toString(new String[]{"AC", "ACDE", "BCFG", "CDE"}), Arrays.toString(new Solution().solution(orders, course)));
    }

    @Test
    public void test2() {
        String[] orders = {"XYZ", "XWY", "WXA"};
        int[] course = {2,3,4};

        Assert.assertEquals(Arrays.toString(new String[]{"WX", "XY"}), Arrays.toString(new Solution().solution(orders, course)));
    }
}
