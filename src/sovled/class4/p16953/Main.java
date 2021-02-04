package sovled.class4.p16953;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * A -> B
 * 2021-01-02
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");

        int a = Integer.parseInt(split[0]);
        int b = Integer.parseInt(split[1]);

        Queue<Number> queue = new LinkedList<>();

        queue.add(new Number(a, 1));
        Set<Long> set = new HashSet<>();
        set.add((long) a);
        while (!queue.isEmpty()) {
            Number current = queue.poll();

            if (current.value == b) {
                System.out.println(current.time);
                return;
            }

            long newA = current.value * 2;
            if (newA > 0 && newA <= b && !set.contains(newA)) {
                set.add(newA);
                queue.add(new Number(newA, current.time + 1));
            }
            newA = current.value * 10 + 1;
            if (newA > 0 && newA <= b && !set.contains(newA)) {
                set.add(newA);
                queue.add(new Number(newA, current.time + 1));
            }
        }
        System.out.println(-1);
    }

    static class Number {
        long value;
        long time;

        public Number(long value, long time) {
            this.value = value;
            this.time = time;
        }
    }
}
