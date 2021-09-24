package sovled.class3.p1676;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 펙토리얼 0의 개수
 * 2020-12-21
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int count = 0;
        int fiveCount = 5;
        while (fiveCount <= n) {
            for (int i = fiveCount; i <= n; i += fiveCount) {
                if (i % fiveCount == 0) count++;
            }
            fiveCount *= 5;
        }

        System.out.println(count);
    }
}
