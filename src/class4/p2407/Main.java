package class4.p2407;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * 조합
 * 2020-12-29
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);

        m = Math.min(m, n - m);

        BigInteger answer = new BigInteger("1");

        for (int i = 0; i < m; i++) {
            answer = answer.multiply(BigInteger.valueOf(n - i));
            answer = answer.divide(BigInteger.valueOf(i + 1));
        }

        System.out.println(answer.toString());
    }
}
