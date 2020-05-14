package class2.p2775;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        int[][] residents = new int[15][15];

        for (int i = 0; i < residents[0].length; i++) {
            residents[0][i] = i;
        }

        for (int i = 1; i < residents.length; i++) {
            for (int j = 1; j < residents[i].length; j++) {
                residents[i][j] = residents[i-1][j]+ residents[i][j-1];
            }
        }

        for (int i = 0; i < testCase; i++) {
            int floor = Integer.parseInt(br.readLine());
            int count = Integer.parseInt(br.readLine());
            System.out.println(residents[floor][count]);
        }

    }
}
