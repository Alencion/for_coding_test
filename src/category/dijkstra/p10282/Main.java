package category.dijkstra.p10282;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);

        int[][] conditions = new int[m][2];

        for (int i = 0; i < m; i++) {
            split = br.readLine().split(" ");
            conditions[i][0] = Integer.parseInt(split[0]) - 1;
            conditions[i][1] = Integer.parseInt(split[1]) - 1;
        }

        int[] answer = solution(n, m, conditions);
        for (int value : answer) {
            System.out.print(value + " ");
        }
    }

    private static int[] solution(int n, int m, int[][] conditions) {
        List<Integer> answer = new ArrayList<>();
        int[] topology = new int[n];

        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] condition : conditions) {
            adjList.get(condition[0]).add(condition[1]);
            topology[condition[1]] += 1;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < topology.length; i++) {
            if (topology[i] == 0)
                queue.add(i);
        }

        while(!queue.isEmpty()){
            int poll = queue.poll();
            answer.add(poll + 1);

            for (int i = 0; i < adjList.get(poll).size(); i++) {
                int vertex = adjList.get(poll).get(i);
                topology[vertex] -= 1;
                if (topology[vertex] == 0) queue.add(vertex);
            }
        }

        return answer.stream().mapToInt(i->i).toArray();
    }
}
