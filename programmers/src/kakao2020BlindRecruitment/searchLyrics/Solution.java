package kakao2020BlindRecruitment.searchLyrics;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] solution(String[] words, String[] queries) {
        HashMap<Integer, Trie> tries = new HashMap<>();

        for (String word : words) {
            int length = word.length();
            tries.putIfAbsent(length, new Trie());
            tries.get(length).insert(word);
        }

        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++)
            if (tries.get(queries[i].length()) == null) answer[i] = 0;
            else answer[i] = tries.get(queries[i].length()).getCount(queries[i]);

        return answer;
    }

    public static void main(String[] args) {
        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};

        new Solution().solution(words, queries);
    }
}

class Trie {
    Node front;
    Node back;

    public Trie() {
        this.front = new Node();
        this.back = new Node();
    }

    public void insert(String word) {
        insertFront(word);
        insertBack(word);
    }

    private void insertFront(String word) {
        Node node = front;
        for (int i = 0; i < word.length(); i++) {
            node.count++;
            node = node.children.computeIfAbsent(word.charAt(i), c -> new Node());
        }
    }

    private void insertBack(String word) {
        Node node = back;
        for (int i = word.length() - 1; i >= 0; i--) {
            node.count++;
            node = node.children.computeIfAbsent(word.charAt(i), c -> new Node());
        }
    }

    public int getCount(String query) {
        if (query.charAt(0) == '?') return getCountFromBack(query);
        return getCountFromFront(query);
    }

    private int getCountFromFront(String query) {
        Node node = front;

        for (int i = 0; i < query.length(); i++) {
            if (query.charAt(i) == '?') break;
            if (!node.children.containsKey(query.charAt(i))) return 0;
            node = node.children.get(query.charAt(i));
        }
        return node.count;
    }

    private int getCountFromBack(String query) {
        Node node = back;

        for (int i = query.length() - 1; i >= 0; i--) {
            if (query.charAt(i) == '?') break;
            if (!node.children.containsKey(query.charAt(i))) return 0;
            node = node.children.get(query.charAt(i));
        }
        return node.count;
    }
}

class Node {
    Map<Character, Node> children;
    int count;

    public Node() {
        this.children = new HashMap<>();
    }
}