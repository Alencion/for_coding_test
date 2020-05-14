package class2.p10250;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int loopNumber = Integer.parseInt(br.readLine());
        int height, weight, customerNumber;
        ArrayList<String> results = new ArrayList<>();

        for (int i = 0; i < loopNumber; i++) {
            String[] inputStringSplit = br.readLine().split(" ");
            height = Integer.parseInt(inputStringSplit[0]);
            weight = Integer.parseInt(inputStringSplit[1]);
            customerNumber = Integer.parseInt(inputStringSplit[2]) - 1;
            String output = customerNumber % height + 1 + "";

            if (customerNumber / height < 9)
                output += "0";
            output += (customerNumber / height) + 1;
            results.add(output);
        }
        for (String item : results)
            System.out.println(item);
    }
}

