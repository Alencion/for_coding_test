package category.back_tracking.p1759;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");

        int l = Integer.parseInt(split[0]);
        int c = Integer.parseInt(split[1]);

        String[] alphabets = br.readLine().split(" ");

        String[] answer = solution(l, c, alphabets);
        for (String s : answer) {
            System.out.println(s);
        }
    }

    private static String[] solution(int l, int c, String[] alphabets) {
        Arrays.sort(alphabets);

        List<String> results = new ArrayList<>();
        boolean[] visited = new boolean[c];

        for (int i = 0; i < c; i++) {
            visited[i] = true;

            if (isVowel(i, alphabets))
                dfs(1, i, l, alphabets, visited, results, 1, 0);
            else
                dfs(1, i, l, alphabets, visited, results, 0, 1);

            visited[i] = false;
        }

        return results.stream().toArray(String[]::new);
    }

    private static void dfs(int depth, int index, int l, String[] alphabets, boolean[] visited, List<String> results, int vowel, int consonant) {
        if (depth == l && vowel > 0 && consonant > 1) {
            String result = makeString(visited, alphabets);
            results.add(result);
            return;
        }

        for (int i = index + 1; i < alphabets.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                if (isVowel(i, alphabets))
                    dfs(depth + 1, i, l, alphabets, visited, results, vowel + 1, consonant);
                else
                    dfs(depth + 1, i, l, alphabets, visited, results, vowel, consonant + 1);
                visited[i] = false;
            }
        }
    }

    private static String makeString(boolean[] visited, String[] alphabets) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < alphabets.length; i++) {
            if (visited[i]) sb.append(alphabets[i]);
        }
        return sb.toString();
    }

    private static boolean isVowel(int i, String[] alphabets) {
        char c = alphabets[i].charAt(0);
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
