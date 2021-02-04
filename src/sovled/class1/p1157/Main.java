package sovled.class1.p1157;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] inputString = br.readLine().toUpperCase().toCharArray();

        Map<Character, Integer> alphabetMap = new HashMap<>();

        for (char alphabet : inputString) {
            alphabetMap.computeIfPresent(alphabet, (k, v) -> v + 1);
            alphabetMap.putIfAbsent(alphabet, 1);
        }

        Map.Entry<Character, Integer> max = Collections.max(alphabetMap.entrySet(), Map.Entry.comparingByValue());
        alphabetMap.remove(max.getKey());
        Map.Entry<Character, Integer> subMax = null;
        if (alphabetMap.size() != 0)
            subMax = Collections.max(alphabetMap.entrySet(), Map.Entry.comparingByValue());

        if (subMax != null && max.getValue().equals(subMax.getValue())) System.out.println("?");
        else
            System.out.println(max.getKey());
    }
}
