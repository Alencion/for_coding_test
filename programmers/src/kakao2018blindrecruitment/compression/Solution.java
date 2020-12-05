package kakao2018blindrecruitment.compression;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Solution {
    public int[] solution(String msg) {
        int[] answer = {};
        HashMap<String, Integer> map = new HashMap<>();

        initIndex(map);

        answer = compression(map, msg);

        return answer;
    }

    private int[] compression(HashMap<String, Integer> map, String msg) {
        List<Integer> results = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        StringBuilder next = new StringBuilder();
        int value = 27;

        for (int i = 0; i < msg.length(); i++) {
            current.append(msg.charAt(i));
            next.append(msg.charAt(i));

            for (int j = i + 1; j < msg.length(); j++) {
                next.append(msg.charAt(j));
                String nextString = next.toString();
                if (!map.containsKey(nextString)){
                    break;
                }
                current.append(msg.charAt(j));
                i++;
            }
            results.add(map.get(current.toString()));
            map.put(next.toString(), value++);
            current.setLength(0);
            next.setLength(0);
        }
        return results.stream().mapToInt(i->i).toArray();
    }

    private void initIndex(HashMap<String, Integer> map) {
        for (int i = 0; i < 26; i++) {
            char alphabet = (char) ('A' + i);
            map.put(String.valueOf(alphabet), i+1);
        }
    }

    @Test
    public void test() {
        String msg = "KAKAO";

        Assert.assertEquals(Arrays.toString(new int[]{11, 1, 27, 15}), Arrays.toString(new Solution().solution(msg)));
    }

    @Test
    public void test1(){
        String msg = "TOBEORNOTTOBEORTOBEORNOT";
        int[] aspectResult = {20, 15, 2, 5, 15, 18, 14, 15, 20, 27, 29, 31, 36, 30, 32, 34};

        Assert.assertEquals(Arrays.toString(aspectResult), Arrays.toString(new Solution().solution(msg)));
    }
}
