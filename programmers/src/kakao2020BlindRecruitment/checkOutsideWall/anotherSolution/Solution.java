package kakao2020BlindRecruitment.checkOutsideWall.anotherSolution;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public int solution(int n, int[] weak, int[] dist) {
        int answer = Integer.MAX_VALUE;

        int[][] rotatedWall = rotate(n, weak);
        List<Integer> distList = Arrays.stream(dist).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        int i= 0;
        for (int[] wall : rotatedWall) {
            int number = canCover(wall, distList);
            if (number != 0) {
                answer = Math.min(answer, number);
            }
            i++;
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    private int canCover(int[] wall, List<Integer> dist) {
        boolean[] isChecked = new boolean[wall.length];
        int count = 0;

        int index = 0;
        Arrays.sort(wall);

        for (int i = 0; index < wall.length && i < dist.size(); i++) {
            int friend = dist.get(i);
            int weak = wall[index];
            count++;
            isChecked[index++] = true;
            while (index < wall.length && weak + friend >= wall[index]) {
                isChecked[index++] = true;
            }
        }

        for (boolean bool : isChecked) {
            if (!bool) return 0;
        }
        return count;
    }

    private int[][] rotate(int n, int[] weak) {
        int[][] results = new int[n][weak.length];

        for (int i = 0; i < n; i++) {
            int[] result = new int[weak.length];
            for (int j = 0; j < weak.length; j++) {
                result[j] = (weak[j] + i) % n;
            }
            results[i] = result;
        }
        return results;
    }

    @Test
    public void test() {
        int n = 200;
        int[] weak = {0, 10, 50, 80, 120, 160};
        int[] dist = {1, 10, 5, 40, 30};

        Assert.assertEquals(3, new Solution().solution(n, weak, dist));
    }
}
