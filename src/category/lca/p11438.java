package category.lca;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class p11438 {
    static int N, M;
    static int MAX = 1000001;
    static int[][] parent = new int[MAX][21];
    static int[] depths = new int[MAX];
    static boolean[] visited = new boolean[MAX];

    static ArrayList<Integer>[] adjList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token;

        N = Integer.parseInt(br.readLine());
        adjList = new ArrayList[N + 1];

        for (int i = 0; i < adjList.length; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            token = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(token.nextToken());
            int b = Integer.parseInt(token.nextToken());
            adjList[a].add(b);
            adjList[b].add(a);
        }

        M = Integer.parseInt(br.readLine());

        dfs(1, 0);
        set_parent();
        for (int i = 1; i <= M; i++) {
            token = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(token.nextToken());
            int b = Integer.parseInt(token.nextToken());

            bw.write(lca(a, b) + "\n");
        }
        bw.flush();
    }

    static void dfs(int here, int depth) {
        visited[here] = true;
        depths[here] = depth;

        for (int next : adjList[here]) {
            if (visited[next]) {
                continue;
            }

            parent[next][0] = here;
            dfs(next, depth + 1);
        }
    }

    static void set_parent() {
        for (int j = 1; j < 21; j++) {
            for (int i = 1; i <= N; i++) {
                parent[i][j] = parent[parent[i][j - 1]][j - 1];
            }
        }
    }

    static int lca(int a, int b) {
        if (depths[a] > depths[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        for (int n = 20; n >= 0; n--) {
            if (depths[b] - depths[a] >= (1 << n)) {
                b = parent[b][n];
            }
        }

        if (a == b) return a;

        for (int n = 20; n >= 0; n--) {
            if (parent[a][n] != parent[b][n]) {
                a = parent[a][n];
                b = parent[b][n];
            }
        }

        return parent[a][0];
    }
}