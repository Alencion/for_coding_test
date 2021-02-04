package sovled.class2.p11050;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputStringSplits = br.readLine().split(" ");

        int n = Integer.parseInt(inputStringSplits[0]);
        int k = Integer.parseInt(inputStringSplits[1]);

        int sum = 1;
        for (int i = 0; i < k; i++) {
            sum *= n--;
        }
        int divide = 1;
        for (int i = 1; i <= k; i++) {
            divide *= i;
        }
        System.out.println(sum/divide);
    }
}
