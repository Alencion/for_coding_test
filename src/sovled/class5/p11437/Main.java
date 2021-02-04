package sovled.class5.p11437;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * LCA
 * 2021-02-04
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        List<List<Integer>> adjList = new ArrayList();
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            String[] split = br.readLine().split(" ");
            int from = Integer.parseInt(split[0]);
            int to = Integer.parseInt(split[1]);

            adjList.get(from).add(to);
            adjList.get(to).add(from);
        }

        boolean[] visited = new boolean[n + 1];
        int[] depths = new int[n + 1];
        int[][] parents = new int[n + 1][20];

        dfs(1, 0, depths, parents, adjList, visited);
        setParent(n, parents);

        int m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            String[] split = br.readLine().split(" ");
            int from = Integer.parseInt(split[0]);
            int to = Integer.parseInt(split[1]);

            sb.append(LCA(from, to, depths, parents)).append(System.lineSeparator());
        }
        System.out.println(sb.toString());
    }

    private static int LCA(int from, int to, int[] depths, int[][] parents) {
        if (depths[from] > depths[to]) {
            int temp = from;
            from = to;
            to = temp;
        }

        for (int n = 19; n >= 0; n--) {
            if (depths[to] - depths[from] >= (1 << n)) {
                to = parents[to][n];
            }
        }

        if (from == to) return from;

        for (int n = 19; n >= 0; n--) {
            if (parents[from][n] != parents[to][n]) {
                from = parents[from][n];
                to = parents[to][n];
            }
        }
        return parents[from][0];
    }

    private static void setParent(int n, int[][] parents) {
        for (int j = 1; j < 20; j++) {
            for (int i = 1; i <= n; i++) {
                parents[i][j] = parents[parents[i][j - 1]][j - 1];
            }
        }
    }

    private static void dfs(int current, int depth, int[] depths, int[][] parents, List<List<Integer>> adjList, boolean[] visited) {
        visited[current] = true;
        depths[current] = depth;

        for (int child : adjList.get(current)) {
            if (visited[child]) continue;

            parents[child][0] = current;
            dfs(child, depth + 1, depths, parents, adjList, visited);
        }
    }
}
