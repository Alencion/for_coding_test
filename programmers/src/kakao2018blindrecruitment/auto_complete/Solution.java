package kakao2018blindrecruitment.auto_complete;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int solution(String[] words) {
        int answer = 0;
        Trie trie = new Trie();
        for (String word : words) {
            Node node = trie.root;
            for (char alphabet : word.toCharArray()) {
                node.map.putIfAbsent(alphabet, new Node(alphabet, 0));
                node = node.map.get(alphabet);
                node.count++;
            }
        }

        for (String word : words) {
            Node node = trie.root;
            for (int i = 0; i < word.length(); i++) {
                char alphabet = word.charAt(i);

                node = node.map.get(alphabet);
                if (node.count == 1 || i == word.length() - 1) {
                    answer += i + 1;
                    break;
                }
            }
        }

        return answer;
    }

    private static class Trie {
        Node root = new Node('_', 0);
    }

    private static class Node {
        char value;
        int count;
        Map<Character, Node> map = new HashMap<>();

        public Node(char value, int count) {
            this.value = value;
            this.count = count;
        }
    }

    @Test
    public void test() {
        String[] word = {"go", "gone", "guild"};

        Assert.assertEquals(7, new Solution().solution(word));
    }
}
