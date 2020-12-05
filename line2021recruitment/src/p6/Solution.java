package p6;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    Map<Character, Applicant> applicantMap = new HashMap<>();
    Map<Character, Company> companyMap = new HashMap<>();

    public String[] solution(String[] companies, String[] applicants) {
        String[] answer = {};

        for (String company : companies) {
            String[] split = company.split(" ");
            companyMap.put(split[0].charAt(0), new Company(split[0].charAt(0), Integer.parseInt(split[2]), split[1]));
        }

        for (String applicant : applicants) {
            String[] split = applicant.split(" ");
            applicantMap.put(split[0].charAt(0), new Applicant(split[0].charAt(0), Integer.parseInt(split[2]), split[1]));
        }

        matching();

        answer = companyMap.values().stream()
                .sorted(Comparator.comparingInt(company -> company.name))
                .map(Company::toString)
                .toArray(String[]::new);

        return answer;
    }

    private void matching() {
        boolean isMatched = false;

        while (!isMatched) {
            isMatched = true;

            for (Applicant applicant : applicantMap.values()) {
                if (applicant.tryCount == 0) {
                    continue;
                }
                if (applicant.partner != '\0') {
                    continue;
                }

                applicant.tryCount -= 1;
                Character remove = applicant.companyPrefer.remove(0);
                Company company = companyMap.get(remove);

                company.temporaryEmployee.add(new Node(applicant.name, company.applicantPrefer.get(applicant.name)));
                applicant.partner = company.name;

                for (int i = company.temporaryEmployee.size(); i > company.maxEmployeeCount; i--) {
                    Node fire = company.temporaryEmployee.poll();
                    applicantMap.get(fire.name).partner = '\0';
                    isMatched = false;
                }
            }
        }
    }

    private static class Applicant {
        char name;
        int tryCount;
        char partner;
        List<Character> companyPrefer;

        public Applicant(char name, int tryCount, String companyPrefer) {
            this.name = name;
            this.tryCount = tryCount;
            this.companyPrefer = companyPrefer.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        }
    }

    private static class Company {
        char name;
        int maxEmployeeCount;
        Map<Character, Integer> applicantPrefer;
        PriorityQueue<Node> temporaryEmployee;

        public Company(char name, int maxEmployeeCount, String applicantPrefer) {
            this.name = name;
            this.maxEmployeeCount = maxEmployeeCount;
            this.applicantPrefer = new HashMap<>();
            for (int i = 0; i < applicantPrefer.length(); i++) {
                this.applicantPrefer.put(applicantPrefer.charAt(i), -(i + 1));
            }
            this.temporaryEmployee = new PriorityQueue<>();
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(name);
            sb.append("_");
            temporaryEmployee.stream()
                    .sorted(Comparator.comparingInt(node -> node.name))
                    .forEach(node -> sb.append(node.name));

            return sb.toString();
        }
    }

    private static class Node implements Comparable<Node> {
        char name;
        int priority;

        public Node(char name, int priority) {
            this.name = name;
            this.priority = priority;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(priority, o.priority);
        }
    }

    @Test
    public void test() {
        String[] companies = {"A fabdec 2", "B cebdfa 2", "C ecfadb 2"};
        String[] applicants = {"a BAC 1", "b BAC 3", "c BCA 2", "d ABC 3", "e BCA 3", "f ABC 2"};

        Assert.assertArrayEquals(new String[]{"A_bf", "B_ce", "C_d"}, solution(companies, applicants));

        new LinkedList<>();
    }

    @Test
    public void test2() {
        String[] companies = {"A abc 2", "B abc 1"};
        String[] applicants = {"a AB 1", "b AB 1", "c AB 1"};

        Assert.assertEquals(Arrays.toString(new String[]{"A_ab", "B_"}), Arrays.toString(solution(companies, applicants)));
    }
}
