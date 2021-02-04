package sovled.class2.p7568;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
2020-12-10
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        List<Person> people = new ArrayList<>();

        String[] split;
        for (int i = 0; i < n; i++) {
            split = br.readLine().split(" ");
            people.add(new Person(i, Integer.parseInt(split[0]), Integer.parseInt(split[1])));
        }

        int[] answer = solution(n, people);
        Arrays.stream(answer).forEach(rank -> System.out.print(rank + " "));
    }

    private static int[] solution(int n, List<Person> people) {
        int[] answer = new int[n];

        for (int i = 0; i < n; i++) {
            int count = 1;
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (people.get(i).compareTo(people.get(j)) > 0) {
                    count++;
                }
            }
            answer[i] = count;
        }

        return answer;
    }

    static class Person implements Comparable<Person> {
        int index;
        int weight;
        int height;

        public Person(int index, int weight, int height) {
            this.index = index;
            this.weight = weight;
            this.height = height;
        }

        @Override
        public int compareTo(Person o) {
            if (o.weight > this.weight && o.height > this.height) return 1;
            else if (o.weight < this.weight && o.height < this.height) return -1;
            return 0;
        }
    }
}
