package p1;

import org.junit.Assert;
import org.junit.Test;

public class Solution {
    public String solution(String new_id) {
        String answer = "";

        new_id = new_id.toLowerCase();
        new_id = new_id.replaceAll("[^a-zA-Z0-9-_.]", "");
        new_id = new_id.replaceAll("[.]+", ".");

        if (new_id.charAt(0) == '.') new_id = new_id.substring(1);
        if (new_id.length() > 0 && new_id.charAt(new_id.length() - 1) == '.') new_id = new_id.substring(0, new_id.length() - 1);

        if (new_id.equals("")) new_id = "a";

        if (new_id.length() > 15) new_id = new_id.substring(0, 15);
        while(new_id.charAt(new_id.length() - 1) == '.') new_id = new_id.substring(0, new_id.length() - 1);

        while(new_id.length() < 3) new_id += new_id.charAt(new_id.length() - 1);

        answer = new_id;
        return answer;
    }


    @Test
    public void test() {
        String new_id = "...!@BaT#*..y.abcdefghijklm";

        Assert.assertEquals("bat.y.abcdefghi", new Solution().solution(new_id));
    }

    @Test
    public void test2() {
        String new_id = "=.=";

        Assert.assertEquals("=.=", new Solution().solution(new_id));
    }
}
