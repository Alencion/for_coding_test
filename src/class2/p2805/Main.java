package class2.p2805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);

        int[] array = new int[n];
        split = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(split[i]);
        }

        int answer = solution(n, m, array);
        System.out.println(answer);
    }

    private static int solution(int n, int m, int[] array) {
        long max = Arrays.stream(array).max().orElse(0);
        long min = 0;

        int treeMax = 0;
        while (min <= max) {
            int mid = (int) ((min + max) / 2);

            long cutTree = check(array, mid);
            if (cutTree >= m) {
                min = mid + 1;
                treeMax = Math.max(treeMax, mid);
            } else {
                max = mid - 1;
            }
        }

        return treeMax;
    }

    private static long check(int[] array, long mid) {
        long sum = 0;
        for (int tree : array) {
            if (tree > mid){
                sum += tree - mid;
            }
        }
        return sum;
    }
}
