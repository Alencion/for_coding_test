package kakao2020intern.shopping;

import java.util.*;

public class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        Map<String, Integer> gemTypes = new HashMap<>();

        int index = 0;
        for (String gem : gems)
            if (!gemTypes.containsKey(gem))
                gemTypes.put(gem, index++);

        if (gemTypes.size() == 1) return new int[]{1,1};

        Map<String, Integer> map = new HashMap<>();
        Section section = new Section(1, gems.length);
        String find = null;
        for (int i = 0; i < gems.length; i++) {
            map.putIfAbsent(gems[i], i);
            map.put(gems[i], i);

            if (map.size() == gemTypes.size() && (find == null || find.equals(gems[i]))) {
                Section newSection = findSection(map);
                if (section.getDistance() > newSection.getDistance())
                    section = newSection;
                find = gems[section.start-1];
            }
        }
        answer[0] = section.start;
        answer[1] = section.last;
        return answer;
    }

    private Section findSection(Map<String, Integer> map) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        Set<String> strings = map.keySet();

        for (String key : strings) {
            int value = map.get(key);
            if (min > value) min = value;
            if (max < value) max = value;
        }
        return new Section(min + 1, max + 1);
    }

    public static void main(String[] args) {
        String[] gems = {"AA", "AB", "AB", "AC", "AA", "AC"};
        new Solution().solution(gems);
    }
}

class Section {
    int start;
    int last;

    public Section(int start, int last) {
        this.start = start;
        this.last = last;
    }

    public int getDistance() {
        return last - start;
    }
}