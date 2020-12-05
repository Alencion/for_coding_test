package back_tracking.p2661;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String answer = solution(n);
        System.out.println(answer);
    }

    private static String solution(int n) {
        return dfs(n, "");
    }

    private static String dfs(int n, String text) {
        if (text.length() == n){
            return text;
        }

        for (int i = 1; i <= 3; i++) {
            if (canFill(text, i)){
                String result = dfs(n, text + i);
                if (!result.equals("")) return result;
            }
        }

        return "";
    }

    private static boolean canFill(String text, int i) {
        text = text + i;
        int maxIndex = text.length() / 2;

        for (int j = 1; j <= maxIndex; j++) {
            String sub = text.substring(text.length() - j);
            String sub2 = text.substring(text.length() - j * 2, text.length() - j);
            if (sub.equals(sub2)) return false;
        }
        return true;
    }
}
