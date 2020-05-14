package hash.p3;

import java.util.HashMap;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        HashMap<String, Integer> map = new HashMap<>();
        for (String[] clothe : clothes) {
            String key = clothe[1];
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        for (Integer integer : map.values())
            answer *= integer + 1;

        return answer - 1;
    }
}