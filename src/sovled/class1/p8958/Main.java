package sovled.class1.p8958;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int loopNumber = Integer.parseInt(br.readLine());

        for (int i = 0; i < loopNumber; i++) {
            String inputString = br.readLine();
            int total = 0, subtotal = 0;

            for (int j = 0; j < inputString.length(); j++) {
                if (inputString.charAt(j) == 'O') {
                    if (j > 0 && inputString.charAt(j - 1) == 'O')
                        subtotal += 1;
                    else
                        subtotal = 1;
                    total += subtotal;
                }
            }
            System.out.println(total);
        }
    }
}
