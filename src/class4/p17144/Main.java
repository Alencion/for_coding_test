package class4.p17144;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 미세먼지 안녕!
 * 2020-01-02
 */
public class Main {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int r = Integer.parseInt(split[0]);
        int c = Integer.parseInt(split[1]);
        int t = Integer.parseInt(split[2]);

        int[][] map = new int[r][c];

        int[] vacuum = new int[2];
        int index = 0;
        for (int i = 0; i < r; i++) {
            split = br.readLine().split(" ");
            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(split[j]);
                if (map[i][j] == -1) vacuum[index++] = i;
            }
        }

        for (int i = 0; i < t; i++) {
            int[][] newMap = spread(r, c, map);
            newMap[vacuum[0]][0] = -1;
            newMap[vacuum[1]][0] = -1;
            rolling(r, c, vacuum, newMap);
            map = newMap;
        }

        int count = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] < 1) continue;
                count += map[i][j];
            }
        }
        System.out.println(count);
    }

    private static void rolling(int r, int c, int[] vacuum, int[][] map) {
        int[][] newMap = new int[r][c];

        for (int i = 0; i < r; i++) {
            Arrays.fill(newMap[i], -1);
        }

        for (int i = 1; i < c; i++) {
            newMap[0][i - 1] = map[0][i];
            newMap[r - 1][i - 1] = map[r - 1][i];
            if (i < c - 1) {
                newMap[vacuum[0]][i + 1] = map[vacuum[0]][i];
                newMap[vacuum[1]][i + 1] = map[vacuum[1]][i];
            }
        }

        for (int i = 1; i <= vacuum[0]; i++) {
            newMap[i - 1][c - 1] = map[i][c - 1];
            newMap[i][0] = map[i - 1][0];
        }

        for (int i = vacuum[1] + 1; i < r; i++) {
            newMap[i - 1][0] = map[i][0];
            newMap[i][c - 1] = map[i - 1][c - 1];
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (newMap[i][j] == -1) continue;
                map[i][j] = newMap[i][j];
            }
        }
        map[vacuum[0]][1] = 0;
        map[vacuum[1]][1] = 0;
        map[vacuum[0]][0] = -1;
        map[vacuum[1]][0] = -1;
    }

    private static int[][] spread(int r, int c, int[][] map) {
        int[][] newMap = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] >= 5) {
                    int subDust = map[i][j] / 5;
                    for (int k = 0; k < 4; k++) {
                        int newY = i + dy[k];
                        int newX = j + dx[k];

                        if (newY < 0 || newX < 0 || newY > r - 1 || newX > c - 1) continue;
                        if (map[newY][newX] == -1) continue;
                        newMap[newY][newX] += subDust;
                        map[i][j] -= subDust;
                    }
                }
                newMap[i][j] += map[i][j];
            }
        }

        return newMap;
    }
}

