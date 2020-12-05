package p2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] split = br.readLine().split(" ");
            for (int j = 0; j < split.length; j++) {
                map[i][j] = Integer.parseInt(split[j]);
            }
        }

        int answer = solution(n, map);
        System.out.println(answer);
    }

    private static int solution(int n, int[][] map) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int up = i == 0 ? 0 : map[i - 1][j];
                int left = j == 0 ? 0 : map[i][j - 1];
                int upAndLeft = (i == 0 || j == 0) ? 0 : map[i - 1][j - 1];
                map[i][j] += up + left - upAndLeft;
            }
        }

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (j + k >= n || i + k >= n) continue;
                    int up = i == 0 ? 0 : map[i - 1][j + k];
                    int left = j == 0 ? 0 : map[i + k][j - 1];
                    int upAndLeft = (i == 0 || j == 0) ? 0 : map[i - 1][j - 1];

                    int sum = map[i + k][j + k] - up - left + upAndLeft;
                    max = Math.max(max, sum);
                }
            }
        }

        return max;
    }
}
