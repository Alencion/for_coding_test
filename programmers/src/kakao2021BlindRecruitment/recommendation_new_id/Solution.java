package kakao2021BlindRecruitment.recommendation_new_id;

public class Solution {
    public String solution(String new_id) {
        String answer = "";

        new_id = new_id.toLowerCase();
        new_id = new_id.replaceAll("[^a-zA-Z0-9-_.]", "");
        new_id = new_id.replaceAll("[.]+", ".");

        if (new_id.charAt(0) == '.') new_id = new_id.substring(1);
        if (new_id.length() > 0 && new_id.charAt(new_id.length() - 1) == '.')
            new_id = new_id.substring(0, new_id.length() - 1);

        if (new_id.equals("")) new_id = "a";

        if (new_id.length() > 15) new_id = new_id.substring(0, 15);
        while (new_id.charAt(new_id.length() - 1) == '.') new_id = new_id.substring(0, new_id.length() - 1);

        StringBuilder sb = new StringBuilder(new_id);
        while (sb.length() < 3) sb.append(sb.charAt(sb.length() - 1));

        answer = sb.toString();
        return answer;
    }
}