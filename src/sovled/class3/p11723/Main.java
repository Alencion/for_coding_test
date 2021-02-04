package sovled.class3.p11723;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 집합
 * 2020-12-24
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int m = Integer.parseInt(br.readLine());

        Set<Integer> set = new HashSet<>();
        String[] split;
        for (int i = 0; i < m; i++) {
            split = br.readLine().split(" ");

            int number = 0;
            if (split.length == 2){
                number = Integer.parseInt(split[1]);
            }
            execute(split[0], number, set, bw);
        }
        bw.flush();
    }

    private static void execute(String op, int number, Set<Integer> set, BufferedWriter bw) throws IOException {
        switch (op) {
            case "add":
                set.add(number);
                break;
            case "check":
                bw.write(set.contains(number) ? 1 + "\n" : 0 + "\n");
                break;
            case "remove":
                set.remove(number);
                break;
            case "toggle":
                if (set.contains(number)) {
                    set.remove(number);
                } else {
                    set.add(number);
                }
                break;
            case "all":
                for (int i = 1; i < 21; i++) {
                    set.add(i);
                }
                break;
            case "empty":
                set.clear();
                break;
        }
    }
}
