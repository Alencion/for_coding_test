package skillTest.level_one.p6;

// N진수 게임 ok
public class Solution {
    public String solution(int n, int t, int m, int p) {
        String answer = "";

        String array = "0123456789ABCDEF";
        StringBuilder totalLine = new StringBuilder("0");
        for (int i = 0; totalLine.length() < t * m + p-1; i++) {
            int number = i;
            StringBuilder builder = new StringBuilder();
            while(number != 0){
                builder.insert(0, array.charAt(number % n));
                number = number / n;
            }
            totalLine.append(builder);
        }
        String s = totalLine.toString();
        for (int i = 0; i < t; i++) {
            answer += s.charAt((p-1)+m*i);
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(2, 4, 2, 1));  // 0111
        System.out.println(new Solution().solution(16, 16, 2, 1));// 02468ACE11111111
        System.out.println(new Solution().solution(16, 16, 2, 2));// 13579BDF01234567
    }
}
