package contest.b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            min = Integer.MAX_VALUE;

            String[] split = br.readLine().split(" ");
            Map<String, Integer> map = new HashMap<>();
            for (int j = 0; j < n; j++) {
                map.put(split[j], map.getOrDefault(split[j], 0) + 1);
            }

            String[] types = map.keySet().toArray(String[]::new);
            int[] visited = new int[3];

            int depth = 0;
            for (int j = 0; j < types.length; j++) {
                map.put(types[j], map.get(types[j]) - 1);
                visited[depth] = j;
                dfs(depth + 1, j, map, types, visited);
            }

            if (min == Integer.MAX_VALUE) {
                sb.append("0");
            } else {
                sb.append(min);
            }
            sb.append(System.lineSeparator());
        }

        System.out.println(sb.toString());
    }

    private static void dfs(int depth, int index, Map<String, Integer> map, String[] types, int[] visited) {
        if (depth == 3) {
            min = Math.min(min, cost(types, visited));
            return;
        }

        for (int i = index; i < types.length; i++) {
            if (map.get(types[i]) > 0) {
                map.put(types[i], map.get(types[i]) - 1);
                visited[depth] = i;
                dfs(depth + 1, i, map, types, visited);
                map.put(types[i], map.get(types[i]) + 1);
            }
        }
    }

    private static int cost(String[] types, int[] visited) {
        String first = types[visited[0]];
        String second = types[visited[1]];
        String third = types[visited[2]];
        return cal(first, second) + cal(second, third) + cal(first, third);
    }

    private static int cal(String first, String third) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            if (first.charAt(i) != third.charAt(i)) count++;
        }
        return count;
    }
}
