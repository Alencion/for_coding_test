package completeSearch.mockExam;

import java.util.*;

public class Solution {
    public int[] solution(int[] answers) {
        int[] answer = {};

        List<Person> people = new ArrayList<>();

        people.add(new Person(1, new int[]{1, 2, 3, 4, 5}));
        people.add(new Person(2, new int[]{2, 1, 2, 3, 2, 4, 2, 5}));
        people.add(new Person(3, new int[]{3, 3, 1, 1, 2, 2, 4, 4, 5, 5}));

        for (Person person : people)
            for (int isRight : answers)
                person.chopping(isRight);

        Collections.sort(people);
        int max = people.get(0).matchedCount;

        answer = people.stream()
                .filter(person -> max == person.matchedCount)
                .sorted(Comparator.comparingInt(p -> p.number))
                .mapToInt(p->p.number).toArray();

        return answer;
    }

    public static void main(String[] args) {
        int[] answers = {1,2,3,4,5};
        System.out.println(Arrays.toString(new Solution().solution(answers)));
    }
}

class Person implements Comparable<Person>{
    int number;
    int[] pattern;
    int index;
    int matchedCount;

    public Person(int number, int[] pattern) {
        this.number = number;
        this.pattern = pattern;
    }

    public void chopping(int answer) {
        if (pattern[index++] == answer)
            matchedCount++;
        index %= pattern.length;
    }

    @Override
    public int compareTo(Person o) {
        return Integer.compare(o.matchedCount, this.matchedCount);
    }
}
