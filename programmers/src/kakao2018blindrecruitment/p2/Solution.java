package kakao2018blindrecruitment.p2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
    public int solution(String dartResult) {
        int answer = 0;
        List<String> results;

        results = parseDartResult(dartResult);
        answer = calculator(results);

        return answer;
    }

    public ArrayList<String> parseDartResult(String dartResult) {
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(dartResult);
        ArrayList<String> results = new ArrayList<>();

        while (matcher.find()) {
            results.add(matcher.group());
            if (dartResult.charAt(matcher.end()) == 'S'
                    || dartResult.charAt(matcher.end()) == 'D'
                    || dartResult.charAt(matcher.end()) == 'T')
                results.add(String.valueOf(dartResult.charAt(matcher.end())));
            if (dartResult.charAt(matcher.end() + 1) == '*'
                    || dartResult.charAt(matcher.end()) == '#')
                results.add(String.valueOf(dartResult.charAt(matcher.end() + 1)));
        }
        System.out.println(results);
        return results;
    }

    public int calculator(List<String> results) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < results.size(); i++) {
            switch (results.get(i)) {
                case "S":
                    break;
                case "D":
                    stack.push((int) Math.pow(Integer.parseInt(results.get(i - 1)), 2));
                    break;
                case "T":
                    stack.push((int) Math.pow(Integer.parseInt(results.get(i - 1)), 3));
                    break;
                case "*":
                    int pop = stack.pop();
                    if (!stack.isEmpty()) {
                        int pop2 = stack.pop();
                        stack.push(pop * 2);
                        stack.push(pop2 * 2);
                    }else{
                        stack.push(pop * 2);
                    }
                    break;
                case "#":
                    int pop3 = stack.pop();
                    stack.push(-pop3);
            }
        }

        return stack.stream().reduce(Integer::sum).get();
    }

    public static void main(String[] args) {
        new Solution().solution("1S2D*3T");
    }
}
