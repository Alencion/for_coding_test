package sovled.class5.p1647;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 도시 분할 계획
 * 2021-01-05
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);

        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            split = br.readLine().split(" ");

            edges.add(new Edge(Integer.parseInt(split[0]) - 1,
                    Integer.parseInt(split[1]) - 1,
                    Integer.parseInt(split[2])));
        }

        int[] cycleTable = new int[n];
        for (int i = 0; i < n; i++) {
            cycleTable[i] = i;
        }

        Collections.sort(edges);
        int sum = 0;
        int lastCost = 0;
        for (Edge edge : edges) {
            int a = getParent(cycleTable, edge.from);
            int b = getParent(cycleTable, edge.to);

            if (a != b){
                sum += edge.cost;
                unionParent(cycleTable, edge.from, edge.to);
                lastCost = edge.cost;
            }
        }

        System.out.println(sum - lastCost);
    }

    private static void unionParent(int[] cycleTable, int from, int to) {
        int a = getParent(cycleTable, from);
        int b = getParent(cycleTable, to);
        if (a > b) cycleTable[a] = b;
        else cycleTable[b] = a;
    }

    private static int getParent(int[] cycleTable, int to) {
        if (cycleTable[to] != to) return getParent(cycleTable, cycleTable[to]);
        return to;
    }

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(cost, o.cost);
        }
    }
}
