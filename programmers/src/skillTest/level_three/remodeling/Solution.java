package skillTest.level_three.remodeling;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public int solution(int n, int[] weak, int[] dist) {
        int answer = 0;

        boolean[] check = new boolean[weak.length];
        List<Integer> results = new ArrayList<>();
        List<Integer> distList = Arrays.stream(dist).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        for (int i = 0; i < n; i++) {
            int[] rotateWeak = new int[weak.length];
            for (int j = 0; j < weak.length; j++) {
                rotateWeak[j] = (weak[j] + i) % n;
            }

            dfs(0, n, rotateWeak, check, distList, results);
        }

        answer = Collections.min(results);
        return answer;
    }

    private void dfs(int depth,int n, int[] rotateWeak, boolean[] check, List<Integer> dist, List<Integer> results) {

    }

    private boolean isCheck(boolean[] check) {
        for (int i = 0; i < check.length; i++) {
            if (!check[i]) return false;
        }
        return true;
    }

    @Test
    public void test1() {
        int n = 12;
        int[] weak = {1, 5, 6, 10};
        int[] dist = {1, 2, 3, 4};

        Assert.assertEquals(2, new Solution().solution(n, weak, dist));
    }

    @Test
    public void test2() {
        int n = 12;
        int[] weak = {1, 3, 4, 9, 10};
        int[] dist = {3, 5, 7};

        Assert.assertEquals(1, new Solution().solution(n, weak, dist));
    }
}
