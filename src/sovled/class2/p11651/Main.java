package sovled.class2.p11651;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 2020-12-12
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        List<Point> points = new ArrayList<>();

        String[] split;
        for (int i = 0; i < n; i++) {
            split = br.readLine().split(" ");
            points.add(new Point(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
        }

        Collections.sort(points);
        points.forEach(System.out::println);
    }

    static class Point implements Comparable<Point>{

        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            if (this.y == o.y) return Integer.compare(this.x, o.x);
            return Integer.compare(this.y, o.y);
        }

        @Override
        public String toString() {
            return this.x + " " + this.y;
        }
    }
}
