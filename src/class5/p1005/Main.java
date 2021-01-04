package class5.p1005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * ACM Craft
 * 2020-01-03
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < t; i++) {
            String[] split = br.readLine().split(" ");
            int n = Integer.parseInt(split[0]);
            int k = Integer.parseInt(split[1]);

            int[] time = new int[n];
            List<List<Edge>> adjList = new ArrayList<>();
            split = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                time[j] = Integer.parseInt(split[j]);
                adjList.add(new ArrayList<>());
            }

            int[] edgesCounts = new int[n];
            for (int j = 0; j < k; j++) {
                split = br.readLine().split(" ");
                int from = Integer.parseInt(split[0])-1;
                int to = Integer.parseInt(split[1])-1;
                edgesCounts[to]++;
                adjList.get(from).add(new Edge(to));
            }

            int w = Integer.parseInt(br.readLine()) - 1;

            int[] dp = new int[n];

            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < n; j++) {
                if (edgesCounts[j] == 0) {
                    queue.add(j);
                    dp[j] = time[j];
                }
            }

            while(!queue.isEmpty()){
                int current = queue.poll();

                if (current == w) {
                    break;
                }

                for (Edge edge : adjList.get(current)) {
                    edgesCounts[edge.to]--;

                    dp[edge.to] = Math.max(dp[edge.to], dp[current]);
                    if (edgesCounts[edge.to] == 0){
                        dp[edge.to] += time[edge.to];
                        queue.add(edge.to);
                    }
                }
            }

            sb.append(dp[w]).append(System.lineSeparator());
        }
        System.out.println(sb.toString());
    }

    static class Edge{
        int to;

        public Edge(int to) {
            this.to = to;
        }
    }
}
