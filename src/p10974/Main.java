package p10974;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
//    public void solution(int N) {
//        int[] output = new int[N];
//        dfs(N, 0, output, new boolean[N]);
//    }
//
//    private void dfs(int N, int depth, int[] output, boolean[] visited) {
//        if (depth == N) {
//            print(output);
//            return;
//        }
//
//        for (int i = 0; i < N; i++) {
//            if (visited[i]) continue;
//            visited[i] = true;
//            output[depth] = i + 1;
//            dfs(N, depth + 1, output, visited);
//            visited[i] = false;
//        }
//    }
//
//    private void print(int[] output) {
//        for (int value : output) System.out.print(value + " ");
//        System.out.println();
//    }

    public void solution(int N){
        int max = (1 << N);

        for (int i = 0; i < N; i++) {

        }
        /*
        * 1
        * 1 2
        * 2 1
        * 1 2 3
        * 1 3 2
        * 2 1 3
        * 2 3 1
        * 3 1 2
        * 3 2 1
        * */
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        new Main().solution(N);
    }
}
