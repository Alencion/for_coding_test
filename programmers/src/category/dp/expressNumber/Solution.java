package category.dp.expressNumber;

import java.util.ArrayList;
import java.util.HashSet;

class Solution {
    public int solution(int n, int num) {
        int answer = 0;

        HashSet<Integer> set = new HashSet<>();
        set.add(n);

        ArrayList<HashSet<Integer>> list = new ArrayList<>();
        list.add(set);

        while (answer < 8) {
            if (list.get(answer).contains(num)) break;
            answer++;

            set = new HashSet<>();
            set.add(Integer.parseInt(String.valueOf(n).repeat(Math.max(0, answer + 1))));

            for (int i = 0; i <= answer / 2; i++) {
                for (int j = 0; i + j < answer; j++) {
                    for (int integer : list.get(i)) {
                        for (int value : list.get(j)) {
                            set.add(integer + value);
                            set.add(integer - value);
                            set.add(integer * value);
                            if (integer != 0) set.add(value/integer);
                        }
                    }
                }
            }
            list.add(set);
        }

        return (answer >= 8) ? -1 : answer + 1;
    }

    public static void main(String[] args) {
        int N = 5;
        int number = 12;
        System.out.println(new Solution().solution(N, number));
    }
}