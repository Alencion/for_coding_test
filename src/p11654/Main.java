package p11654;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = br.readLine().split(" ");
        int r1 = Integer.parseInt(inputs[0]);
        int s = Integer.parseInt(inputs[1]);
        int r2 = 2* s - r1;
        System.out.println(r2);
    }
}
