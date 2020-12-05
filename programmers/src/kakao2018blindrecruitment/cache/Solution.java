package kakao2018blindrecruitment.cache;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        LRU<String, String> clsTemp = LRU.newInstance(cacheSize);

        for (String city : cities) {
            String sTemp = city.toUpperCase();

            if (clsTemp.containsKey(sTemp)) {
                answer++;
            } else {
                answer += 5;
            }

            clsTemp.put(sTemp, sTemp);
        }

        return answer;
    }


    @Test
    public void test() {
        int cacheSize = 3;
        String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Jeju", "NewYork", "LA"};

        Assert.assertEquals(50, new Solution().solution(cacheSize, cities));
    }
}

class LRU<K, V> extends LinkedHashMap<K, V> {
    private int size;

    private LRU(int size) {
        super(size, 0.75f, true);
        this.size = size;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > size;
    }

    public static <K, V> LRU<K, V> newInstance(int size) {
        return new LRU<>(size);
    }
}
