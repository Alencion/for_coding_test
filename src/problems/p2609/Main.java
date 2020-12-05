package p2609;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");

        int a = Integer.parseInt(split[0]);
        int b = Integer.parseInt(split[1]);

        int min = Math.min(a, b);
        int max = Math.max(a, b);

        a = max;
        b = min;
        int rest;
        while (a % b != 0) {
             rest = a % b;
            a = b;
            b = rest;
        }

        System.out.println(b);
        System.out.println(min * max / b);
    }
}
