package kakao2018blindrecruitment.p3;

import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        List<String> str1Array = new ArrayList<>();
        List<String> str2Array = new ArrayList<>();

        for (int i = 0; i < str1.length() - 1; i++) {
            if (Character.isAlphabetic(str1.charAt(i))
                    && Character.isAlphabetic(str1.charAt(i + 1))) {
                str1Array.add(str1.charAt(i) + "" + str1.charAt(i + 1));
            }
        }
        for (int i = 0; i < str2.length() - 1; i++) {
            if (Character.isAlphabetic(str2.charAt(i))
                    && Character.isAlphabetic(str2.charAt(i + 1))) {
                str2Array.add(str2.charAt(i) + "" + str2.charAt(i + 1));
            }
        }

        HashMap<String, Integer> str1Set = new HashMap<>();
        HashMap<String, Integer> str2Set = new HashMap<>();

        for (String s : str1Array) {
            str1Set.computeIfPresent(s, (k, v) -> v + 1);
            str1Set.putIfAbsent(s, 1);
        }

        for (String s : str2Array) {
            str2Set.computeIfPresent(s, (k, v) -> v + 1);
            str2Set.putIfAbsent(s, 1);
        }

        Set<String> strings = str1Set.keySet();
        if (strings.size() == 0) return 65536;
        int intersectionCount = strings.stream().map(key -> {
            if (str1Set.get(key) != null && str2Set.get(key) != null) {
                return Math.min(str1Set.get(key), str2Set.get(key));
            }
            return 0;
        }).reduce(Integer::sum).get();


        int unionCount = str1Array.size() + str2Array.size() - intersectionCount;

        answer = (int) (intersectionCount * 1.0 / unionCount * 65536);

        return answer;
    }

    public static void main(String[] args) {

        System.out.println(new Solution().solution("FRANCE", "french"));
        System.out.println(new Solution().solution("handshake", "shake hands"));
        System.out.println(new Solution().solution("aa1+aa2", "AAAA12"));
        System.out.println(new Solution().solution("E=M*C^2", "e=m*c^2"));
    }
}