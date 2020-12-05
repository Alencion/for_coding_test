package dfs_bfs.targetNumber;

class Solution {
    public int solution(int[] numbers, int target) {
        int answer = 0;
        int maxIndex = (1 << numbers.length) - 1;

        for (int i = 0; i < maxIndex; i++) {
            if (check(i, numbers, target)) {
                answer++;
            }
        }

        return answer;
    }

    private boolean check(int i, int[] numbers, int target) {
        int sum = 0;
        for (int number : numbers) {
            if ((i & 1) == 1) sum -= number;
            else sum += number;
            i = i >> 1;
        }

        return target == sum;
    }


    public static void main(String[] args) {
        int[] numbers = {1,1,1,1,1};
        int target = 3;
        System.out.println(new Solution().solution(numbers, target));
    }

    public int solution2(int[] numbers, int target) {
        int answer = 0;
        answer = dfs(numbers, 0, 0, target);
        return answer;
    }
    int dfs(int[] numbers, int n, int sum, int target) {
        if(n == numbers.length) {
            if(sum == target) {
                return 1;
            }
            return 0;
        }
        return dfs(numbers, n + 1, sum + numbers[n], target)
                + dfs(numbers, n + 1, sum - numbers[n], target);
    }
}