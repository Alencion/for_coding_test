package class3.p5430;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * AC
 * 2020-12-22
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            String p = br.readLine();

            int n = Integer.parseInt(br.readLine());

            Deque<Integer> deque = new ArrayDeque<>();
            String readLine = br.readLine();
            String[] split = readLine.substring(1, readLine.length()-1).split(",");
            for (String s : split) {
                if (!s.equals(""))
                    deque.add(Integer.parseInt(s));
            }
            solution(p, deque, bw);
        }
        bw.flush();
    }

    private static void solution(String p, Deque<Integer> deque, BufferedWriter bw) throws IOException {
        boolean isHead = true;

        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == 'R'){
                isHead = !isHead;
            } else {
                if (deque.isEmpty()){
                    bw.write("error\n");
                    return;
                }
                delete(isHead, deque);
            }
        }

        bw.write("[");
        if (!deque.isEmpty()){
            bw.write(String.valueOf(delete(isHead, deque)));
            while(!deque.isEmpty()){
                bw.write(","+delete(isHead, deque));
            }
        }
        bw.write("]\n");
    }

    private static Integer delete(boolean isHead, Deque<Integer> deque) {
        if (isHead){
            return deque.pollFirst();
        } else {
            return deque.pollLast();
        }
    }
}
