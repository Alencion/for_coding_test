package back_tracking.p1339;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String[] array = new String[n];
        for (int i = 0; i < n; i++) {
            array[i] = br.readLine();
        }

        int answer = solution(n, array);
        System.out.println(answer);
    }

    private static int solution(int n, String[] array) {

        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < array[i].length(); j++) {
                set.add(array[i].charAt(j));
            }
        }

        List<Character> alphabets = new ArrayList<>(set);

        boolean[] visited = new boolean[10];

        Map<Character, Integer> map = new HashMap<>();
        dfs(0, alphabets, array, visited, map);

        return answer;
    }

    private static void dfs(int depth, List<Character> alphabets, String[] array, boolean[] visited, Map<Character, Integer> map) {
        if (depth == alphabets.size()) {
            int total = 0;
            for (String numberStr : array) {
                int subTotal = 0;

                for (int i = 0; i < numberStr.length(); i++) {
                    subTotal *= 10;
                    subTotal += map.get(numberStr.charAt(i));
                }

                total += subTotal;
            }
            answer = Math.max(answer, total);
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (!visited[i]) {
                visited[i] = true;
                map.put(alphabets.get(depth), i);
                dfs(depth + 1, alphabets, array, visited, map);
                visited[i] = false;
            }
        }
    }
}
