package sovled.class3.p11724;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 연결 요소의 개수
 * 2020-12-24
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);

        boolean[][] adjMatrix = new boolean[n][n];
        for (int i = 0; i < m; i++) {
            split = br.readLine().split(" ");

            int v1 = Integer.parseInt(split[0]) - 1;
            int v2 = Integer.parseInt(split[1]) - 1;

            adjMatrix[v1][v2] = true;
            adjMatrix[v2][v1] = true;
        }

        int answer = solution(n, adjMatrix);
        System.out.println(answer);
    }

    private static int solution(int n, boolean[][] adjMatrix) {
        boolean[] visited = new boolean[n];

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]){
                dfs(i, adjMatrix, visited);
                count++;
            }
        }
        return count;
    }

    private static void dfs(int i, boolean[][] adjMatrix, boolean[] visited) {
        visited[i] = true;

        for (int j = 0; j < adjMatrix[i].length; j++) {
            if (!visited[j] && adjMatrix[i][j]){
                dfs(j, adjMatrix, visited);
            }
        }
    }
}
