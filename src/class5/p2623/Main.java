package class5.p2623;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 음악프로그램
 * 2021-01-19
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int singerCount = Integer.parseInt(split[0]);
        int pdCount = Integer.parseInt(split[1]);

        int[] parentCount = new int[singerCount];
        List<List<Edge>> adjList = new ArrayList<>();

        for (int i = 0; i < singerCount; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < pdCount; i++) {
            split = br.readLine().split(" ");

            int from = Integer.parseInt(split[1]) - 1;
            for (int j = 2; j < split.length; j++) {
                int to = Integer.parseInt(split[j]) - 1;
                adjList.get(from).add(new Edge(to));
                parentCount[to]++;
                from = to;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < parentCount.length; i++) {
            if (parentCount[i] == 0) {
                queue.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        int isPossible = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            sb.append(current + 1).append(System.lineSeparator());
            isPossible++;

            for (Edge edge : adjList.get(current)) {
                parentCount[edge.to]--;
                if (parentCount[edge.to] == 0) {
                    queue.add(edge.to);
                }
            }
        }

        if (isPossible == singerCount) {
            System.out.println(sb.toString());
        } else {
            System.out.println(0);
        }
    }

    static class Edge {
        int to;

        public Edge(int to) {
            this.to = to;
        }
    }
}
