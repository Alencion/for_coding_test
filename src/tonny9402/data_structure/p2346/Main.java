package tonny9402.data_structure.p2346;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 풍선 터트리기 - Deque 사용
 * 2021-10-04
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Deque<Balloon> queue = new ArrayDeque<>();

        int n = Integer.parseInt(br.readLine());
        String[] split = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            queue.add(new Balloon(i + 1, Integer.parseInt(split[i])));
        }

        StringBuilder sb = new StringBuilder();
        Balloon current = queue.pollFirst();
        sb.append(current.index);

        while(!queue.isEmpty()){
            if(current.value < 0) {
                int value = current.value * -1;
                for (int i = 0; i < value; i++) {
                    queue.offerFirst(queue.pollLast());
                }
            } else {
                for (int i = 0; i < current.value - 1; i++) {
                    queue.offerLast(queue.pollFirst());
                }
            }

            current = queue.pollFirst();
            sb.append( " " + current.index);
        }

        System.out.println(sb);
    }

    static class Balloon{
        int index;
        int value;

        public Balloon(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
}

