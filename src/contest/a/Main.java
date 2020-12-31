package contest.a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        String[] split = br.readLine().split(" ");
        char front = split[0].charAt(0);
        boolean check = true;
        for (int i = 1; i < n; i++) {
            char temp = split[i].charAt(0);

            if (front != temp) {
                check = false;
                break;
            }
        }

        if (check) System.out.println(1);
        else System.out.println(0);
    }


}
