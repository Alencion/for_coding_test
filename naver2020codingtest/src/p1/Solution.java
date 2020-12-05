package p1;

import org.junit.Assert;
import org.junit.Test;

public class Solution {
    public String solution(String m, String k) {

        boolean[] visited = new boolean[m.length()];
        int index =0;
        for (int i = 0; i < m.length(); i++) {
            if (index < k.length() && m.charAt(i) == k.charAt(index)){
                index++;
                continue;
            }
            visited[i] = true;
        }



        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < visited.length; i++) {
            if (visited[i])sb.append(m.charAt(i));
        }
        return sb.toString();
    }

    @Test
    public void test() {
        String m = "acbbcdc";
        String k = "abc";

        Assert.assertEquals("cbdc", new Solution().solution(m, k));
    }
}
