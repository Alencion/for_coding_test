package class2.p1436;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 영화감독 숌
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int inputNumber = Integer.parseInt(br.readLine());
        int start = 665;
        while (inputNumber != 0) {
            start++;
            if (String.valueOf(start).contains("666"))inputNumber--;
        }
        System.out.println(start);
    }
}
