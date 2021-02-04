package sovled.class3.p17219;

import java.io.*;
import java.util.HashMap;

/**
 * 비밀번호 찾기
 * 2020-12-24
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);

        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            split = br.readLine().split(" ");
            map.put(split[0], split[1]);
        }

        for (int i = 0; i < m; i++) {
            String readLine = br.readLine();
            bw.write(map.get(readLine)+"\n");
        }
        bw.flush();
    }
}
