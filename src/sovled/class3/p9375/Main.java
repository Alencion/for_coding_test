package sovled.class3.p9375;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 패션왕 신해빈
 * 2020-12-23 이항 계수 대해서 더 생각해보자.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());

            Map<String, Integer> type = new HashMap<>();

            for (int j = 0; j < n; j++) {
                String[] split = br.readLine().split(" ");
                type.put(split[1], type.getOrDefault(split[1], 0) + 1);
            }

            int count = 1;
            for (int value : type.values()) {
                count *= value + 1;
            }
            bw.write((count - 1) + "\n");
        }
        bw.flush();
    }
}
