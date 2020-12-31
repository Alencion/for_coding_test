package contest.c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int q = Integer.parseInt(split[1]);

        boolean[][] adjMatrix = new boolean[n + 1][n + 1];

        for (int i = 0; i < n; i++) {
            split = br.readLine().split(" ");

            int v1 = Integer.parseInt(split[0]);
            int v2 = Integer.parseInt(split[1]);

            adjMatrix[v1][v2] = true;
            adjMatrix[v2][v1] = true;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            split = br.readLine().split(" ");

            int from = Integer.parseInt(split[0]);
            int to = Integer.parseInt(split[1]);
            int answer = solution(from, to, n, adjMatrix);

            sb.append(answer).append(System.lineSeparator());
        }
        System.out.println(sb.toString());
    }

    private static int solution(int from, int to, int n, boolean[][] adjMatrix) {
        int[] dp = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        dp[from] = 1;
        visited[from] = true;
        int sum = dfs(to, dp, adjMatrix, visited);
        return sum;
    }

    private static int dfs(int to, int[] dp, boolean[][] adjMatrix, boolean[] visited) {
        int sum = 0;
        for (int i = 0; i < adjMatrix[to].length; i++) {
            if (adjMatrix[to][i]) {
                if (!visited[i]) {
                    visited[i] = true;
                    dfs(i, dp, adjMatrix, visited);
                }
                sum += dp[i];
            }
        }
        dp[to] = sum;
        return dp[to];
    }
}
