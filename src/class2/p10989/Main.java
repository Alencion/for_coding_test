package class2.p10989;

import java.io.*;

/**
 * 2020-12-12
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int[] count = new int[10001];
        for (int i = 0; i < n; i++) {
            count[Integer.parseInt(br.readLine())] += 1;
        }

        for (int i = 0; i < count.length; i++) {
            for (int j = 0; j < count[i]; j++) {
                bw.write(i+"\n");
            }
        }

        bw.flush();
    }
}
