package skillTest.level_two.kakao_friends_coloring_book;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    final static int[] dx = {-1, 0, 1, 0};
    final static int[] dy = {0, -1, 0, 1};

    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        boolean[][] isCheck = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isCheck[i][j]) continue;

                if (picture[i][j] != 0) {
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, bfs(i, j, picture, isCheck, picture[i][j]));
                    numberOfArea++;
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    private int bfs(int i, int j, int[][] picture, boolean[][] isCheck, int target) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(i + "," + j);
        isCheck[i][j] = true;
        int count = 0;

        while (!queue.isEmpty()) {
            String[] split = queue.poll().split(",");
            i = Integer.parseInt(split[0]);
            j = Integer.parseInt(split[1]);
            count++;
            for (int k = 0; k < 4; k++) {
                int nextI = i + dy[k];
                int nextJ = j + dx[k];
                if (nextI >= 0 && nextI < picture.length
                        && nextJ >= 0 && nextJ < picture[0].length
                        && !isCheck[nextI][nextJ]
                        && picture[nextI][nextJ] == target) {
                    isCheck[nextI][nextJ] = true;
                    queue.offer((i + dy[k]) + "," + (j + dx[k]));
                }
            }
        }
        return count;
    }

    @Test
    public void test_one() {
        int m = 6;
        int n = 4;

//        int[][] picture = {
//                {1, 1, 1, 0},
//                {1, 2, 2, 0},
//                {1, 0, 0, 1},
//                {0, 0, 0, 1},
//                {0, 0, 0, 3},
//                {0, 0, 0, 3}};
        int[][] picture = {
                {1, 1, 1, 0},
                {1, 1, 1, 0},
                {0, 0, 0, 1},
                {0, 0, 0, 1},
                {0, 0, 0, 1},
                {0, 0, 0, 1}};

//        Assert.assertEquals(new int[]{4, 5}, new Solution().solution(m, n, picture));
//        System.out.println(Arrays.toString(new Solution().solution(m, n, picture)));
        System.out.println(Arrays.toString(new Solution().solution(m, n, picture)));
    }
}
