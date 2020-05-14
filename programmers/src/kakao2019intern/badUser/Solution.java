package kakao2019intern.badUser;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    static int ans;
    static Set<Integer> set = new HashSet<>();

    public void dfs(String[] user_id, String[] banned_id, int n, boolean[] visited) {
        if (n == banned_id.length) {
            int count = 0;
            int definition = 0;
            for (int i = 0; i < visited.length; i++) {
                if (visited[i]) {
                    definition += 1 << i;
                    count++;
                }
            }

            if (count == banned_id.length && !set.contains(definition)) {
                set.add(definition);
                ans++;
            }
            return;
        }

        for (int i = 0; i < user_id.length; i++) {
            String regex = banned_id[n].replaceAll("[*]", ".");
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(user_id[i]);

            if (matcher.find() && user_id[i].length() == banned_id[n].length()) {
                if (!visited[i]) {
                    visited[i] = true;
                    dfs(user_id, banned_id, n + 1, visited);
                    visited[i] = false;
                }
            }
        }
    }

    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        boolean[] visited = new boolean[user_id.length];

        dfs(user_id, banned_id, 0, visited);
        answer = ans;
        return answer;
    }

    public static void main(String[] args) {
        String[] user_id = new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = new String[]{"fr*d*", "abc1**"};
//        new Solution().solution(user_id, banned_id);
//
//        user_id = new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"};
//        banned_id = new String[]{"*rodo", "*rodo", "******"};
//        new Solution().solution(user_id, banned_id);

        user_id = new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"};
        banned_id = new String[]{"fr*d*", "*rodo", "******", "******"};
        new Solution().solution(user_id, banned_id);
    }
}
