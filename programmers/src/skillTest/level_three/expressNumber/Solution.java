package skillTest.level_three.expressNumber;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public int solution(int N, int number) {
        List<Set<Integer>> list = new ArrayList<>();

        HashSet<Integer> set = new HashSet<>();
        set.add(N);
        list.add(set);

        for (int i = 1; i < 8; i++) {
            Set<Integer> results = makePossibleNumber(i, N, list);
            list.add(results);
            if (results.contains(number)) {
                return i + 1;
            }
        }

        return -1;
    }

    private Set<Integer> makePossibleNumber(int i, int N, List<Set<Integer>> list) {
        int index = 0;
        int size = i - 1;

        HashSet<Integer> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(N).repeat(i + 1));
        set.add(Integer.parseInt(sb.toString()));

        while(index <= size){
            set.addAll(makePossibleNumber(index++, size--, N, list));
        }

        return set;
    }

    private  Set<Integer> makePossibleNumber(int head, int tail, int N, List<Set<Integer>> list) {
        HashSet<Integer> set = new HashSet<>();

        Set<Integer> setHead = list.get(head);
        Set<Integer> setTail = list.get(tail);

        for (int headElement: setHead){
            for (int tailElement: setTail){
                set.add(headElement + tailElement);
                set.add(headElement - tailElement);
                set.add(tailElement - headElement);
                set.add(headElement * tailElement);
                if (tailElement != 0)
                    set.add(headElement / tailElement);
                if (headElement != 0)
                    set.add(tailElement / headElement);
            }
        }

        return set;
    }

    @Test
    public void test() {
        final int N = 5;
        final int number = 12;

        Assert.assertEquals(4, new Solution().solution(N, number));
    }

    @Test
    public void test2(){
        final int N = 2;
        final int number = 11;

        Assert.assertEquals(3, new Solution().solution(N, number));
    }
}

