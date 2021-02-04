package sovled.class4.p15654;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * N과 M (5)
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
        boolean[] visited = new boolean[n];
        int[] selected = new int[m];
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            visited[i - 1] = true;
            selected[0] = i;
            dfs(1, visited, selected, array, sb);
            visited[i - 1] = false;
        }

        System.out.println(sb.toString());
    }

    private static void dfs(int depth, boolean[] visited, int[] selected, int[] array, StringBuilder sb) {
        if (depth == m) {
            for (int i = 0; i < selected.length; i++) {
                sb.append(array[selected[i] - 1] + " ");
            }
            sb.append(System.lineSeparator());
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (!visited[i - 1]) {
                visited[i - 1] = true;
                selected[depth] = i;
                dfs(depth + 1, visited, selected, array, sb);
                visited[i - 1] = false;
            }
        }
    }
}
