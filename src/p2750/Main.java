package p2750;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Set<Integer> integers = new HashSet<>();
        String str = br.readLine();
        while (!(str = br.readLine()).equals("")) {
            integers.add(Integer.parseInt(str));
        }

        integers.stream().sorted().forEach(System.out::println);
    }
}
