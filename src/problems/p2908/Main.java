package p2908;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        StringBuilder sb = new StringBuilder(split[0]);
        sb.reverse();
        int number1 = Integer.parseInt(sb.toString());
        sb = new StringBuilder(split[1]);
        sb.reverse();
        int number2 = Integer.parseInt(sb.toString());

        System.out.println(Math.max(number1, number2));

    }
}
