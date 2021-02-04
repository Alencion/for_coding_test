package sovled.class3.p6064;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
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
        int count = x % (m + 1);
        int tempY = x;

        for (int j = 0; j < n; j++) {
            int ty = tempY % n == 0 ? n : tempY % n;
            if (ty == y) {
                break;
            }

            tempY = ty + m;
            count += m;
        }

        if (count > lcm(m, n)) {
            return -1;
        } else {
            return count;
        }
    }

    static int lcm(int x, int y) {
        return x * y / gcd(x, y);
    }

    /* 최대 공약수*/
    static int gcd(int x, int y) {
        while (y != 0) {
            int r = x % y;
            x = y;
            y = r;
        }
        return x;
    }
}
