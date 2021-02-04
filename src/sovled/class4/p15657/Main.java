package sovled.class4.p15657;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * N과 M (8)
 * 2021-01-01
 */
public class Main {
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");

        n = Integer.parseInt(split[0]);
        m = Integer.parseInt(split[1]);

        int[] array = new int[n];
        split = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(split[i]);
        }

        Arrays.sort(array);
        int[] selected = new int[m];
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            selected[0] = i;
            dfs(1, i, selected, array, sb);
        }

        System.out.println(sb.toString());
    }

    private static void dfs(int depth, int index, int[] selected, int[] array, StringBuilder sb) {
        if (depth == m) {
            for (int i = 0; i < selected.length; i++) {
                if (selected[i] != 0)
                    sb.append(array[selected[i] - 1] + " ");
            }
            sb.append(System.lineSeparator());
            return;
        }

        for (int i = index; i <= n; i++) {
            selected[depth] = i;
            dfs(depth + 1, i, selected, array, sb);
        }
    }
}
