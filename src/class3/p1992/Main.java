package class3.p1992;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 쿼드 트리
 * 2020-12-21
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] img = new int[n][n];
        for (int i = 0; i < n; i++) {
            String readLine = br.readLine();
            for (int j = 0; j < n; j++) {
                img[i][j] = readLine.charAt(j) - '0';
            }
        }

        StringBuilder sb = new StringBuilder();
        dfs(sb, img, 0, 0, n);

        System.out.println(sb.toString());
    }

    private static void dfs(StringBuilder sb, int[][] img, int startY, int startX, int n) {
        boolean check = canCompress(img, startX, startY, n);

        if (check) {
            sb.append(img[startY][startX]);
        } else {
            sb.append("(");
            dfs(sb, img, startY, startX, n / 2);
            dfs(sb, img, startY, startX + n / 2, n / 2);
            dfs(sb, img, startY + n / 2, startX, n / 2);
            dfs(sb, img, startY + n / 2, startX + n / 2, n / 2);
            sb.append(")");
        }
    }

    private static boolean canCompress(int[][] img, int startX, int startY, int n) {
        int value = img[startY][startX];
        for (int i = startY; i < startY + n; i++) {
            for (int j = startX; j < startX + n; j++) {
                if (value != img[i][j]) return false;
            }
        }
        return true;
    }
}
