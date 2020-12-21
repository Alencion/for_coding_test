package class3.p1764;

import java.io.*;
import java.util.*;

/**
 * 듣보잡
 * 2020-12-21
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] split = br.readLine().split(" ");

        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);

        Set<String> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(br.readLine());
        }

        List<String> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            String readLine = br.readLine();
            if (set.contains(readLine)) {
                result.add(readLine);
            }
        }
        System.out.println(result.size());
        Collections.sort(result);
        for (String s : result) {
            bw.write(s + "\n");
        }
        bw.flush();
    }
}
