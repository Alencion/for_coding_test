package skillTest.p7;

import java.util.*;

// 캐시
// 각 도시 이름은 공백, 숫자, 특수문자 등이 없는 영문자로 구성되며, 대소문자 구분을 하지 않는다. 도시 이름은 최대 20자로 이루어져 있다.
// 대소문자 구분하지 않는다 == "upper나 lowercase로 만들어서 비교하자 ㅠㅠ"
public class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;


        for (int i = 0; i < cities.length; i++) {
            boolean hit = false;
            Set<String> set = new HashSet<>();
            for (int j = 1; i - j >= 0 && set.size() < cacheSize; j++) {
                set.add(cities[i-j].toLowerCase());
                if (cities[i].toLowerCase().equals(cities[i - j].toLowerCase())) {
                    hit = true;
                    break;
                }
            }
            if (hit) answer++;
            else answer += 5;
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(3, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork",
                "Pangyo", "Seoul", "Seoul", "Seoul", "NewYork", "LA"}));
        System.out.println(new Solution().solution(3, new String[]{"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo",
                "Seoul", "Jeju", "Pangyo", "Seoul",}));
        System.out.println(new Solution().solution(2, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork",
                "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"}));
        System.out.println(new Solution().solution(0, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork",
                "LA"}));
    }
}

