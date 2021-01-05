package class5.p1516;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 게임 개발
 * 2020-01-05
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        List<List<Edge>> adjLists = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjLists.add(new ArrayList<>());
        }

        int[] time = new int[n];
        int[] dp = new int[n];
        int[] edgesCount = new int[n];
        for (int i = 0; i < n; i++) {
            String[] split = br.readLine().split(" ");

            time[i] = Integer.parseInt(split[0]);
            for (int j = 1; j < split.length - 1; j++) {
                adjLists.get(Integer.parseInt(split[j]) - 1).add(new Edge(i));
                edgesCount[i]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < edgesCount.length; i++) {
            if (edgesCount[i] == 0){
                queue.add(i);
                dp[i] = time[i];
            }
        }

        while(!queue.isEmpty()){
            int current = queue.poll();

            for (Edge edge : adjLists.get(current)) {
                edgesCount[edge.to]--;
                dp[edge.to] = Math.max(dp[edge.to], dp[current]);

                if (edgesCount[edge.to] == 0){
                    queue.add(edge.to);
                    dp[edge.to] += time[edge.to];
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int value : dp) {
            sb.append(value).append(System.lineSeparator());
        }
        System.out.println(sb.toString());
    }

    static class Edge {
        int to;

        public Edge(int to) {
            this.to = to;
        }
    }
}
