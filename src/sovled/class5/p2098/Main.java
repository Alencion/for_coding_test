package sovled.class5.p2098;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 외판원 순회
 * 2021-01-06
 */
public class Main {
    private static int INF = 16 * 1_000_000;
    private static int[][] adjMatrix;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        adjMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] split = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                adjMatrix[i][j] = Integer.parseInt(split[j]);
            }
        }

        dp = new int[n][(1 << n) - 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], INF);
        }

        System.out.println(tsp(n, 0, 1));
    }

    private static int tsp(int n, int node, int visit) {
        if (visit == (1 << n) - 1) {
            if (adjMatrix[node][0] == 0) {
                return INF;
            }
            return adjMatrix[node][0];
        }

        if (dp[node][visit] != INF) return dp[node][visit];

        for (int i = 0; i < n; i++) {
            int next = visit | (1 << i);
            if (adjMatrix[node][i] == 0 || (visit & (1 << i)) != 0) continue;

            dp[node][visit] = Math.min(dp[node][visit], tsp(n, i, next) + adjMatrix[node][i]);
        }
        return dp[node][visit];
    }
}
