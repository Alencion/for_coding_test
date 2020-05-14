package kakao2020BlindRecruitment.stringCompression;

public class Solution {
    public int solution(String s) {
        int answer = Integer.MAX_VALUE;

        for (int unit = 1; unit <= s.length() / 2; unit++) {
            StringBuilder sb = new StringBuilder();
            String target, current;
            int count = 1;

            target = s.substring(0, unit);
            for (int i = unit; i < s.length(); i += unit) {
                if (i + unit < s.length())
                    current = s.substring(i, i + unit);
                else current = s.substring(i);
                if (target.equals(current)) {
                    count++;
                } else {
                    if (count > 1) {
                        sb.append(count);
                    }
                    sb.append(target);
                    count = 1;
                    target = current;
                }
            }

            if (count > 1) {
                sb.append(count);
            }
            sb.append(target);


            answer = Math.min(answer, sb.toString().length());
        }
        if (answer == Integer.MAX_VALUE) return 1;
        return answer;
    }

    public static void main(String[] args) {
        new Solution().solution("ababcdcdababcdcd");
    }
}
