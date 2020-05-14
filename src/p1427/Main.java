package p1427;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String string = br.readLine();
        ArrayList<Character> characters = new ArrayList<>();
        for (int i = 0; i < string.length(); i++) {
            characters.add(string.charAt(i));
        }

        characters.sort((a, b) -> {
            if (a > b) return -1;
            else return 0;
        });

        characters.forEach(System.out::print);
    }
}
