package segment.p2042;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static long[] segment;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");

        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);
        int k = Integer.parseInt(split[2]);

        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        int[][] queries = new int[m + k][3];
        for (int i = 0; i < m + k; i++) {
            split = br.readLine().split(" ");
            queries[i][0] = Integer.parseInt(split[0]);
            queries[i][1] = Integer.parseInt(split[1]);
            queries[i][2] = Integer.parseInt(split[2]);
        }

        solution(n, numbers, queries);
    }

    private static void solution(int n, int[] numbers, int[][] queries) {
        segment = new long[n * 4];

        init(numbers, 0, n - 1, 1);

        for (int[] query : queries) {
            if (query[0] == 1) {
                update(0, n - 1, 1, query[1] - 1, numbers[query[1] - 1] - query[2]);
                numbers[query[1] - 1] = query[2];
            } else {
                long subSum = sum(0, n - 1, 1, query[1] - 1, query[2] - 1);
                System.out.println(subSum);
            }
        }
    }

    private static void update(int start, int end, int node, int target, int value) {
        if (target < start || end < target) return;
        segment[node] -= value;
        if (start == end) return;
        int mid = (start + end) / 2;
        update(start, mid, node *2 , target, value);
        update(mid + 1, end, node * 2 + 1, target, value);
    }

    private static long sum(int start, int end, int node, int left, int right) {
        if (end < left || right < start) return 0;
        if (left <= start && end <= right) return segment[node];
        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }

    private static long init(int[] numbers, int start, int end, int node) {
        if (start == end) return segment[node] = numbers[start];
        int mid = (start + end) / 2;
        return segment[node] = init(numbers, start, mid, node * 2) + init(numbers, mid + 1, end, node * 2 + 1);
    }

    @Test
    public void test(){
        double sqrt = new Main().sqrt(9);
        System.out.println(sqrt);
    }

    public double sqrt(double a)
    {
        double x = 2;

        for(int i = 0; i < 10; i++) {
            x = ( x + ( a / x ) ) / 2;
        }

        return x;
    }
}
