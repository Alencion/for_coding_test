package p1260;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        // write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = br.readLine().split(" ");
        int vertexCount = Integer.parseInt(strings[0]);
        int edgeCount = Integer.parseInt(strings[1]);
        int startNodeIndex = Integer.parseInt(strings[2]);

        int[][] adj = new int[vertexCount][vertexCount];

        for (int i = 0; i < edgeCount; i++) {
            strings = br.readLine().split(" ");
            adj[Integer.parseInt(strings[0]) - 1][Integer.parseInt(strings[1]) - 1] = 1;
            adj[Integer.parseInt(strings[1]) - 1][Integer.parseInt(strings[0]) - 1] = 1;
        }
        boolean[] visit = new boolean[vertexCount];
        visit[startNodeIndex - 1] = true;
        System.out.print(startNodeIndex + " ");
        DFS(adj, startNodeIndex - 1, visit);
        System.out.println();
        visit = new boolean[vertexCount];
        BFS(adj, startNodeIndex - 1, visit);
    }

    private static void BFS(int[][] adj, int start, boolean[] visit) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        int element;
        while(!queue.isEmpty()){
            element = queue.poll();
            if (!visit[element])
            System.out.print((element+1)+" ");
            visit[element] = true;
            for (int i = 0; i < adj[element].length; i++) {
                if (adj[element][i] == 1 && !visit[i])
                    queue.offer(i);
            }
        }
    }

    public static void DFS(int[][] adj, int start, boolean[] visit) {
        for (int i = 0; i < adj.length; i++) {
            if (adj[start][i] == 1 && !visit[i]) {
                visit[i] = true;
                System.out.print((i + 1) + " ");
                DFS(adj, i, visit);
            }
        }
    }
}
