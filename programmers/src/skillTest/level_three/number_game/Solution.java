package skillTest.level_three.number_game;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        List<Integer> aList = Arrays.stream(A).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        List<Integer> bList = Arrays.stream(B).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        int bIndex = 0, aIndex= 0;
        while(aIndex < aList.size() && bIndex < bList.size()){
            if (bList.get(bIndex) > aList.get(aIndex)){
                answer++;
                bIndex++;
            }
            aIndex++;
        }

        return answer;
    }

    @Test
    public void test1() {
        int[] A = {1, 2, 3, 4};
        int[] B = {5, 4, 3, 2};
        Assert.assertEquals(4, new Solution().solution(A, B));
    }

    @Test
    public void test2() {
        int[] A = {2, 2, 2, 2};
        int[] B = {1, 1, 1, 1};
        Assert.assertEquals(0, new Solution().solution(A, B));
    }

    @Test
    public void test3() {
        int[] A = {5, 1, 3, 7};
        int[] B = {2, 2, 6, 8};
        Assert.assertEquals(3, new Solution().solution(A, B));
    }

}