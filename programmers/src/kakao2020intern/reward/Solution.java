package kakao2020intern.reward;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    List<String> expressionList;

    public long solution(String expression) {
        long answer = 0;
        expressionList = expressionParsing(expression);
        List<String> operateList = new ArrayList<>(findOperation(expressionList));
        String[] priorityOperate = new String[operateList.size()];
        boolean[] prioritySelect = new boolean[operateList.size()];

        ArrayList<Long> results = new ArrayList<>();

        bfs(0, operateList.size(), results, operateList, priorityOperate, prioritySelect);
        answer = results.stream().map(Math::abs).max(Long::compare).get();
        return answer;
    }

    private void bfs(int depth, int size, ArrayList<Long> results, List<String> operateList, String[] priorityOperate, boolean[] prioritySelect) {
        if (depth == size) {
            results.add(calculate(priorityOperate));
            return;
        }

        for (int i = 0; i < size; i++) {
            if (prioritySelect[i]) continue;
            prioritySelect[i] = true;
            priorityOperate[depth] = operateList.get(i);
            bfs(depth + 1, size, results, operateList, priorityOperate, prioritySelect);
            prioritySelect[i] = false;
        }
    }

    private Long calculate(String[] priorityOperate) {
        Stack<String> stack = new Stack<>();

        List<String> list = expressionList;
        for (int i = priorityOperate.length - 1; i >= 0; i--) {
            for (int j = 0; j < list.size(); j++) {
                String element = list.get(j);
                if (element.equals(priorityOperate[i])) {
                    Long num1 = Long.parseLong(stack.pop());
                    Long num2 = Long.parseLong(list.get(++j));
                    stack.add(rules(num1, element, num2));
                } else
                    stack.add(element);
            }
            list = new ArrayList<>(stack);
            stack.clear();
        }

        return Long.parseLong(list.get(0));
    }

    private String rules(Long num1, String element, Long num2) {
        if (element.equals("+")) return String.valueOf(num1 + num2);
        if (element.equals("-")) return String.valueOf(num1 - num2);
        return String.valueOf(num1 * num2);
    }

    private List<String> expressionParsing(String expression) {
        ArrayList<String> result = new ArrayList<>();

        String regex = "[0-9]+";
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(expression);

        if (matcher.find())
            result.add(matcher.group());
        while (matcher.find()) {
            result.add(String.valueOf(expression.charAt(matcher.start() - 1)));
            result.add(matcher.group());
        }
        return result;
    }

    private Set findOperation(List<String> list) {
        Set<String> result = new HashSet<>();

        for (String element : list) {
            if (result.size() == 3) return result;
            if (isOperation(element)) result.add(element);
        }
        return result;
    }

    private boolean isOperation(String element) {
        return element.equals("+") || element.equals("-") || element.equals("*");
    }

    public static void main(String[] args) {
        String expression = "100-200*300-500+20";
        new Solution().solution(expression);
    }
}