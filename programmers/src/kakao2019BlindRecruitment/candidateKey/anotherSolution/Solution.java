package kakao2019BlindRecruitment.candidateKey.anotherSolution;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int solution(String[][] relation) {
        int answer = 0;

        int size = 1 << relation[0].length;

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < size; i++) {
            if (isCandidateKey(i, relation)) {
                push(set, i);
            }
        }


        return set.size();
    }

    private void push(Set<Integer> set, int i) {
        for(int candidateKey: set){
            if ((candidateKey & i) == candidateKey) return ;
        }
        set.add(i);
    }

    private boolean isCandidateKey(int i, String[][] relation) {
        Set<String> set = new HashSet<>();

        boolean[] isChecked = new boolean[relation[0].length];

        for (int j = relation[0].length - 1; j >= 0; j--) {
            if (i == 0) break;
            int rest = i % 2;
            if (rest == 1)
                isChecked[j] = true;
            i /= 2;
        }

        StringBuilder sb = new StringBuilder();
        for (String[] columns : relation) {
            sb.setLength(0);
            for (int j = 0; j < columns.length; j++) {
                if (isChecked[j]){
                    sb.append(columns[j]);
                    sb.append("/");
                }
            }
            set.add(sb.toString());
        }

        return set.size() == relation.length;
    }

    @Test
    public void test() {
        String[][] relation = {{"100", "ryan", "music", "2"},
                {"200", "apeach", "math", "2"},
                {"300", "tube", "computer", "3"},
                {"400", "con", "computer", "4"},
                {"500", "muzi", "music", "3"},
                {"600", "apeach", "music", "2"}};

        Assert.assertEquals(2, new Solution().solution(relation));
    }
}
