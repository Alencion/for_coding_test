package sovled.class5.p1197;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int v = Integer.parseInt(split[0]);
        int e = Integer.parseInt(split[1]);

        Edge[] edges = new Edge[e];

        for (int i = 0; i < e; i++) {
            split = br.readLine().split(" ");
            edges[i] = new Edge(Integer.parseInt(split[0]) - 1, Integer.parseInt(split[1]) - 1, Integer.parseInt(split[2]));
        }

        Arrays.sort(edges);
        int[] cycleTable = new int[v];
        for (int i = 0; i < v; i++) {
            cycleTable[i] = i;
        }
        int sum = 0;

        for (Edge edge : edges) {
            if (!find(cycleTable, edge)) {
                sum += edge.cost;
                unionParent(cycleTable, edge);
            }
        }
        System.out.println(sum);
    }

    private static void unionParent(int[] cycleTable, Edge edge) {
        int a = getParent(cycleTable, edge.from);
        int b = getParent(cycleTable, edge.to);
        if (a < b) cycleTable[b] = a;
        else cycleTable[a] = b;
    }

    private static boolean find(int[] cycleTable, Edge edge) {
        int a = getParent(cycleTable, edge.from);
        int b = getParent(cycleTable, edge.to);
        return a == b;
    }

    private static int getParent(int[] cycleTable, int from) {
        if (cycleTable[from] == from) return from;
        return cycleTable[from] = getParent(cycleTable, cycleTable[from]);
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
            return Integer.compare(this.cost, o.cost);
        }
    }
}
