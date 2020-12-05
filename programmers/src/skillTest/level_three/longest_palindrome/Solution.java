package skillTest.level_three.longest_palindrome;

import org.junit.Assert;
import org.junit.Test;

public class Solution {
    public int solution(String s) {
        char[] chr = s.toCharArray();

        for (int leng = s.length(); leng > 1; leng--) {
            for (int start = 0; start + leng <= s.length(); start++) {
                boolean chk = true;

                for (int i = 0; i < leng/2; i++) {
                    if (chr[start + i] != chr[start + leng  - i - 1]) {
                        chk = false;
                        break;
                    }
                }

                if (chk){
                    return leng;
                }
            }
        }

        return 1;
    }

    @Test
    public void test(){
        String s = "abcdcba";
        Assert.assertEquals(7, new Solution().solution(s));
    }

    @Test
    public void test2(){
        String s = "abcde";
        Assert.assertEquals(4, new Solution().solution(s));
    }
}
