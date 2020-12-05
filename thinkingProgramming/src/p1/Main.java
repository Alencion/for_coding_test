package p1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String start = br.readLine();

        int problem = 0;

        String problemString = br.readLine();
        while(!problemString.equals("고무오리 디버깅 끝")){
            if (problem == 0 && problemString.equals("고무오리")){
                problem += 2;
            }
            else if (problemString.equals("고무오리")){
                problem -= 1;
            }
            else if (problemString.equals("문제")){
                problem += 1;
            }
            problemString = br.readLine();
        }

        if (problem == 0) System.out.println("고무오리야 사랑해");
        else System.out.println("힝구");
    }
}
