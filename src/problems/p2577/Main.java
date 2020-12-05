package p2577;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        // write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());
        int c = Integer.parseInt(br.readLine());

        String result = String.valueOf(a * b * c);

        int[] array = new int[10];

        for (int i = 0; i < result.length(); i++) {
            array[result.charAt(i) - '0'] += 1;
        }

        Arrays.stream(array).forEach(System.out::println);
    }
}
