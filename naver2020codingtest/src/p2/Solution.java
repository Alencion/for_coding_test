package p2;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public int[] solution(int[][] blocks) {
        int[] answer = {};
        List<Integer> results = new ArrayList<>();
        int size = blocks.length;
        int[][] map = new int[size][size];

        map[0][0] = blocks[0][1];

        for (int i = 1; i < size; i++) {
            int[] block = blocks[i];
            map[i][block[0]] = block[1];
            fill(i, map, block);
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j <= i; j++) {
                results.add(map[i][j]);
            }
        }
        
        answer = results.stream().mapToInt(i -> i).toArray();
        return answer;
    }

    private void fill(int depth, int[][] map, int[] block) {

        for (int i = block[0]; i > 0; i--) {
            map[depth][i - 1] = map[depth - 1][i - 1] - map[depth][i];
        }

        for (int i = block[0]; i < depth; i++) {
            map[depth][i + 1] = map[depth - 1][i] - map[depth][i];
        }
    }

    @Test
    public void test() {
        int[][] blocks = new int[][]{{0, 50}, {0, 22}, {2, 10}, {1, 4}, {4, -13}};

        Assert.assertEquals(Arrays.toString(new int[]{50, 22, 28, 4, 18, 10, 0, 4, 14, -4, 1, -1, 5, 9, -13}), Arrays.toString(new Solution().solution(blocks)));
    }
}
