package kakao2020BlindRecruitment.bracket;

import java.util.Stack;

public class Solution {
    public String solution(String p) {
        String answer = "";

        answer = makeRightString(p);
        return answer;
    }

    public boolean verifyRightString(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (!stack.isEmpty() && s.charAt(i) == ')' && stack.peek() == '(') stack.pop();
            else stack.push(s.charAt(i));
        }

        return stack.isEmpty();
    }

    public String reverseString(String u) {
        u = u.substring(1, u.length()-1);
        StringBuilder reverse = new StringBuilder();

        for (int i = 0; i < u.length(); i++) {
            if (u.charAt(i) == '(') reverse.append(')');
            else reverse.append('(');
        }
        return reverse.toString();
    }

    public String makeRightString(String v){
        if (v.equals("")) return "";

        String u;
        Bracket bracket = new Bracket();

        bracket.increaseCount(v.charAt(0));

        int index;
        for (index = 1; index < v.length(); index++) {
            if (bracket.rightCount == bracket.leftCount) break;
            if (v.charAt(index) == '(') bracket.leftCount++;
            else bracket.rightCount++;
        }
        u = v.substring(0, index);
        v = v.substring(index);

        if (verifyRightString(u)) u += makeRightString(v);
        else u = '(' + makeRightString(v) + ")" + reverseString(u);

        return u;
    }

    public static void main(String[] args) {
        new Solution().solution("()))((()");
    }
}

class Bracket {
    int leftCount;
    int rightCount;

    public void increaseCount(char c) {
        if (c == '(') leftCount++;
        else rightCount++;
    }
}