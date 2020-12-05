package graph.source;


import java.util.ArrayList;
import java.util.List;

public class Graph {
    public static final int INF = Integer.MAX_VALUE;

    public static int[][] adjMatrix(){
        return new int[][]{
                {0, 5, INF, 8},
                {5, 0, 9, INF},
                {INF, 9, 0, 4},
                {8, INF, 4, 0}};
    }

    public static List<List<Edge>> adjList(){
        ArrayList<List<Edge>> lists = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            lists.add(new ArrayList<>());
        }

        lists.get(0).add(new Edge(1, 5));
        lists.get(0).add(new Edge(3, 8));

        lists.get(1).add(new Edge(0, 5));
        lists.get(1).add(new Edge(2, 9));

        lists.get(2).add(new Edge(1, 9));
        lists.get(2).add(new Edge(3, 4));

        lists.get(3).add(new Edge(0, 8));
        lists.get(3).add(new Edge(2, 4));

        return lists;
    }

    public static class Edge {
        public int to;
        public int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
