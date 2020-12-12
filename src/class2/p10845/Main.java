package class2.p10845;

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
        Deque<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            String[] split = br.readLine().split(" ");

            switch (split[0]) {
                case "push":
                    queue.add(Integer.parseInt(split[1]));
                    break;
                case "pop":
                    if (queue.isEmpty()) {
                        bw.write("-1\n");
                    } else {
                        bw.write(queue.poll() + "\n");
                    }
                    break;
                case "size":
                    bw.write(queue.size() + "\n");
                    break;
                case "empty":
                    if (queue.isEmpty()) {
                        bw.write("1\n");
                    } else {
                        bw.write("0\n");
                    }
                    break;
                case "front":
                    if (queue.isEmpty()) {
                        bw.write("-1\n");
                    } else {
                        bw.write(queue.getFirst() + "\n");
                    }
                    break;
                case "back":
                    if (queue.isEmpty()) {
                        bw.write("-1\n");
                    } else {
                        bw.write(queue.getLast() + "\n");
                    }
                    break;
            }
        }
        bw.flush();
    }
}
