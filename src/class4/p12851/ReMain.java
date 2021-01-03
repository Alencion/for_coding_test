package class4.p12851;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ReMain {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[100001];
        int[] count = new int[100001];
        int[] dist = new int[100001];


        Queue<Integer> q = new LinkedList<>();

        q.add(N);
        visited[N] = true;
        count[N] = 1;

        while (!q.isEmpty()) {
            int now = q.poll();

            int[] next = {now - 1, now + 1, now * 2};
            for (int n : next) {
                if (n >= 0 && n <= 100000) {
                    if (!visited[n]) {
                        visited[n] = true;
                        dist[n] = dist[now] + 1;
                        q.add(n);
                        count[n] = count[now];
                    } else if (dist[n] == dist[now] + 1) {
                        count[n] += count[now];
                    }
                }
            }
        }

        System.out.println(dist[K]);
        System.out.println(count[K]);

    }
}
