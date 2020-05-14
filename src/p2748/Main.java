package p2748;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(br.readLine());
        List<Long> integers = new ArrayList<>();

        integers.add((long) 0);
        integers.add((long) 1);
        for (int i = 2; i <= number; i++) {
            integers.add(integers.get(i-2) + integers.get(i-1));
        }
        System.out.println(integers.get(number));
    }
}
