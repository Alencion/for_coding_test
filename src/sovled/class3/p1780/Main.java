package sovled.class3.p1780;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 종이의 개수
 * 2020-12-21 분할정복 - 재귀로 해결.
 */
public class Main {
    static int one, zero, minusOne;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] paper = new int[n][n];
        String[] split;
        for (int i = 0; i < n; i++) {
            split = br.readLine().split(" ");
            for (int j = 0; j < split.length; j++) {
                paper[i][j] = Integer.parseInt(split[j]);
            }
        }

        dfs(paper, 0, n, 0, n);

        System.out.println(minusOne);
        System.out.println(zero);
        System.out.println(one);
    }

    private static void dfs(int[][] paper, int startCol, int endCol, int startLow, int endLow) {
        int value = paper[startLow][startCol];
        boolean check = false;
        loop : for (int i = startLow; i < endLow; i++) {
            for (int j = startCol; j < endCol; j++) {
                if (value != paper[i][j]) {
                    check = true;
                    break loop;
                }
            }
        }

        if (check) {
            for (int k = 0; k < 3; k++) {
                for (int l = 0; l < 3; l++) {
                    dfs(paper, startCol + (endCol - startCol) / 3 * l, startCol + (endCol - startCol) / 3 * (l + 1)
                            , startLow + (endLow - startLow) / 3 * k, startLow + (endLow - startLow) / 3 * (k + 1));
                }
            }
        } else {
            countPaper(value);
        }
    }

    private static void countPaper(int value) {
        if (value == 0) {
            zero++;
        }
        else if (value == 1) {
            one++;
        } else {
            minusOne++;
        }
    }
}
