package m9.p2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Solution {
    // 하 우 상좌
    int[] dx = {0, 1, -1};
    int[] dy = {1, 0, -1};

    public int[] solution(int n) {
        int[] answer = {};

        int[][] arr = new int[n][n];

        fillNumber(arr, n);

        answer = Arrays.stream(arr)
                .flatMapToInt(subArr -> Arrays.stream(subArr).filter(value -> value != 0))
                .toArray();

        return answer;
    }

    private void fillNumber(int[][] arr, int n) {
        int maxIndex = 0;
        int number = 2;
        int dir = 0;

        arr[0][0] = 1;

        int y = 0;
        int x = 0;
        for (int i = 0; i < n; i++) {
            maxIndex += n-i;
            for (; number <= maxIndex; number++) {
                y += dy[dir];
                x += dx[dir];
                arr[y][x] = number;
            }
            dir = (dir + 1) % 3;
        }
    }

    @Test
    public void test(){
        solution(4);
    }
}
