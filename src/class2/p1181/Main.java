package class2.p1181;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

// 단어 정렬
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int loopNumber = Integer.parseInt(br.readLine());

        HashSet<String> strings = new HashSet<>();

        for (int i = 0; i < loopNumber; i++) {
            strings.add(br.readLine());
        }
        ArrayList<String> collection = new ArrayList<>(strings);
        collection.stream().sorted((a, b) -> {
            if (a.length() < b.length()) return -2;
            if (a.length() == b.length()) {
                return a.compareTo(b);
            } else return 2;
        }).forEach(System.out::println);
    }
}
