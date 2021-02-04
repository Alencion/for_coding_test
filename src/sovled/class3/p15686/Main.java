package sovled.class3.p15686;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 치킨 배달
 * 2020-12-24 백트래킹
 */
public class Main {
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);

        List<int[]> house = new ArrayList<>();
        List<int[]> chicken = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            split = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                if (split[j].equals("1")) {
                    house.add(new int[]{i, j});
                } else if (split[j].equals("2")) {
                    chicken.add(new int[]{i, j});
                }
            }
        }

        answer = Integer.MAX_VALUE;
        boolean[] select = new boolean[chicken.size()];

        for (int i = 0; i < chicken.size(); i++) {
            select[i] = true;
            dfs(1, m, i, house, chicken, select);
            select[i] = false;
        }
        System.out.println(answer);
    }

    private static void dfs(int depth, int m, int i, List<int[]> house, List<int[]> chicken, boolean[] select) {
        if (depth == m) {
            int sum = 0;

            for (int j = 0; j < house.size(); j++) {
                int[] housePos = house.get(j);
                int min = Integer.MAX_VALUE;
                for (int k = 0; k < chicken.size(); k++) {
                    if (select[k]) {
                        int[] chickenPos = chicken.get(k);
                        min = Math.min(min, Math.abs(housePos[0] - chickenPos[0]) + Math.abs(housePos[1] - chickenPos[1]));
                    }
                }
                sum += min;
            }
            answer = Math.min(sum, answer);
            return;
        }

        for (int j = i + 1; j < chicken.size(); j++) {
            select[j] = true;
            dfs(depth + 1, m, j, house, chicken, select);
            select[j] = false;
        }
    }
}
