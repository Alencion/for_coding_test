package class4.p1865;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());

        List<String> results = new ArrayList<>();
        for (int i = 0; i < tc; i++) {
            String[] split = br.readLine().split(" ");

            int n = Integer.parseInt(split[0]);
            int m = Integer.parseInt(split[1]);
            int w = Integer.parseInt(split[2]);

            List<List<Edge>> adjList = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                adjList.add(new ArrayList<>());
            }

            for (int j = 0; j < m; j++) {
                split = br.readLine().split(" ");
                adjList.get(Integer.parseInt(split[0]) - 1)
                        .add(new Edge(Integer.parseInt(split[1]) - 1, Integer.parseInt(split[2])));
                adjList.get(Integer.parseInt(split[1]) - 1)
                        .add(new Edge(Integer.parseInt(split[0]) - 1, Integer.parseInt(split[2])));
            }

            for (int j = 0; j < w; j++) {
                split = br.readLine().split(" ");
                adjList.get(Integer.parseInt(split[0]) - 1)
                        .add(new Edge(Integer.parseInt(split[1]) - 1, Integer.parseInt(split[2]) * -1));
            }


            if (solution(n, adjList)) results.add("YES");
            else results.add("NO");
        }

        results.forEach(System.out::println);
    }

    private static boolean solution(int n, List<List<Edge>> adjList) {
        int start = 0;
        boolean updated = false;

        int[] upper = new int[n];
        Arrays.fill(upper, Integer.MAX_VALUE);
        upper[start] = 0;

        for (int i = 0; i < n; i++) {
            updated = false;
            for (int j = 0; j < adjList.size(); j++) {
                for (Edge edge : adjList.get(j)) {
                    if (upper[j] != Integer.MAX_VALUE && upper[edge.to] > upper[j] + edge.weight){
                        upper[edge.to] = upper[j] + edge.weight;
                        updated = true;
                    }
                }
            }

            if (!updated) break;
        }
        return !updated;
    }

    static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
