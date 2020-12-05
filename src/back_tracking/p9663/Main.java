package back_tracking.p9663;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int answer = solution(n);
        System.out.println(answer);
    }

    private static int solution(int n) {

        int[] cols = new int[n];

        for (int i = 0; i < n; i++) {
            cols[0] = i;
            dfs(cols, 0, n);
        }

        return count;
    }

    private static void dfs(int[] cols, int row, int n) {
        if (row == n - 1){
            count++;
            return;
        }

        for (int i = 0; i < n; i++) {
            cols[row + 1] = i;
            if (isPossible(cols, row + 1)){
                dfs(cols, row + 1, n);
            }
        }
    }

    private static boolean isPossible(int[] cols, int row) {
        for (int i = 0; i < row; i++) {
            if (cols[i] == cols[row]) return false;

            if (row - i == Math.abs(cols[i] - cols[row])) return false;
        }

        return true;
    }
}
