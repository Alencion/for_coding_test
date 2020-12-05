package p3;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;


public class Solution {
    public int[] solution(String[] infos, String[] queries) {
        int[] answer = {};

        List<Integer> answerList = new ArrayList<>();

        Set<Integer> set = new HashSet<>();

        List<Volunteer> volunteers = new ArrayList<>();
        for (int i = 0; i < infos.length; i++) {
            String[] info = infos[i].split(" ");
            set.add(Integer.parseInt(info[4]));
            volunteers.add(new Volunteer(info[0], info[1], info[2], info[3], Integer.parseInt(info[4])));
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>(set);
        HashMap<Integer, Integer> map = new HashMap<>();
        int index =0;
        while (!queue.isEmpty()) {
            map.put(queue.poll(), index);
            index++;
        }
        int[][][][][] dp = new int[4][3][3][3][set.size()];
        for (Volunteer volunteer : volunteers) {
            setDP(volunteer, dp, map);
        }

        for (String query : queries) {
            String[] conditions = query.split(" ");
            int languageIndex = convertLanguage(conditions[0]);
            int jobFamilyIndex = convertJobFamily(conditions[2]);
            int careerIndex = convertCareer(conditions[4]);
            int soulFoodIndex = convertSoulFood(conditions[6]);
            int value = Integer.parseInt(conditions[7]);
            int temp = Integer.MAX_VALUE;
            for (int i : map.keySet()) {
                if (value <= i) temp = Math.min(temp, i);
            }
            int score = map.get(temp);
            if (temp == Integer.MAX_VALUE) answerList.add(0);
            else
                answerList.add(dp[languageIndex][jobFamilyIndex][careerIndex][soulFoodIndex][score]);
        }

        answer = answerList.stream().mapToInt(i -> i).toArray();

        return answer;
    }

    private void setDP(Volunteer volunteer, int[][][][][] dp, Map<Integer, Integer> map) {
        int languageIndex = convertLanguage(volunteer.language);
        int jobFamilyIndex = convertJobFamily(volunteer.jobFamily);
        int careerIndex = convertCareer(volunteer.career);
        int soulFoodIndex = convertSoulFood(volunteer.soulFood);
        int scoreIndex = map.get(volunteer.score);

        for (int i = 0; i < (1 << 4); i++) {
            setDP(i, languageIndex, jobFamilyIndex, careerIndex, soulFoodIndex, dp, scoreIndex);
        }
    }

    private void setDP(int i, int languageIndex, int jobFamilyIndex, int careerIndex, int soulFoodIndex, int[][][][][] dp, int scoreIndex) {
        if (i / 8 == 0) languageIndex = 0;
        i %= 8;
        if (i / 4 == 0) jobFamilyIndex = 0;
        i %= 4;
        if (i / 2 == 0) careerIndex = 0;
        i %= 2;
        if (i % 2 == 0) soulFoodIndex = 0;

        for (int j = 0; j <= scoreIndex; j++) {
            dp[languageIndex][jobFamilyIndex][careerIndex][soulFoodIndex][j]++;
        }
    }

    private int convertSoulFood(String soulFood) {
        if (soulFood.equals("chicken")) return 1;
        if (soulFood.equals("pizza")) return 2;
        return 0;
    }

    private int convertCareer(String career) {
        if (career.equals("junior")) return 1;
        if (career.equals("senior")) return 2;
        return 0;
    }

    private int convertJobFamily(String jobFamily) {
        if (jobFamily.equals("backend")) return 1;
        if (jobFamily.equals("frontend")) return 2;
        return 0;
    }

    private int convertLanguage(String language) {
        if (language.equals("cpp")) return 1;
        if (language.equals("java")) return 2;
        if (language.equals("python")) return 3;
        return 0;
    }


    private static class Volunteer {
        String language;
        String jobFamily;
        String career;
        String soulFood;
        int score;

        public Volunteer(String language, String jobFamily, String career, String soulFood, int score) {
            this.language = language;
            this.jobFamily = jobFamily;
            this.career = career;
            this.soulFood = soulFood;
            this.score = score;
        }
    }

    @Test
    public void test() {
        String[] infos = {"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"};
        String[] queries = {"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"};

        Assert.assertEquals(Arrays.toString(new int[]{1, 1, 1, 1, 2, 4}), Arrays.toString(new Solution().solution(infos, queries)));
    }
}
