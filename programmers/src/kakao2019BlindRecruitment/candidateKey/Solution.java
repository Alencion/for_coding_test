package kakao2019BlindRecruitment.candidateKey;

import java.util.ArrayList;
import java.util.HashSet;

class Solution {
    public int solution(String[][] relation) {
        int rowSize = relation.length;
        int colSize = relation[0].length;
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 1; i < (1 << colSize); i++) {
            if (!checkMinimality(i, list)) continue; // 최소성을 만족하지 못하면 패스
            if (!checkUniqueness(i, relation, rowSize, colSize)) continue; // 유일성을 만족하지 못하면 패스

            list.add(i);
        }

        return list.size();
    }

    private boolean checkMinimality(int set, ArrayList<Integer> list) {
        for (int key : list) {
            if ((set & key) == key) return false; // 중복 되어 있으면 false
        }
        return true;
    }

    private boolean checkUniqueness(int set, String[][] relation, int rowSize, int colSize) {
        ArrayList<Integer> s = getSet(set, colSize);

        HashSet<String> hashSet = new HashSet<>();
        for (int i = 0; i < rowSize; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j : s) {
                sb.append(relation[i][j]).append(" ");
            }
            hashSet.add(sb.toString());
        }
        return hashSet.size() == rowSize;
    }

    private ArrayList<Integer> getSet(int set, int colSize) { // 0101 -> [0, 2]로 변환
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < colSize; i++) {
            if (((set >> i) & 1) == 1) result.add(i);
        }
        return result;
    }

        public static void main(String[] args) {
        String[][] relation = {{"100", "ryan", "music", "2"},
                {"200", "apeach", "math", "2"},
                {"300", "tube", "computer", "3"},
                {"400", "con", "computer", "4"},
                {"500", "muzi", "music", "3"},
                {"600", "apeach", "music", "2"}};

        new Solution().solution(relation);
    }
}