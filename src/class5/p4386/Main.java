package class5.p4386;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 별자리 만들기
 * 2021-01-22
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int starsCount = Integer.parseInt(br.readLine());

        List<Star> stars = new ArrayList<>();
        for (int i = 0; i < starsCount; i++) {
            String[] split = br.readLine().split(" ");

            stars.add(new Star(i, Double.parseDouble(split[0]), Double.parseDouble(split[1])));
        }

        List<Edge> edges = new ArrayList<>();
        for (Star from : stars) {
            for (Star to : stars) {
                edges.add(new Edge(from.index, to.index, from.calcCost(to)));
            }
        }

        Collections.sort(edges);

        int[] cycleTable = new int[starsCount];

        for (int i = 0; i < starsCount; i++) {
            cycleTable[i] = i;
        }

        double answer = 0.0;
        for (Edge edge : edges) {
            int fromParent = getParent(cycleTable, edge.from);
            int toParent = getParent(cycleTable, edge.to);

            if (fromParent != toParent){
                union(cycleTable, fromParent, toParent);
                answer += edge.cost;
            }
        }

        System.out.printf("%.2f", answer);
    }

    private static void union(int[] cycleTable, int from, int to) {
        int fromParent = getParent(cycleTable, from);
        int toParent = getParent(cycleTable, to);
        if (fromParent > toParent) cycleTable[fromParent] = toParent;
        else cycleTable[toParent] = fromParent;
    }

    private static int getParent(int[] cycleTable, int from) {
        if (cycleTable[from] == from) return from;
        return getParent(cycleTable, cycleTable[from]);
    }

    static class Star {
        int index;
        double x;
        double y;

        public Star(int index, double x, double y) {
            this.index = index;
            this.x = x;
            this.y = y;
        }

        public double calcCost(Star other) {
            return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
        }
    }

    static class Edge implements Comparable<Edge>{
        int from;
        int to;
        double cost;

        public Edge(int from, int to, double cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.cost, o.cost);
        }
    }
}
