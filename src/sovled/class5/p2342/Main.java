package sovled.class5.p2342;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Dance Dance Revolution
 * 2021-01-08
 */
public class Main {
    static int[][][] cache;
    static String[] split;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        split = br.readLine().split(" ");

        cache = new int[5][5][split.length];

        System.out.println(dp(0, 0, 0, split.length - 1));
    }

    private static int dp(int left, int right, int index, int n) {
        if (index == n) {
            return 0;
        }
        if (cache[left][right][index] != 0){
            return cache[left][right][index];
        }

        cache[left][right][index] = Integer.MAX_VALUE;
        int x = dp(Integer.parseInt(split[index]), right, index + 1, n) + calc(Integer.parseInt(split[index]), left);
        int y = dp(left, Integer.parseInt(split[index]), index + 1, n) + calc(Integer.parseInt(split[index]), right);
        cache[left][right][index] = Math.min(x,y);
        return cache[left][right][index];
    }

    private static int calc(int next, int left) {
        if (next == left) return 1;
        if (left == 0) return 2;
        if (Math.abs(next - left) == 2) return 4;
        return 3;
    }
}
