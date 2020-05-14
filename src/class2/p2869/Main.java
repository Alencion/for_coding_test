package class2.p2869;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputStringSplits = br.readLine().split(" ");
        int canClimbMiter = Integer.parseInt(inputStringSplits[0]);
        int fallDownMiter = Integer.parseInt(inputStringSplits[1]);
        int height = Integer.parseInt(inputStringSplits[2]);

        int days = (height - canClimbMiter) / (canClimbMiter - fallDownMiter) + 1;
        if ((height - canClimbMiter) % (canClimbMiter - fallDownMiter) != 0) days++;
        System.out.println(days);
    }
}
