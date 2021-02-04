package sovled.class2.p7568;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/*
2020-12-10 풀이
 */
public class Fail {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int loopNumber = Integer.parseInt(br.readLine());

        ArrayList<Person> people = new ArrayList<>();
        ArrayList<Person> people2 = new ArrayList<>();
        for (int i = 0; i < loopNumber; i++) {
            String[] splits = br.readLine().split(" ");
            int weight = Integer.parseInt(splits[0]);
            int height = Integer.parseInt(splits[1]);

            people.add(new Person(weight, height));
            people2.add(new Person(weight, height));
        }

        Collections.sort(people);
        HashMap<Integer, Integer> results = new HashMap<>();

        int ranking = 1;

        results.put(people.get(0).weight, ranking);
        for (int i = 1; i < people.size(); i++) {
            if (people.get(i-1).height > people.get(i).height) {
                ranking = i+1;
            }
            results.put(people.get(i).weight, ranking);
        }

        people2.forEach( person -> System.out.print(results.get(person.weight)+" "));
    }
}

class Person implements Comparable<Person>{
    int weight;
    int height;

    public Person(int weight, int height) {
        this.weight = weight;
        this.height = height;
    }

    @Override
    public int compareTo(Person o) {
        return Integer.compare(o.weight, this.weight);
    }

    @Override
    public String toString() {
        return "Person{" +
                "weight=" + weight +
                ", height=" + height +
                '}';
    }
}
