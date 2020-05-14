package p2675;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int loop_count = Integer.parseInt(br.readLine());

        for (int i = 0; i < loop_count; i++) {
            String input = br.readLine();
            String[] split = input.split(" ");
            int number = Integer.parseInt(split[0]);
            String str = split[1];
            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < str.length(); j++) {
                for (int k = 0; k < number; k++) {
                    sb.append(str.charAt(j));
                }
            }
            System.out.println(sb.toString());
        }
    }
}
