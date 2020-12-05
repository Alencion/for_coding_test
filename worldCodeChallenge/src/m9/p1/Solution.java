package m9.p1;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = {};

        SortedSet<Integer> sorted = new TreeSet<>();
        List<Integer> select = new ArrayList<>();
        for (int i = 0; i < numbers.length - 1; i++) {
            select.add(numbers[i]);
            combination(1, i, numbers, sorted, select);
            select.remove(select.size() - 1);
        }

        answer = sorted.stream().mapToInt(i -> i).toArray();
        return answer;
    }

    private void combination(int depth, int i, int[] numbers, SortedSet<Integer> sorted, List<Integer> select) {
        if (depth == 2) {
            int first = select.get(0);
            int second = select.get(1);

            sorted.add(first + second);
            return;
        }

        for (int j = i + 1; j < numbers.length; j++) {
            select.add(numbers[j]);
            combination(depth + 1, j, numbers, sorted, select);
            select.remove(select.size() - 1);
        }
    }
}
