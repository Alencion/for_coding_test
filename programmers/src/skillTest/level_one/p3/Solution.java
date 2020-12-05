package skillTest.level_one.p3;

public class Solution {
    public int solution(int n) {
        int answer = 0;

        answer = String.valueOf(n).chars().map(number -> number - '0').sum();

        return answer;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(123));
    }
}