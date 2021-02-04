package sovled.class5.p1202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ReMain {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int k = Integer.parseInt(split[1]);

        List<Jewelry> jewelries = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            split = br.readLine().split(" ");
            jewelries.add(new Jewelry(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
        }

        List<Integer> bag = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            bag.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(jewelries);
        Collections.sort(bag);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        long sum = 0;
        int index = 0;
        for (int i = 0; i < bag.size(); i++) {
            while (index < n && jewelries.get(index).weight <= bag.get(i)) {
                pq.add(jewelries.get(index).value);
                index++;
            }

            if (!pq.isEmpty()) sum += pq.poll();
        }
        System.out.println(sum);

    }

    static class Jewelry implements Comparable<Jewelry> {
        int weight;
        int value;

        public Jewelry(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Jewelry o) {
            return Integer.compare(weight, o.weight);
        }
    }
}

