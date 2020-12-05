package p1316_dont_solve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(br.readLine());
        Set<Character> characters = new HashSet<>();
        char previous;
        int total = 0;

        for (int i = 0; i < number; i++) {

            String str = br.readLine();
            previous = str.charAt(0);
            characters.add(str.charAt(0));
            for (int j = 1; j < str.length(); j++) {
                boolean contains = characters.contains(str.charAt(j));
                if (contains) {
                    if (previous != str.charAt(j)) {
                        total += 1;
                        break;
                    }
                    characters.add(str.charAt(j));
                }
                previous = str.charAt(j);
            }
            characters.clear();
        }
        System.out.println(number - total);
    }
}
