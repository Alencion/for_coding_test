package kakao2021BlindRecruitment.rank_search;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solution {

    public int[] solution(String[] infos, String[] queries) {
        int[] answer = {};

        List[][][][] dp = new List[4][3][3][3];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        dp[i][j][k][l] = new ArrayList();
                    }
                }
            }
        }

        for (String info : infos) {
            String[] split = info.split(" ");
            int lang = Language.valueOf(split[0]).ordinal();
            int position = Position.valueOf(split[1]).ordinal();
            int career = Career.valueOf(split[2]).ordinal();
            int food = Food.valueOf(split[3]).ordinal();
            int value = Integer.parseInt(split[4]);

            dp[lang][position][career][food].add(value);
            dp[3][position][career][food].add(value);
            dp[lang][2][career][food].add(value);
            dp[lang][position][2][food].add(value);
            dp[lang][position][career][2].add(value);
            dp[3][2][career][food].add(value);
            dp[3][position][2][food].add(value);
            dp[3][position][career][2].add(value);
            dp[lang][2][2][food].add(value);
            dp[lang][2][career][2].add(value);
            dp[lang][position][2][2].add(value);
            dp[3][2][2][food].add(value);
            dp[3][2][career][2].add(value);
            dp[3][position][2][2].add(value);
            dp[lang][2][2][2].add(value);
            dp[3][2][2][2].add(value);
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        Collections.sort(dp[i][j][k][l], Comparator.comparingInt(a -> (int) a));
                    }
                }
            }
        }

        List<Integer> results = new ArrayList<>(queries.length);
         for (String query : queries) {
            String[] split = query.split(" and ");
            String[] split2 = split[3].split(" ");

            int lang = split[0].equals("-") ? 3 : Language.valueOf(split[0]).ordinal();
            int position = split[1].equals("-") ? 2 : Position.valueOf(split[1]).ordinal();
            int career = split[2].equals("-") ? 2 : Career.valueOf(split[2]).ordinal();
            int food = split2[0].equals("-") ? 2 : Food.valueOf(split2[0]).ordinal();
            int value = Integer.parseInt(split2[1]);

            results.add(lowerBound(dp[lang][position][career][food], value));
        }

        answer = results.stream().mapToInt(i -> i).toArray();
        return answer;
    }

    private int lowerBound(List list, int target) {
        int start = 0;
        int end = list.size();

        while (start < end) {
            int mid = (start + end) / 2;

            if ((int) list.get(mid) >= target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return list.size() - end;
    }

    enum Language {
        cpp, java, python;
    }

    enum Position {
        backend, frontend;
    }

    enum Career {
        junior, senior;
    }

    enum Food {
        chicken, pizza;
    }

    @Test
    public void test1() {
        String[] infos = {"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"};
        String[] queries = {"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"};
        int[] expected = {1, 1, 1, 1, 2, 4};
        Assert.assertArrayEquals(expected, solution(infos, queries));
    }
}