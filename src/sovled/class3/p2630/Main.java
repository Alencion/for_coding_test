package sovled.class3.p2630;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 색종이 자르기
 * 2020-12-21
 */
public class Main {
    static int zero, one;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] paper = new int[n][n];

        String[] split;
        for (int i = 0; i < n; i++) {
            split = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                paper[i][j] = Integer.parseInt(split[j]);
            }
        }

        dfs(paper, 0, 0, n);
        System.out.println(zero);
        System.out.println(one);
    }

    private static void dfs(int[][] paper, int y, int x, int n) {
        boolean needCutting = check(paper, y, x, n);

        if (needCutting) {
            dfs(paper, y, x, n / 2);
            dfs(paper, y + n / 2, x, n / 2);
            dfs(paper, y, x + n / 2, n / 2);
            dfs(paper, y + n / 2, x + n / 2, n / 2);
        } else {
            if (paper[y][x] == 0) zero++;
            else one++;
        }
    }

    private static boolean check(int[][] paper, int y, int x, int n) {
        int value = paper[y][x];
        for (int i = y; i < y + n; i++) {
            for (int j = x; j < x + n; j++) {
                if (value != paper[i][j]) return true;
            }
        }
        return false;
    }
}
