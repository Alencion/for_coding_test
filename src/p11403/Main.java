package p11403;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public void printAdj(int[][] adj) {
        for (int[] low : adj) {
            for (int isReach : low) {
                System.out.print(isReach + " ");
            }
            System.out.println();
        }
    }

    private int[] BFS(int i, int[][] adj) {
        Queue<Integer> queue = new LinkedList<>();
        int[] visit = new int[adj.length];

        queue.add(i);

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int k = 0; k < adj[node].length; k++) {
                if (visit[k] == 0 && adj[node][k] == 1) {
                    queue.add(k);
                    visit[k] = 1;
                }
            }
        }

        return visit;
    }

    public int[][] solution(int n, int[][] adj) {
        int[][] answer = new int[n][n];

        for (int i = 0; i < n; i++)
            answer[i] = BFS(i, adj);

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] adj = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; st.hasMoreTokens(); j++) {
                adj[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Main main = new Main();
        adj = main.solution(n, adj);
        main.printAdj(adj);
    }
}
