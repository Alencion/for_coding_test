package sovled.class2.p10814;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 2020-12-10 나이순 정렬
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        List<Member> members = new ArrayList<>();

        String[] split;
        for (int i = 0; i < n; i++) {
            split = br.readLine().split(" ");
            members.add(new Member(i, Integer.parseInt(split[0]), split[1]));
        }

        Collections.sort(members);

        members.forEach(System.out::println);
    }

    static class Member implements Comparable<Member> {
        int index;
        int age;
        String name;

        public Member(int index, int age, String name) {
            this.index = index;
            this.age = age;
            this.name = name;
        }

        @Override
        public int compareTo(Member o) {
            if (this.age != o.age) return Integer.compare(this.age, o.age);
            return Integer.compare(this.index, o.index);
        }

        @Override
        public String toString() {
            return this.age + " " + this.name;
        }
    }
}
