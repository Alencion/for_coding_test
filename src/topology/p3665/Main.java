package topology.p3665;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());

            String[] split = br.readLine().split(" ");
            int[] originRank = new int[n];
            for (int j = 0; j < n; j++) {
                originRank[j] = Integer.parseInt(split[j]) - 1;
            }

            int m = Integer.parseInt(br.readLine());
            int[][] edge = new int[m][2];
            for (int j = 0; j < m; j++) {
                split = br.readLine().split(" ");
                edge[j][0] = Integer.parseInt(split[0]) - 1;
                edge[j][1] = Integer.parseInt(split[1]) - 1;
            }

            int[] answer = solution(n, originRank, m, edge);
            if (answer[0] == -1) System.out.println("?");
            else if (answer[0] == -2) System.out.println("IMPOSSIBLE");
            else {
                for (int j = 0; j < answer.length; j++) {
                    System.out.print((answer[j] + 1) + " ");
                }
                System.out.println();
            }
        }
    }

    private static int[] solution(int n, int[] originRank, int m, int[][] edges) {
        int[][] adjMatrix = new int[n][n];
        int[] topology = new int[n];

        for (int i = 0; i < n; i++) {
            int currentOriginRank = originRank[i];
            for (int j = i + 1; j < n; j++) {
                adjMatrix[currentOriginRank][originRank[j]] = 1;
                topology[originRank[j]] += 1;
            }
        }

        for (int[] edge : edges) {
            if (adjMatrix[edge[0]][edge[1]] == 1) {
                adjMatrix[edge[0]][edge[1]] = 0;
                adjMatrix[edge[1]][edge[0]] = 1;
                topology[edge[1]] -= 1;
                topology[edge[0]] += 1;
            } else {
                adjMatrix[edge[0]][edge[1]] = 1;
                adjMatrix[edge[1]][edge[0]] = 0;
                topology[edge[1]] += 1;
                topology[edge[0]] -= 1;
            }
        }

        int[] answer = topologySort(topology, adjMatrix);

        return answer;
    }

    private static int[] topologySort(int[] topology, int[][] adjMatrix) {
        Queue<Integer> queue = new LinkedList<>();

        int[] answer = new int[topology.length];
        for (int i = 0; i < topology.length; i++) {
            if (topology[i] == 0) queue.add(i);
        }

        for (int i = 0; i < topology.length; i++) {

            if (queue.size() > 2) return new int[]{-1};
            if (queue.isEmpty()) return new int[]{-2};

            int poll = queue.poll();
            answer[i] = poll;

            for (int j = 0; j < topology.length; j++) {
                if (adjMatrix[poll][j] == 1) {
                    topology[j] -= 1;
                    if (topology[j] == 0) {
                        queue.add(j);
                    }
                }
            }
        }

        return answer;
    }
}