package class5.p2166;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 다각형의 면적
 * 2021-01-07
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        Spot[] spot = new Spot[N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());

            spot[i] = new Spot(x, y);
        }

        long ans = 0;
        for (int i = 2; i < N; i++) {
            ans += area(spot[1], spot[i], spot[i + 1]);
        }
        ans = Math.abs(ans);
        if (ans % 2 == 1)
            System.out.println((ans / 2) + ".5");
        else
            System.out.println((ans / 2) + ".0");

        bw.flush();
        br.close();
        bw.close();
    }

    public static long area(Spot first, Spot second, Spot third) {
        return ((first.x * second.y + second.x * third.y + third.x * first.y)
                - (first.x * third.y + second.x * first.y + third.x * second.y));
    }

    static class Spot {
        long x, y;

        public Spot(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
}

