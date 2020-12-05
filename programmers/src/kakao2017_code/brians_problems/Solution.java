package kakao2017_code.brians_problems;

import org.junit.Assert;
import org.junit.Test;

public class Solution {
    public String solution(String sentence) {
        String answer = null;

        int size = sentence.length();
        while(sentence.length() != 0) {
            String result = sentence;
            int second = 0;
            int first = 0;

            if (Character.isLowerCase(result.charAt(0))){
                second = secondRule(result);
                if (second == -1) return "invalid";
                else if (second != 0) {
                    result = parseSecondRule(result, second);
                }
            }

            if (Character.isUpperCase(result.charAt(0))){
                first = firstRule(result);
                if (first == -1) return "invalid";
                else if (first != 0) {
                    result = parseFirstRule(result, first);
                }
            }

            if (answer == null) answer = result;
            else answer = answer.join(" ", answer, result);

            if (second > first){
                sentence = sentence.substring(second + 1);
            } else {
                sentence = sentence.substring(first);
            }

            if (size == sentence.length()) return "invalid";
            else size = sentence.length();
        }
        return answer;
    }

    private String parseFirstRule(String result, int first) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < first; i += 2) {
            sb.append(result.charAt(i));
        }
        return sb.toString();
    }

    private int firstRule(String sentence) {
        if (sentence.length() < 2) return 0;
        int i;
        char c = sentence.charAt(1);
        if (Character.isUpperCase(c)) return 0;

        for (i = 3; i < sentence.length(); i += 2) {
            if (c != sentence.charAt(i)) break;
        }
        if (Character.isLowerCase(sentence.charAt(i - 1))) return -1;
        return i;
    }

    private String parseSecondRule(String temp, int second) {
        return temp.substring(1, second);
    }

    public int secondRule(String sentence){
        int i;
        char c = sentence.charAt(0);
        for (i = 2; i < sentence.length(); i++) {
            if (c == sentence.charAt(i)){
                break;
            }
        }

        if (i+2 < sentence.length() && c == sentence.charAt(i + 2)) return -1;

        return i;
    }

    @Test
    public void test() {
        String sentence = "xAaAbAaAx";

        Assert.assertEquals("invalid", new Solution().solution(sentence));

        sentence = "SpIpGpOpNpGJqOqA";

        Assert.assertEquals("SIGONG JOA", new Solution().solution(sentence));

        sentence = "AxAxAxAoBoBoB";

        Assert.assertEquals("invalid", new Solution().solution(sentence));

    }
}
