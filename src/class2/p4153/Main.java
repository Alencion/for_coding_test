package class2.p4153;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputString;

        while (!(inputString = br.readLine()).equals("0 0 0")) {
            String[] split = inputString.split(" ");
            List<Integer> lines = Arrays.stream(split).map(Integer::parseInt).sorted().collect(Collectors.toList());
            int auset = lines.get(0);
            int ausar = lines.get(1);
            int haru = lines.get(2);

            if (isRightTriangle(auset, ausar, haru)) {
                System.out.println("right");
            } else System.out.println("wrong");
        }
    }

    private static boolean isRightTriangle(int auset, int ausar, int haru) {
        return Math.pow(auset, 2) + Math.pow(ausar, 2) == Math.pow(haru, 2);
    }

}
