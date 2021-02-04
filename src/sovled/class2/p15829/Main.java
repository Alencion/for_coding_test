package sovled.class2.p15829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * 2020-12-12
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int l = Integer.parseInt(br.readLine());
        BigInteger r = new BigInteger("1");
        int m = 1234567891;

        String str = br.readLine();
        BigInteger sum = new BigInteger("0");
        for (int i = 0; i < l; i++) {
            int current = str.charAt(i) - 'a' + 1;
            sum = sum.add(new BigInteger(String.valueOf(r.multiply(new BigInteger(String.valueOf(current))))));
            sum = sum.mod(new BigInteger(String.valueOf(m)));
            r = r.multiply(new BigInteger("31"));
        }
        System.out.println(sum);
    }
}
