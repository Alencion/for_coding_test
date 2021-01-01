package class4.p12851;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static boolean check[] =  new boolean[100001];
    static int cnt[] = new int[100001];
    static int dist[] = new int[100001];
    static int N, K;

    public static void bfs(int N, int K) {

        Queue<Integer> q = new LinkedList<>();

        q.add(N);
        check[N] = true;
        cnt[N] = 1;

        while(!q.isEmpty()) {
            int now = q.poll();
            int[] next = {now-1, now+1, now*2};
            for(int n : next){
                if(n >= 0 && n <= 100000) {
                    if(!check[n]) {
                        check[n] = true;
                        dist[n] = dist[now]+1;
                        q.add(n);
                        cnt[n] = cnt[now];
                    }
                    else if(dist[n] == dist[now] +1){
                        cnt[n] += cnt[now];
                    }
                }
            }
        }
        System.out.println(dist[K]);
        System.out.println(cnt[K]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        bfs(N,K);
    }
}