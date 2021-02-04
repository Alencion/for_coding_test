package sovled.class3.p6064;

import java.io.*;

/**
 * 2020-12-27
 */
public class NewMain {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());

        for (int i = 0; i < tc; i++) {
            String[] split = br.readLine().split(" ");

            int m = Integer.parseInt(split[0]);
            int n = Integer.parseInt(split[1]);
            int x = Integer.parseInt(split[2]);
            int y = Integer.parseInt(split[3]);

            int answer = solution(m, n, x, y);
            bw.write(answer + "\n");
        }
        bw.flush();
    }

    private static int solution(int m, int n, int x, int y) {
        int tempX = y % m == 0 ? m : y % m;
        int k = y;
        int max = lcm(m, n);
        while (k < max) {
            if (x == tempX) return k;

            tempX = (tempX + n) % m == 0 ? m : (tempX + n) % m;
            k += n;
        }

        return -1;
    }

    private static int lcm(int m, int n) {
        return m * n / gcd(m, n);
    }

    private static int gcd(int m, int n) {
        if (n == 0) return m;
        return gcd(n, m % n);
    }
}
