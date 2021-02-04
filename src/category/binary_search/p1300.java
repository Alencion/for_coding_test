package category.binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p1300 {
    private static long result = 0 ;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        long answer = solution(N,k);
        System.out.println(answer);
    }

    private static long solution(int n, int k) {
        long left = 1;
        long right = k;

        return binarySearch(left, right, n, k);
    }

    private static long binarySearch(long left, long right, long n, long k) {
        int cnt = 0;

        long mid = (left + right) / 2;

        if(left > right) return result;
        for(int i = 1; i <= n; i++) {
            cnt += Math.min(mid/i, n);
        }

        if(k <= cnt) {
            result = mid;
            return binarySearch(left, mid -1, n, k);
        }else{
            return binarySearch(mid + 1, right, n,k);
        }
    }
}
