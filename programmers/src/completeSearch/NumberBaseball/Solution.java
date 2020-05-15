package completeSearch.NumberBaseball;

public class Solution {
    public int solution(int[][] baseball) {
        int answer = 0;

        for (int i = 123; i <= 987; i++) {
            String number = String.valueOf(i);
            if (isDifferent(number)) continue;
            if (ruleCheck(baseball, number)) answer++;
        }

        return answer;
    }

    private boolean ruleCheck(int[][] baseball, String number) {
        for (int[] compareNumber : baseball) {
            String compareNumberString = String.valueOf(compareNumber[0]);
            int ballCount = 0;
            int strikeCount = 0;
            for (int i = 0; i < 3; i++) {
                char c = number.charAt(i);
                for (int j = 0; j < 3; j++) {
                    if (compareNumberString.charAt(j) == c && i == j) {
                        strikeCount++;
                    } else if (compareNumberString.charAt(j) == c && i != j){
                        ballCount++;
                    }
                }
            }
            if (strikeCount != compareNumber[1] || ballCount != compareNumber[2]) return false;
        }
        return true;
    }

    private boolean isDifferent(String number) {
        return number.charAt(0) == number.charAt(1)
                || number.charAt(0) == number.charAt(2)
                || number.charAt(1) == number.charAt(2)
                || number.charAt(1) == '0'
                || number.charAt(2) == '0';
    }

    public static void main(String[] args) {
        int[][] baseball  = {{123, 1, 1}, {356, 1, 0}, {327, 2, 0}, {489, 0, 1}};
        System.out.println(new Solution().solution(baseball));
    }
}
