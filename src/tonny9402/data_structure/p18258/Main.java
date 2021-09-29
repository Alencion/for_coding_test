package tonny9402.data_structure.p18258;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

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
                    bw.write(queue.isEmpty() ? "-1\n" : queue.poll() + "\n");
                    break;
                case "size":
                    bw.write(queue.size() + "\n");
                    break;
                case "empty":
                    bw.write(queue.isEmpty() ? "1\n" : "0\n");
                    break;
                case "front":
                    bw.write(queue.isEmpty() ? "-1\n" : queue.getFirst() + "\n");
                    break;
                case "back":
                    bw.write(queue.isEmpty() ? "-1\n" : queue.getLast() + "\n");
                    break;
            }
        }
        bw.flush();
    }
}
