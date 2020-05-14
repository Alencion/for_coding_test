package kakao2020intern.demo1;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int[] solution(int[][] v) {
        int[] answer = new int[2];

        Map<Integer, Integer> xMap = new HashMap<>();
        Map<Integer, Integer> yMap = new HashMap<>();
        for (int[] point : v) {
            xMap.put(point[0], xMap.getOrDefault(point[0], 0) + 1);
            yMap.put(point[1], yMap.getOrDefault(point[1], 0) + 1);
        }

        for (int i = 0; i < v.length; i++) {
            if (xMap.get(v[i][0]) == 1) answer[0] = v[i][0];
            if (yMap.get(v[i][1]) == 1) answer[1] = v[i][1];
        }
        return answer;
    }

    public static void main(String[] args) {
        int[][] v = {{1, 1}, {2, 2}, {1, 2}};
        new Solution().solution(v);
    }
}
