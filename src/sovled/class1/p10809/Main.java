package sovled.class1.p10809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputString = br.readLine();

        TreeMap<Integer, Integer> beginsPositionAlphabets = new TreeMap<>();

        for (int i = 0; i < inputString.length(); i++) {
            beginsPositionAlphabets.putIfAbsent(inputString.charAt(i) - 'a', i);
        }

        for (int i = 0; i < 26; i++) {
            if (!beginsPositionAlphabets.containsKey(i)) System.out.print("-1 ");
            else System.out.print(beginsPositionAlphabets.get(i)+" ");
        }
    }
}
