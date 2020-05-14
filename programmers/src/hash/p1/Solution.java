package hash.p1;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";

        Map<String, Integer> map = new HashMap<>();
        for (String person : participant)
            map.put(person, 0);

        for (String peaple : completion)
            map.put(peaple, map.get(peaple) + 1);
        for (String people : participant) {
            if (map.get(people) == 0) {
                answer = people;
            }
            map.put(people, map.get(people) - 1);
        }
        return answer;
    }
}