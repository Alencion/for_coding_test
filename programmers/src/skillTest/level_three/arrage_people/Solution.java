package skillTest.level_three.arrage_people;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution1 {
    public int[] solution(int n, long k) {
        int[] answer = {};

        List<List<Integer>> results = new ArrayList<>();
        List<Integer> result = new ArrayList();

        boolean[] selected = new boolean[n];
        dfs(n, k, selected, result, results);

        result = results.get(results.size() - 1);
        answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }

    private void dfs(int n, long k, boolean[] selected, List<Integer> result, List<List<Integer>> results) {
        if (k - results.size() == 0) return;
        if (checkSelected(selected)) {
            results.add(new ArrayList<>(result));
        }

        for (int i = 0; i < n; i++) {
            if (!selected[i]) {
                result.add(i + 1);
                selected[i] = true;
                dfs(n, k, selected, result, results);
                selected[i] = false;
                result.remove(result.size() - 1);
            }
        }
    }

    private boolean checkSelected(boolean[] selected) {
        for (boolean select : selected) {
            if (!select) return false;
        }
        return true;
    }

    @Test
    public void test1() {
        int n = 3;
        int k = 5;
        Assert.assertEquals(Arrays.toString(new int[]{3, 1, 2}), Arrays.toString(new Solution().solution(n, k)));
    }
}

public class Solution {
    public int[] solution(int n, long k) {
        if (n ==1 && k ==1 )return new int[]{1};

        int[] answer = new int[n];
        List<Integer> result = new ArrayList<>();
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            integers.add(i+1);
        }

        List<Long> list = new ArrayList<>();
        list.add((long) 1);
        for (int i = 2; i < n; i++) {
            list.add(list.get(i-2) * i);
        }

        for (int i = list.size() - 1; i >= 0 ; i--) {
            long number = list.get(i);
            int divide = (int) ((k - 1) / number);
            result.add(integers.remove(divide));
            k -= divide * number;
        }
        result.add(integers.remove(0));

        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }

    @Test
    public void test1() {
        int n = 3;
        int k = 1;
        Assert.assertEquals(Arrays.toString(new int[]{3, 1, 2}), Arrays.toString(new Solution().solution(n, k)));
    }
}