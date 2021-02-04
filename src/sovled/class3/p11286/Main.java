package sovled.class3.p11286;

import java.io.*;
import java.util.PriorityQueue;

/**
 * 절대값 힙
 * 2020-12-23
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>((i1, i2) -> {
            if (Math.abs(i1) == Math.abs(i2)) return Integer.compare(i1, i2);
            return Integer.compare(Math.abs(i1), Math.abs(i2));
        });

        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());

            if (x == 0) {
                if (pq.isEmpty()) bw.write("0\n");
                else bw.write(pq.poll() + "\n");
            } else {
                pq.offer(x);
            }
        }

        bw.flush();
    }
}
