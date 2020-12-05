package segment.p11659;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");

        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);

        int[] numbers = new int[n];
        split = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(split[i]);
        }

        int[][] questions = new int[m][2];
        for (int i = 0; i < m; i++) {
            split = br.readLine().split(" ");
            questions[i][0] = Integer.parseInt(split[0]);
            questions[i][1] = Integer.parseInt(split[1]);
        }

        solution(n, m, numbers, questions);
    }

    private static void solution(int n, int m, int[] numbers, int[][] questions) {
        long[] segment = new long[n * 4];

        init(segment, numbers, 0, n-1, 1);

        for (int i = 0; i < m; i++) {
            System.out.println(sum(segment, 0, n-1, 1, questions[i][0] - 1, questions[i][1] - 1));
        }
    }

    private static long init(long[] segment, int[] numbers, int start, int end, int node) {
        if (start == end) return segment[node] = numbers[start];
        int mid = (start + end) / 2;
        return segment[node] = init(segment, numbers, start, mid, node * 2) + init(segment, numbers, mid + 1, end, node * 2 + 1);
    }

    private static long sum(long[] segment, int start, int end, int node, int left, int right){
        if (left > end || right < start) return 0;
        if (left <= start && end <= right) return segment[node];
        int mid = (start + end) / 2;
        return sum(segment, start, mid, node*2, left, right) + sum(segment, mid + 1, end, node*2 + 1, left, right);
    }

    
}
