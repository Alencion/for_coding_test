package class4.p1016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 제곱 ㄴㄴ수
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        long min = Long.parseLong(split[0]);
        long max = Long.parseLong(split[1]);

        int count = (int) (max - min + 1);
        int squareRoot = ((int) Math.sqrt(max));
        boolean[] visited = new boolean[count];

        for (long i = 2; i <= squareRoot; i++) {
            long square = i * i;
            long start = min % square == 0 ? min / square : (min / square) + 1;

            for (long j = start; j * square <= max; j++) {
                if (visited[(int) ((j * square) - min)]) continue;
                visited[(int) ((j * square) - min)] = true;
                count--;
            }
        }

        System.out.println(count);

    }
}
