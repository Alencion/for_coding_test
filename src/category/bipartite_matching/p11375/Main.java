package category.bipartite_matching.p11375;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");

        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);

        List<List<Edge>> adjList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());

            split = br.readLine().split(" ");

            for (int j = 1; j < split.length; j++) {
                adjList.get(i).add(new Edge(Integer.parseInt(split[j]) - 1));
            }
        }

        int answer = solution(n,m, adjList);
        System.out.println(answer);
    }

    private static int solution(int n, int m, List<List<Edge>> adjList) {
        int answer = 0;

        int[] matching = new int[m];
        Arrays.fill(matching, -1);

        for (int i = 0; i < n; i++) {
            boolean[] visited = new boolean[m];
            if (dfs(i, visited, matching, adjList)) answer++;
        }

        return answer;
    }

    private static boolean dfs(int i, boolean[] visited, int[] dist, List<List<Edge>> adjList) {
        for (int j = 0; j < adjList.get(i).size(); j++) {
            int t = adjList.get(i).get(j).to;

            if (visited[t]) continue;
            visited[t] = true;

            if (dist[t] == -1 || dfs(dist[t], visited, dist, adjList)){
                dist[t] = i;
                return true;
            }
        }
        return false;
    }


    private static class Edge{
        int to;

        public Edge(int to) {
            this.to = to;
        }
    }
}