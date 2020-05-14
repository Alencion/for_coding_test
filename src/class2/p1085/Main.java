package class2.p1085;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputStringSplits = br.readLine().split(" ");

        int x = Integer.parseInt(inputStringSplits[0]);
        int y = Integer.parseInt(inputStringSplits[1]);
        int w = Integer.parseInt(inputStringSplits[2]);
        int h = Integer.parseInt(inputStringSplits[3]);

        ArrayList<Integer> boundaryLength = new ArrayList<>();

        boundaryLength.add(x);
        boundaryLength.add(y);
        boundaryLength.add(w-x);
        boundaryLength.add(h-y);

        System.out.println(Collections.min(boundaryLength));
    }
}
