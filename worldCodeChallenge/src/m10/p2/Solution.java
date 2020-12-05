package p2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Solution {
    public int[] solution(int[][] arr) {
        int[] answer = {};

        int size = arr.length;

        answer = dfs(size, 0, 0, arr);

        return answer;
    }

    private int[] dfs(int size, int y, int x, int[][] arr) {
        if (size == 1){
            if (arr[y][x] == 0) {
                return new int[]{1, 0};
            }
            if (arr[y][x] == 1) {
                return  new int[]{0, 1};
            }
        }

        int[] result = new int[2];
        int[] leftUp = dfs(size/2, y, x, arr);
        int[] rightUp = dfs(size/2 ,y, x + size/2 , arr);
        int[] leftDown = dfs(size/2, y + size/2 , x , arr);
        int[] rightDown = dfs(size/2, y+ size/2, x + size/2, arr);

        result[0] = leftUp[0] + rightUp[0] + leftDown[0] + rightDown[0];
        result[1] = leftUp[1] + rightUp[1] + leftDown[1] + rightDown[1];

        if (result[0] == 4 && result[1] == 0) result[0] = 1;
        else if (result[0] == 0 && result[1] == 4) result[1] = 1;
        return result;
    }

    @Test
    public void test(){
        int[][] arr ={{1,1,0,0},{1,0,0,0},{1,0,0,1},{1,1,1,1}};

        Assertions.assertEquals(Arrays.toString(new int[]{4,9}), Arrays.toString(new Solution().solution(arr)));
    }
}
