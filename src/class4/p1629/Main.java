package class4.p1629;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");

        int a = Integer.parseInt(split[0]);
        int b = Integer.parseInt(split[1]);
        int c = Integer.parseInt(split[2]);

        long answer = power(a % c, b, c);
        System.out.println(answer);
    }

    private static long power(long a, long b, long c) {
        if (b == 1) return a;

        long temp = power(a, b / 2, c) % c;

        if (b % 2 == 0) return (temp * temp) % c;
        return (((temp * temp) % c) * a) % c;
    }
}