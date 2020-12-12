package class2.p10866;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 2020-12-12
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            String[] split = br.readLine().split(" ");

            switch (split[0]) {
                case "push_front":
                    deque.addFirst(Integer.parseInt(split[1]));
                    break;
                case "push_back":
                    deque.addLast(Integer.parseInt(split[1]));
                    break;
                case "pop_front":
                    if (deque.isEmpty()) {
                        bw.write("-1\n");
                    } else {
                        bw.write(deque.pollFirst() + "\n");
                    }
                    break;
                case "pop_back":
                    if (deque.isEmpty()) {
                        bw.write("-1\n");
                    } else {
                        bw.write(deque.pollLast() + "\n");
                    }
                    break;
                case "size":
                    bw.write(deque.size() + "\n");
                    break;
                case "empty":
                    if (deque.isEmpty()) {
                        bw.write("1\n");
                    } else {
                        bw.write("0\n");
                    }
                    break;
                case "front":
                    if (deque.isEmpty()) {
                        bw.write("-1\n");
                    } else {
                        bw.write(deque.getFirst() + "\n");
                    }
                    break;
                case "back":
                    if (deque.isEmpty()) {
                        bw.write("-1\n");
                    } else {
                        bw.write(deque.getLast() + "\n");
                    }
                    break;
            }
        }
        bw.flush();
    }
}
