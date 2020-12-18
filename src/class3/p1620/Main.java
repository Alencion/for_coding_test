package class3.p1620;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/***
 * 나는야 포켓몬 마스터 이다솜
 * 2020-12-18
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);

        Map<String, Integer> nameToIndexMap = new HashMap<>();
        Map<Integer, String> indexToNameMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String readLine = br.readLine();
            nameToIndexMap.put(readLine, i + 1);
            indexToNameMap.put(i + 1, readLine);
        }

        for (int i = 0; i < m; i++) {
            String readLine = br.readLine();
            if (isNumber(readLine)) {
                bw.write(indexToNameMap.get(Integer.parseInt(readLine)) + "\n");
            } else {
                bw.write(nameToIndexMap.get(readLine) + "\n");
            }
        }
        bw.flush();
    }

    private static boolean isNumber(String readLine) {
        return Character.isDigit(readLine.charAt(0));
    }
}
