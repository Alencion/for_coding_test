package p2562;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> list = new ArrayList<>();

        String str;

        while(!(str = br.readLine()).equals("")){
            list.add(Integer.parseInt(str));
        }

        int max = Collections.max(list);
        int index = list.indexOf(max);

        System.out.println(max);
        System.out.println(index);
    }
}
