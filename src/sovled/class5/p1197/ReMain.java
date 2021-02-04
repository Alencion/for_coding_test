package sovled.class5.p1197;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReMain {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int v = Integer.parseInt(split[0]);
        int e = Integer.parseInt(split[1]);

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < e; i++) {
            split = br.readLine().split(" ");
            edges.add(new Edge(Integer.parseInt(split[0]) - 1, Integer.parseInt(split[1]) - 1, Integer.parseInt(split[2])));
        }

        Collections.sort(edges);

        int[] cycleTable = new int[v];
        for (int i = 0; i < v; i++) {
            cycleTable[i] = i;
        }

        int sum = 0;
        for (Edge edge : edges) {
            int a = getParent(cycleTable, edge.from);
            int b = getParent(cycleTable, edge.to);

            if (a != b) {
                unionParent(cycleTable, edge.from, edge.to);
                sum += edge.weight;
            }
        }

        System.out.println(sum);
    }

    private static void unionParent(int[] cycleTable, int from, int to) {
        int a = getParent(cycleTable, from);
        int b = getParent(cycleTable, to);

        if (a > b) cycleTable[a] = b;
        else cycleTable[b] = a;
    }

    private static int getParent(int[] cycleTable, int from) {
        if (from != cycleTable[from]) return cycleTable[from] = getParent(cycleTable, cycleTable[from]);
        return from;
    }

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(weight, o.weight);
        }
    }
}
