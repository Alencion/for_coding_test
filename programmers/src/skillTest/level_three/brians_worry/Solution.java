package skillTest.level_three.brians_worry;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public static final String INVALID = "invalid";

    public String solution(String sentence) {
        String answer = "";
        List<String> results = new ArrayList<>();

        try {
            Parser parser = new Parser();
            parser.parsing(sentence);
            ArrayList<String> splits = parser.getSplits();

            for (String string : splits) {
                Matcher matcher = Matcher.matching(string);
                results.add(matcher.convert(string));
            }
        } catch (Exception e) {
            return INVALID;
        }
        answer = String.join(" ", results);
        return answer;
    }

    @Test
    public void test1() {
        String sentence = "HaEaLaLaObWORLDb";
        Assert.assertEquals("HELLO WORLD", new Solution().solution(sentence));
    }

    @Test
    public void test2() {
        String sentence = "SpIpGpOpNpGJqOqA";
        Assert.assertEquals("SIGONG JOA", new Solution().solution(sentence));
    }

    @Test
    public void test3() {
        String sentence = "AxAxAxAoBoBoB";
        Assert.assertEquals("invalid", new Solution().solution(sentence));
    }

    @Test
    public void test4() {
        String sentence = "aHHHabCCCb";
        Assert.assertEquals("HHH CCC", new Solution().solution(sentence));
    }

    @Test
    public void test5() {
        String sentence = "bTxTxTaTxTbkABaCDk";
        Assert.assertEquals("HHH CCC CCCC", new Solution().solution(sentence));
    }
}

class Parser {
    Set<Character> set = new HashSet<>();
    ArrayList<String> splits = new ArrayList<>();

    public void parsing(String inputString) throws Exception {
        boolean isTwo = false, parsing =false;

        char head = inputString.charAt(0);
        if (Character.isLowerCase(head)) isTwo = true;
        if (isTwo) {
            if (set.contains(head)) throw new Exception();
            set.add(head);
            for (int i = 1; i < inputString.length() - 1; i++) {
                if (head == inputString.charAt(i)) {
                    parsing = true;
                    splits.add(inputString.substring(0, i + 1));
                    parsing(inputString.substring(i + 1));
                    break;
                }
            }
        } else {
            head = inputString.charAt(1);
            if (set.contains(head)) throw new Exception();
            set.add(head);
            for (int i = 3; i < inputString.length(); i += 2) {
                if (head != inputString.charAt(i)) {
                    parsing = true;
                    splits.add(inputString.substring(0, i));
                    parsing(inputString.substring(i));
                    break;
                }
            }
        }
        if (!parsing)
            splits.add(inputString);
    }

    public ArrayList<String> getSplits() {
        return splits;
    }
}

enum Matcher {
    one() {
        @Override
        String convert(String inputString) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < inputString.length(); i += 2) {
                sb.append(inputString.charAt(i));
            }
            return sb.toString();
        }
    },
    two() {
        @Override
        String convert(String inputString) {
            return inputString.substring(1, inputString.length() - 1);
        }
    };

    abstract String convert(String inputString);

    public static Matcher matching(String inputString) throws Exception {
        char head = inputString.charAt(0);
        char second = inputString.charAt(1);
        if (head >= 'a' && head <= 'z') return two;
        if (second >= 'a' && second <= 'z') return one;
        throw new Exception();
    }
}