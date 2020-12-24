package class3.p18870;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 좌표 압축
 * 2020-12-24
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int[] answer = new int[n];

        String[] split = br.readLine().split(" ");
        int[] sorted = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = Integer.parseInt(split[i]);
            sorted[i] = Integer.parseInt(split[i]);
        }

        Arrays.sort(sorted);
        HashMap<Integer, Integer> map = new HashMap<>();
        int index = 0;
        for (int value : sorted) {
            if (map.containsKey(value)) continue;
            map.put(value, index++);
        }

        for (int i = 0; i < n; i++) {
            answer[i] = map.get(answer[i]);
        }

        bw.write(String.valueOf(answer[0]));
        for (int i = 1; i < n; i++) {
            bw.write(" " + answer[i]);
        }
        bw.flush();
    }
}
