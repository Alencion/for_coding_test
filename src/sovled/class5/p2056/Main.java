package sovled.class5.p2056;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 작업
 * 2021-01-06
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        int[] time = new int[n];
        int[] edgesCount = new int[n];
        for (int i = 0; i < n; i++) {
            String[] split = br.readLine().split(" ");
            time[i] = Integer.parseInt(split[0]);

            int preWorksSize = Integer.parseInt(split[1]) + 2;
            edgesCount[i] = preWorksSize - 2;
            for (int j = 2; j < preWorksSize; j++) {
                adjList.get(Integer.parseInt(split[j]) - 1).add(i);
            }
        }

        int[] dp = new int[n];

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (edgesCount[i] == 0) {
                queue.add(i);
                dp[i] = time[i];
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int to : adjList.get(current)) {
                edgesCount[to]--;
                dp[to] = Math.max(dp[to], dp[current]);

                if (edgesCount[to] == 0) {
                    queue.add(to);
                    dp[to] += time[to];
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, dp[i]);
        }

        System.out.println(answer);
    }
}
