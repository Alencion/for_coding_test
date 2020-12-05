package kakao_code.kakao_friends_coloring_book;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    //         상 우 하 좌
    int[] dx = {0, 1, 0, -1};
    int[] dy = {-1, 0, 1, 0};

    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        int[] answer = new int[2];

        boolean[][] isChecked = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] != 0 && !isChecked[i][j]){
                    int result = bfs(i,j,picture, isChecked);
                    numberOfArea++;
                    maxSizeOfOneArea = Math.max(result, maxSizeOfOneArea);
                }
            }
        }


        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    private int bfs(int i, int j, int[][] picture, boolean[][] isChecked) {
        int value = picture[i][j];
        int result = 0;
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(i, j));
        isChecked[i][j] = true;

        while(!queue.isEmpty()){
            Point point = queue.poll();

            result++;

            for (int k = 0; k < 4; k++) {
                int newY = point.y + dy[k];
                int newX = point.x + dx[k];

                if (newY >= 0  && newX >= 0 && newY < picture.length && newX < picture[0].length && !isChecked[newY][newX] && value == picture[newY][newX]){
                    isChecked[newY][newX] = true;
                    queue.add(new Point(newY, newX));
                }
            }
        }

        return result;
    }

    private final class Point{
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    @Test
    public void test() {
        int m = 6;
        int n = 4;

        int[][] picture = {{1, 1, 1, 0},
                {1, 2, 2, 0},
                {1, 0, 0, 1},
                {0, 0, 0, 1},
                {0, 0, 0, 3},
                {0, 0, 0, 3}};

        Assert.assertEquals(Arrays.toString(new int[]{4, 5}), Arrays.toString(new Solution().solution(m, n, picture)));
    }
}
