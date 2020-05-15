package completeSearch.findPrimeNumber;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public int solution(String numbers) {
        int answer = 0;
        Set<Integer> results = new HashSet<>();

        for (int i = 1; i < (1 << numbers.length()); i++)
            completeSearch(i, numbers, results);

        answer = (int) results.stream()
                .filter(this::isPrimeNumber).count();
        return answer;
    }

    private void completeSearch(int bitMask, String numbers, Set<Integer> results) {
        int n = getBitSize(bitMask);
        List<Character> characters = makeNumbers(bitMask, numbers);
        boolean[] visit = new boolean[n];
        findCases(0, "", characters, visit, results);
    }

    private List<Character> makeNumbers(int bitMask, String numbers) {
        List<Character> characters = new ArrayList<>();
        for (int i = 0; bitMask != 0; i++) {
            if ((bitMask & 1) == 1) characters.add(numbers.charAt(i));
            bitMask >>= 1;
        }
        return characters;
    }

    private void findCases(int depth, String result, List<Character> characters, boolean[] visit, Set<Integer> results) {
        if (depth == characters.size()) {
            results.add(Integer.parseInt(result));
            return;
        }

        for (int i = 0; i < characters.size(); i++) {
            if (visit[i]) continue;
            visit[i] = true;
            findCases(depth + 1, result + characters.get(i), characters, visit, results);
            visit[i] = false;
        }
    }

    private int getBitSize(int bitMask) {
        int count = 0;
        while (bitMask != 0) {
            if ((bitMask & 1) == 1) count++;
            bitMask >>= 1;
        }
        return count;
    }

    private boolean isPrimeNumber(int number) {
        for (int i = 2; i < number; i++)
            if (number % i == 0) return false;
        return number > 1;
    }


    public static void main(String[] args) {
        String numbers = "011";
        new Solution().solution(numbers);
    }
}
