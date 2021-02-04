package sovled.class2.p2292;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int inputNumber = Integer.parseInt(br.readLine());
        int startBoundary = 1;
        int step;

        for (step = 1; inputNumber > startBoundary; step++){
            startBoundary += step * 6;

        }
        System.out.println(step);
    }
}
