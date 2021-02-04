package category.binary_search.budget;

class Solution {

    public int solution(int[] budgets, int M) {
        int max = 0;
        for (int budget : budgets) {
            if (budget > max) {
                max = budget;
            }
        }
        return recursion(budgets, M, 0, max + 1);
    }

    private int recursion(int[] budgets, int M, int start, int end) {
        int half = (start + end) / 2;
        if (half <= start) {
            return start;
        }

        int sum = 0;
        for (int i : budgets) {
            sum += Math.min(i, half);
        }

        int result;
        if (sum < M) {
            result = recursion(budgets, M, half, end);
        } else if (sum == M) {
            result = half;
        } else {
            result = recursion(budgets, M, start, half);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] budgets = {120, 110, 140, 150};
        int M = 485;
        System.out.println(new Solution().solution(budgets, M));
    }
}