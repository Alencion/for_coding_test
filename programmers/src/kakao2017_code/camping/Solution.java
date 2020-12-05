package kakao_code.camping;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Solution {
    public int solution(int n, int[][] data) {

        // 좌표 압축
        ArrayList<Integer> xList = new ArrayList<>();
        ArrayList<Integer> yList = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            xList.add(data[i][0]);
            yList.add(data[i][1]);
        }

        ArrayList<Integer> uniqueXList = new ArrayList<>(new HashSet<>(xList));
        ArrayList<Integer> uniqueYList = new ArrayList<>(new HashSet<>(yList));

        Collections.sort(uniqueXList);
        Collections.sort(uniqueYList);

        // 구간합 배열
        int[][] S = new int[n][n];

        for (int i = 0; i < n; i++) {

            int x = uniqueXList.indexOf(data[i][0]);
            int y = uniqueYList.indexOf(data[i][1]);

            // 좌표 압축 적용
            data[i][0] = x;
            data[i][1] = y;

            // 구간합 배열 초기값
            S[x][y] = 1;
        }

        // N^2 구간합 구하기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                S[i][j] += (i - 1 >= 0 ? S[i - 1][j] : 0)
                        + (j - 1 >= 0 ? S[i][j - 1] : 0)
                        - (i - 1 >= 0 && j - 1 >= 0 ? S[i - 1][j - 1] : 0);
            }
        }

        int ans = 0;
        // N^2 모든 쐐기 조합에 대하여 검사
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                // 조건#1 검사 : 직사각형이 아닌 경우
                if (data[i][0] == data[j][0] || data[i][1] == data[j][1]) continue;

                // 조건#2 검사 : 내부에 쐐기가 존재하는 경우
                int startX, startY, endX, endY;

                startX = Math.min(data[i][0], data[j][0]);
                startY = Math.min(data[i][1], data[j][1]);
                endX = Math.max(data[i][0], data[j][0]);
                endY = Math.max(data[i][1], data[j][1]);

                int cnt;

                if (startX + 1 > endX - 1 || startY + 1 > endY - 1) {

                    cnt = 0;
                } else {

                    cnt = S[endX - 1][endY - 1] - S[endX - 1][startY] - S[startX][endY - 1] + S[startX][startY];
                }

                if (cnt == 0) ans++;
            }
        }

        return ans;
    }


    @Test
    public void test() {
        int n = 2;
        int[][] data = {{0, 0}, {1, 2}};

        Assert.assertEquals(3, new Solution().solution(n, data));
    }
}