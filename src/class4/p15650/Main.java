package class4.p15650;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * N과 M (2)
 * 2021-01-01
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");

        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);

        int[] selected = new int[n];
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            selected[0] = i;
            dfs(1, n, m, i + 1, selected, sb);
        }

        System.out.println(sb.toString());
    }

    private static void dfs(int depth, int n, int m, int index, int[] selected, StringBuilder sb) {
        if (depth == m) {
            for (int i = 0; i < selected.length; i++) {
                if (selected[i] != 0)
                    sb.append(selected[i] + " ");
            }
            sb.append(System.lineSeparator());
            return;
        }

        for (int i = index; i <= n; i++) {
            selected[depth] = i;
            dfs(depth + 1, n, m, i + 1, selected, sb);
        }
    }
}
