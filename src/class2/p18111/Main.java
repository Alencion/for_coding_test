package class2.p18111;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");

        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);
        int b = Integer.parseInt(split[2]);

        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            split = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(split[j]);
            }
        }

        int[] answer = solution(b, map);
        System.out.println(answer[0] + " " + answer[1]);

    }

    private static int[] solution(int b, int[][] map) {
        int[] answer = new int[2];

        int result = Integer.MAX_VALUE;
        for (int i = 256; i >= 0; i--) {
            int cost = flatting(map, i, b);
            if (cost >= 0 && cost < result){
                answer[0] = cost;
                answer[1] = i;
                result = cost;
            }
        }
        return answer;
    }

    private static int flatting(int[][] map, int target, int b) {
        int cost = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] < target){
                    cost += (target - map[i][j]);
                } else if (map[i][j] > target){
                    cost += 2 * (map[i][j] - target);
                }
                b += map[i][j] - target;
            }
        }

        if (b < 0) return -1;
        return cost;
    }
}
