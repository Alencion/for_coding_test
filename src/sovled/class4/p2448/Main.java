package sovled.class4.p2448;

import java.io.*;

/**
 * 별찍기 - 11
 * 2020-12-29
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        char[][] map = new char[n][n * 2 - 1];

        drawStar(0, n, 0, n * 2 - 1, map);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n * 2 - 1; j++) {
                if (map[i][j] == 0) bw.write(" ");
                else bw.write("*");
            }
            bw.write('\n');
        }
        bw.flush();
    }

    private static void drawStar(int hStart, int hEnd, int start, int end, char[][] map) {
        if (hEnd - hStart == 3) {

            map[hStart][start + 2] = '*';

            map[hStart + 1][start + 1] = '*';
            map[hStart + 1][start + 3] = '*';

            map[hStart + 2][start] = '*';
            map[hStart + 2][start + 1] = '*';
            map[hStart + 2][start + 2] = '*';
            map[hStart + 2][start + 3] = '*';
            map[hStart + 2][start + 4] = '*';
            return;
        }

        int temp = (end - start) / 4;
        drawStar(hStart, (hStart + hEnd) / 2, start + temp + 1, end - temp - 1, map);
        drawStar((hStart + hEnd) / 2, hEnd, start, (start + end) / 2 - 1, map);
        drawStar((hStart + hEnd) / 2, hEnd, (start + end) / 2 + 1, end, map);
    }
}
