package dijkstra.p10217;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            String[] split = br.readLine().split(" ");
            int n = Integer.parseInt(split[0]);
            int m = Integer.parseInt(split[1]);
            int k = Integer.parseInt(split[2]);

            int[][] tickets = new int[k][4];
            for (int j = 0; j < k; j++) {
                split = br.readLine().split(" ");
                tickets[j][0] = Integer.parseInt(split[0]) - 1;
                tickets[j][1] = Integer.parseInt(split[1]) - 1;
                tickets[j][2] = Integer.parseInt(split[2]);
                tickets[j][3] = Integer.parseInt(split[3]);
            }

            int answer = solution(n, m, tickets);
            if (answer == Integer.MAX_VALUE) {
                System.out.println("Poor KCM");
            } else System.out.println(answer);
        }
    }

    private static int solution(int n, int m, int[][] tickets) {

        List<List<Ticket>> adjList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] ticket : tickets) {
            adjList.get(ticket[0]).add(new Ticket(ticket[1], ticket[2], ticket[3]));
        }

        int[][] dist = dijkstra(n, m, adjList);
        int min = Integer.MAX_VALUE;

        for (int i = 1; i <= m; i++) {
            min = Math.min(min, dist[n-1][i]);
        }

        return min;
    }

    private static int[][] dijkstra(int n, int m, List<List<Ticket>> adjList) {
        int[][] dist = new int[n][m + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        PriorityQueue<Ticket> pq = new PriorityQueue<>();

        dist[0][0] = 0;

        pq.add(new Ticket(0, 0, 0));

        while (!pq.isEmpty()) {
            Ticket ticket = pq.poll();
            int vertex = ticket.to;
            int time = ticket.time;
            int cost = ticket.cost;

            if (dist[vertex][cost] < time) continue;
            dist[vertex][cost] = time;

            for (int i = 0; i < adjList.get(vertex).size(); i++) {
                ticket = adjList.get(vertex).get(i);

                if (cost + ticket.cost > m) continue;

                if (dist[ticket.to][cost + ticket.cost] > time + ticket.time) {
                    for (int j = ticket.cost + cost; j <= m; j++) {
                        if (dist[ticket.to][j] > time + ticket.time)
                            dist[ticket.to][j] = time + ticket.time;
                    }
                    pq.add(new Ticket(ticket.to, time + ticket.time, ticket.cost + cost));
                }
            }
        }

        return dist;
    }

    private static class Ticket implements Comparable<Ticket> {
        int to;
        int time;
        int cost;

        public Ticket(int to, int time, int cost) {
            this.to = to;
            this.time = time;
            this.cost = cost;
        }

        @Override
        public int compareTo(Ticket o) {
            if (time == o.time) return Integer.compare(cost, o.cost);
            return Integer.compare(time, o.time);
        }
    }
}
