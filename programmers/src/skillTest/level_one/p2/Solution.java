package skillTest.level_one.p2;

public class Solution {
    public String solution(String phone_number) {
        String answer = "";
        int length = phone_number.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length-4; i++) {
            sb.append("*");
        }
        sb.append(phone_number.subSequence(length-4, length));

        answer = sb.toString();
        return answer;
    }
}
