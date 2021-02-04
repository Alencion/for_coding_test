package sovled.class2.p2798;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputStringSplits = br.readLine().split(" ");

        int cardNumber = Integer.parseInt(inputStringSplits[0]);
        int limitedNumber = Integer.parseInt(inputStringSplits[1]);


        List<Integer> card = Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt).sorted().collect(Collectors.toList());

        int max = 0;
        for (int i = 0; i < cardNumber; i++) {
            int sum = card.get(i);
            for (int j = i+1; j < cardNumber; j++) {
                int sum2 =sum + card.get(j);
                for (int k = j+1; k < cardNumber; k++) {
                    int sum3 = sum2 + card.get(k);
                    if (sum3 <= limitedNumber && sum3 > max) max = sum3;
                }
            }
        }
        System.out.println(max);
    }
}
