package sovled.class4.p1043;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 거짓말
 * 2020-12-25
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);

        split = br.readLine().split(" ");
        int size = Integer.parseInt(split[0]);

        Set<Integer> knowPeople = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            queue.add(Integer.parseInt(split[i + 1]));
            knowPeople.add(Integer.parseInt(split[i + 1]));
        }

        Party[] parties = new Party[m];
        for (int i = 0; i < m; i++) {
            parties[i] = new Party();
            split = br.readLine().split(" ");
            size = Integer.parseInt(split[0]);
            for (int j = 0; j < size; j++) {
                parties[i].addPerson(Integer.parseInt(split[j + 1]));
            }
        }

        int count = m;
        boolean[] checkPerson = new boolean[n];
        boolean[] checkParty = new boolean[m];
        while(!queue.isEmpty()){
            int poll = queue.poll();

            checkPerson[poll-1] = true;
            for (int i = 0; i < parties.length; i++) {
                Party party = parties[i];

                if (!checkParty[i] && party.findPerson(poll)) {
                    checkParty[i] = true;
                    for (int person : party.people) {
                        if (!checkPerson[person-1]){
                            queue.add(person);
                        }
                    }

                    count--;
                }
            }
        }

        System.out.println(count);
    }

    static class Party {
        Set<Integer> people;

        public Party() {
            this.people = new HashSet<>();
        }

        public void addPerson(int index) {
            people.add(index);
        }

        public boolean findPerson(int index) {
            return people.contains(index);
        }
    }
}
