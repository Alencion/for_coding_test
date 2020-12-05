package m10.p4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Solution {
    public long solution(String s) {
        long answer = 0;

        long[] dp = new long[s.length()];

        dp[0] = 0;
        Map<Character, Integer> map = new HashMap<>();

        char prev = s.charAt(0);

        int count = 1;
        for (int i = 1; i < s.length(); i++) {
            dp[i] = dp[i - 1] + i;
            answer += dp[i];

            if (prev == s.charAt(i)) {
                count++;
            } else {
                map.put(prev, map.getOrDefault(prev, 0) + count);
                count = 1;
                prev = s.charAt(i);
            }

            answer -= dp[map.getOrDefault(s.charAt(i), 0) + count - 1];
            if (map.getOrDefault(s.charAt(i), 0) > 0) {
                answer += dp[map.getOrDefault(s.charAt(i), 0) - 1];
            }
        }

        return answer;
    }

    @Test
    public void test() {
        String s = "baby";

        Assertions.assertEquals(new CopySolution().solution(s), new Solution().solution(s));
    }

    @Test
    public void test2() {
        String s = "aoooo";

        Assertions.assertEquals(new CopySolution().solution(s), new Solution().solution(s));
    }

    @Test
    public void test3() {
        String s = "bbabbab";

        Assertions.assertEquals(new CopySolution().solution(s), new Solution().solution(s));
    }

    @Test
    public void test4() {
        String s = "ccbbaabbaacc";

        Assertions.assertEquals(new CopySolution().solution(s), new Solution().solution(s));
    }

    @Test
    public void test5() {
        String s = "cccabc";

        Assertions.assertEquals(new CopySolution().solution(s), new Solution().solution(s));
    }

    @Test
    public void test6() {
        String s = "abaacc";

        Assertions.assertEquals(new CopySolution().solution(s), new Solution().solution(s));
    }
}


class CopySolution {
    public long solution(String s) {
//        ArrayList<Integer>[] charIndex = new ArrayList[26];
//        for (int i = 0; i < 26; i++) {
//            charIndex[i] = new ArrayList<Integer>();
//        }
//        ArrayList<Integer>[] charBlkSize = new ArrayList[26];
//        for (int i = 0; i < 26; i++) {
//            charBlkSize[i] = new ArrayList<Integer>();
//        }
        HashMap<Integer, Integer>[] sumBlkSize = new HashMap[26];
        for (int i = 0; i < 26; i++) {
            sumBlkSize[i] = new HashMap<>();
        }

        ArrayList<Integer> charIndexAll = new ArrayList<>();
        final int S_LENGTH = s.length();
        long answer = 0;

        // 문자 길이가 2 미만
        if (S_LENGTH < 2) {
            return 0L;
        }

        {
            boolean bOnlyOneChar = true;
            int blkSize = 1;
            char chBefore = s.charAt(0);
            // 가장 첫 문자부터 저장
            charIndexAll.add(0);
//            charIndex[chBefore - 'a'].add(0);

            for (int i = 1; i < S_LENGTH; i++) {
                // 다른 문자가 나타나면 해당 사항을 저장
                char chCurrent = s.charAt(i);
                if (chCurrent != chBefore) {
                    charIndexAll.add(i);
//                    charIndex[chCurrent - 'a'].add(i);
//                    charBlkSize[chBefore - 'a'].add(blkSize);
                    if (sumBlkSize[chBefore - 'a'].containsKey(blkSize)) {
                        sumBlkSize[chBefore - 'a'].replace(blkSize, sumBlkSize[chBefore - 'a'].get(blkSize) + 1);
                    } else {
                        sumBlkSize[chBefore - 'a'].put(blkSize, 1);
                    }
                    blkSize = 0;
                    chBefore = chCurrent;
                    bOnlyOneChar = false;
                }
                blkSize++;
            }
//            charBlkSize[chBefore - 'a'].add(blkSize);
            if (sumBlkSize[chBefore - 'a'].containsKey(blkSize)) {
                sumBlkSize[chBefore - 'a'].replace(blkSize, sumBlkSize[chBefore - 'a'].get(blkSize) + 1);
            } else {
                sumBlkSize[chBefore - 'a'].put(blkSize, 1);
            }

            // 모든 문자가 동일한 경우 바로 0 반환
            if (bOnlyOneChar) {
                return 0L;
            }

            // 끝을 표시하는 값을 추가로 저장
            charIndexAll.add(S_LENGTH);
//            for (int i = 0; i < 26; i++) {
//                charIndex[i].add(S_LENGTH);
//            }
        }

        for (int i = 0; i < charIndexAll.size() - 1; i++) {
            int currentIndex = charIndexAll.get(i);
            int endIndex = charIndexAll.get(i + 1);
            int currentBlkSize = endIndex - currentIndex;
            char chCurrentBlk = s.charAt(currentIndex);

            {
                int sumOfcurrentBlkSize = sumBlkSize[chCurrentBlk - 'a'].get(currentBlkSize) - 1;
                if (sumOfcurrentBlkSize == 0) {
                    sumBlkSize[chCurrentBlk - 'a'].remove(currentBlkSize);
                } else {
                    sumBlkSize[chCurrentBlk - 'a'].replace(currentBlkSize, sumOfcurrentBlkSize);
                }
            }

            for (int j = currentIndex; j < endIndex; j++) {
                int frontBlkSize = endIndex - j;
                answer += (long)(S_LENGTH - j) * (S_LENGTH - j - 1) / 2;
                answer -= (long)frontBlkSize * (frontBlkSize - 1) / 2;

                Set<Integer> sumOfBlkSize = sumBlkSize[chCurrentBlk - 'a'].keySet();
                for (Integer blkSize : sumOfBlkSize) {
                    if (blkSize <= frontBlkSize) {
                        answer -= blkSize * (blkSize + 1) / 2 * sumBlkSize[chCurrentBlk - 'a'].get(blkSize);
                    } else {
                        answer -= (frontBlkSize * (frontBlkSize + 1) / 2 + frontBlkSize * (blkSize - frontBlkSize)) * sumBlkSize[chCurrentBlk - 'a'].get(blkSize);
                    }
                }
            } // for j
        } // for i

        return answer;
    }
}
