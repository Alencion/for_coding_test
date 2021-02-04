package sovled.class1.p11720;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int stringLength = Integer.parseInt(br.readLine());

        String inputString = br.readLine();
        int total =0 ;
        for (int i = 0; i < stringLength; i++) {
            total += inputString.charAt(i)-'0';
        }
        System.out.println(total);
    }
}
