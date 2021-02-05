package kakao2021BlindRecruitment.menu_renewal;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class Solution {
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};

        List<String> answerList = new ArrayList<>();

        for (int courseCount : course) {
            Map<String, Integer> map = new HashMap<>();

            for (String order : orders) {
                char[] chars = order.toCharArray();
                Arrays.sort(chars);

                order = new String(chars);

                if (order.length() < courseCount) continue;

                boolean[] isSelected = new boolean[order.length()];

                for (int i = 0; i < order.length(); i++) {
                    if (!isSelected[i]){
                        isSelected[i] = true;
                        dfs(1, i + 1, courseCount, order, map, isSelected);
                        isSelected[i] = false;
                    }
                }
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

    private void dfs(int i, int index, int courseCount, String order, Map<String, Integer> map, boolean[] isSelected) {
        if (i == courseCount){
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
                dfs(i+1, j + 1, courseCount, order, map, isSelected);
                isSelected[j] = false;
            }
        }
    }


    @Test
    public void test1() {
        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course = {2, 3, 4};
        String[] expected = {"AC", "ACDE", "BCFG", "CDE"};

        Assert.assertArrayEquals(expected, solution(orders, course));
    }
}