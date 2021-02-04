package sovled.class1.p2475;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");

        int total = 0;
        for (String digit : split) {
            total += Math.pow(Integer.parseInt(digit), 2);
        }
        System.out.println(total % 10);
    }
}
