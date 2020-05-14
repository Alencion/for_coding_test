package kakao2019intern.tuple;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
    public int[] solution(String s) {
        int[] answer = {};
        PriorityQueue<String> elements = new PriorityQueue<>(Comparator.comparingInt(String::length));

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '{') {
                StringBuilder sb = new StringBuilder();
                i++;
                while (s.charAt(i) != '}') {
                    sb.append(s.charAt(i));
                    i++;
                }
                elements.add(sb.toString());
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!elements.isEmpty()) {
            String poll = elements.poll();
            String[] split = poll.split(",");

            for (String value : split) {
                if (!result.contains(Integer.parseInt(value))) {
                    result.add(Integer.parseInt(value));
                }
            }
        }

        answer = result.stream().mapToInt(i->i).toArray();
        return answer;
    }

    public static void main(String[] args) {
        String s = "{{2},{2,1},{2,1,3},{2,1,3,4}}";

        new Solution().solution(s);
    }
}
