package sovled.class4.p15666;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * N과 M (12)
 * 2021-01-02
 */
public class Main {
    static int n, m;
    static Set<String> set;
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

        set = new HashSet<>();
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
            StringBuilder subSb = new StringBuilder();
            for (int i = 0; i < selected.length; i++) {
                if (selected[i] != 0)
                    subSb.append(array[selected[i] - 1] + " ");
            }
            if (!set.contains(subSb.toString())){
                set.add(subSb.toString());
                sb.append(subSb.toString()).append(System.lineSeparator());
            }
            return;
        }

        for (int i = index; i <= n; i++) {
            selected[depth] = i;
            dfs(depth + 1, i, selected, array, sb);
        }
    }
}
