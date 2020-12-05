package dfs_bfs.wordConversion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        ArrayList<Integer> results = new ArrayList<>();
        boolean[] visited = new boolean[words.length];

        for (int i = 0; i < words.length; i++) {
            if (canConvert(begin, words[i])) {
                visited[i] = true;
                dfs(1, i, target, visited, words, results);
                visited[i] = false;
            }
        }
        if (results.isEmpty()) return 0;
        answer = Collections.min(results);
        return answer;
    }

    private void dfs(int count, int index, String target, boolean[] visited, String[] words, List<Integer> results) {
        String begin = words[index];
        if (begin.equals(target)) {
            results.add(count);
            return;
        }

        for (int i = 0; i < words.length; i++) {
            if (!visited[i] && canConvert(begin, words[i])) {
                visited[index] = true;
                dfs(count + 1, i, target, visited, words, results);
                visited[index] = false;
            }
        }
    }

    private boolean canConvert(String begin, String word) {
        int isNotEqualCount = 0;
        for (int i = 0; i < begin.length(); i++) {
            if (begin.charAt(i) != word.charAt(i)) isNotEqualCount++;
        }
        return isNotEqualCount == 1;
    }

    public static void main(String[] args) {
        String begin = "hit";
        String target = "cog";
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
        System.out.println(new Solution().solution(begin, target, words));
    }
}