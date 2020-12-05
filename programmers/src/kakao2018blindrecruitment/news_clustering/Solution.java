package kakao2018blindrecruitment.news_clustering;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;

        Map<String, Integer> map1 = makeMultipleSet(str1.toUpperCase());
        Map<String, Integer> map2 = makeMultipleSet(str2.toUpperCase());

        answer = calculateSimilarity(map1, map2);
        return answer;
    }

    private int calculateSimilarity(Map<String, Integer> map1, Map<String, Integer> map2) {
        int intersectionCount = 0;
        int unionCount = 0;

        Set<String> keys = map1.keySet();
        for(String key : keys){
            if (map2.containsKey(key)){
                intersectionCount += Math.min(map1.get(key), map2.get(key));
                unionCount += map1.get(key) + map2.get(key) - Math.min(map1.get(key), map2.get(key));
                map2.remove(key);
            } else {
                unionCount += map1.get(key);
            }
        }

        keys = map2.keySet();
        for(String key : keys){
            unionCount += map2.get(key);
        }

        if (unionCount == 0) return 65536;
        return (intersectionCount * 65536) / unionCount;
    }

    private Map<String, Integer> makeMultipleSet(String str1) {
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < str1.length() - 1; i++) {
            String subString = str1.substring(i, i + 2);
            if (checkAlphabet(subString)) {
                map.computeIfPresent(subString, (key, value) -> value + 1);
                map.putIfAbsent(subString, 1);
            }
        }

        return map;
    }

    private boolean checkAlphabet(String subString) {
        if (subString.length() != 2) return false;
        return Character.isAlphabetic(subString.charAt(0)) && Character.isAlphabetic(subString.charAt(1));
    }

    @Test
    public void test() {
        String str1 = "FRANCE";
        String str2 = "french";

        Assert.assertEquals(16384, new Solution().solution(str1, str2));

        str1 = "handshake";
        str2 = "shake hands";

        Assert.assertEquals(65536, new Solution().solution(str1, str2));

        str1 = "aa1+aa2";
        str2 = "AAAA12";

        Assert.assertEquals(43690, new Solution().solution(str1, str2));

        str1 = "E=M*C^2";
        str2 = "e=m*c^2";

        Assert.assertEquals(65536, new Solution().solution(str1, str2));
    }
}