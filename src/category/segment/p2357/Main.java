package category.segment.p2357;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[][] segment;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);

        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        int[][] query = new int[m][2];
        for (int i = 0; i < m; i++) {
            split = br.readLine().split(" ");
            query[i][0] = Integer.parseInt(split[0]);
            query[i][1] = Integer.parseInt(split[1]);
        }

        solution(n, m, numbers, query);
    }

    private static void solution(int n, int m, int[] numbers, int[][] queries) {
        segment = new int[n * 4][2];

        // 0 - min, 1 - max
        init(numbers, 0, n - 1, 1);

        for (int[] query : queries) {
            int[] minMax = find(0, n - 1, 1, query[0] - 1, query[1] - 1);
            System.out.println(minMax[0] + " " + minMax[1]);
        }
    }

    private static int[] find(int start, int end, int node, int left, int right) {
        if (right < start || end < left) return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};

        if (left <= start && end <= right) return segment[node];
        int mid = (start + end) / 2;
        int[] leftChild = find(start, mid, node * 2, left, right);
        int[] rightChild = find(mid + 1, end, node * 2 + 1, left, right);
        return new int[]{Math.min(leftChild[0], rightChild[0]), Math.max(leftChild[1], rightChild[1])};
    }

    private static void init(int[] numbers, int start, int end, int node) {
        if (start == end) {
            segment[node][0] = numbers[start];
            segment[node][1] = numbers[start];
            return;
        }

        int mid = (start + end) / 2;
        init(numbers, start, mid, node * 2);
        init(numbers, mid + 1, end, node * 2 + 1);
        segment[node][0] = Math.min(segment[node * 2][0], segment[node * 2 + 1][0]);
        segment[node][1] = Math.max(segment[node * 2][1], segment[node * 2 + 1][1]);
    }
}
