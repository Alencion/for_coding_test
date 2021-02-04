package category.back_tracking.p1182;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int s = Integer.parseInt(split[1]);

        int[] sequence = new int[n];
        split = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            sequence[i] = Integer.parseInt(split[i]);
        }

        int answer = solution(n,s, sequence);
        System.out.println(answer);
    }

    private static int solution(int n, int s, int[] sequence) {

        for (int i = 0; i < n; i++) {
            backtracking(n, s, i, sequence[i], sequence);
        }

        return answer;
    }

    private static void backtracking(int n, int s, int depth, int total, int[] sequence) {
        if(depth == n-1 && total == s){
            answer++;
        }

        if (depth + 1 < n) {
            backtracking(n, s, depth + 1, total + sequence[depth + 1], sequence);
            backtracking(n, s, depth + 1, total, sequence);
        }
    }
}
