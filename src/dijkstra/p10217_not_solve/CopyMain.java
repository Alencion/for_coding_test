package dijkstra.p10217_not_solve;

import java.io.*;
import java.util.*;

public class CopyMain {
    private static final int INF = 100 * 1_000;
    static int n, m, k;
    static List<AirPlane> list[];
    static int dp[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            dp = new int[n + 1][m + 1];
            list = new ArrayList[n + 1];

            for (int j = 0; j <= n; j++)
                Arrays.fill(dp[j], INF);

            for (int j = 0; j <= n; j++)
                list[j] = new ArrayList<>();

            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());

                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());

                list[start].add(new AirPlane(end, cost, time));
            }

            int result = dijkstra();
            if (result == INF) System.out.println("Poor KCM");
            else System.out.println(result);
        }
    }

    private static int dijkstra() {
        for (int i = 1; i < dp.length; i++)
            Arrays.fill(dp[i], INF);


        PriorityQueue<AirPlane> queue = new PriorityQueue<>();
        queue.add(new AirPlane(1, 0, 0));
        dp[1][0] = 0;

        while (!queue.isEmpty()) {
            AirPlane airPlane = queue.poll();
            int node = airPlane.end;
            int cost = airPlane.cost;
            int time = airPlane.time;

            if (node == n) break;
            if (dp[node][cost] < time) continue;
            dp[node][cost] = time;

            for (int i = 0; i < list[node].size(); i++) {
                AirPlane toAirplane = list[node].get(i);
                int toNode = toAirplane.end;
                int toCost = cost + toAirplane.cost;
                int toTime = time + toAirplane.time;

                if (toCost > m) continue;
                if (dp[toNode][toCost] > toTime) {
                    for (int j = toCost; j <= m; j++) {
                        if (dp[toNode][j] > toTime) dp[toNode][j] = toTime;
                    }
                    queue.add(new AirPlane(toNode, toCost, toTime));
                }
            }
        }

        int result = Integer.MAX_VALUE;

        for (int i = 1; i <= m; i++)
            result = Math.min(result, dp[n][i]);


        return result;
    }

    static class AirPlane implements Comparable<AirPlane> {
        int end;
        int cost;
        int time;

        public AirPlane(int end, int cost, int time) {
            this.end = end;
            this.cost = cost;
            this.time = time;
        }

        @Override
        public int compareTo(AirPlane airPlane) {
            if (this.time == airPlane.time) return cost - airPlane.cost;
            return this.time - airPlane.time;
        }
    }
}