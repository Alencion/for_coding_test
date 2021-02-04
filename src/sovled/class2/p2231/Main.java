package sovled.class2.p2231;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int inputNumber = Integer.parseInt(br.readLine());
        int check = 0;
        for (int i = 0; i < inputNumber; i++) {
            if (findSubSum(i) == inputNumber){
                check = i;
                break;
            }
        }
        System.out.println(check);
    }

    private static int findSubSum(int i) {
        int subSum = 0;
        String convertToString = i+"";
        subSum += i;
        for (int j = 0; j < convertToString.length(); j++) {
            subSum += convertToString.charAt(j) - '0';
        }
        return subSum;
    }


}
