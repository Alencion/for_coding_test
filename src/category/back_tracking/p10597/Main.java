package category.back_tracking.p10597;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static String[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String sequence = br.readLine();

        String[] answer = solution(sequence);

        for (int i = 0; i < answer.length; i++) {
            System.out.print(answer[i]+" ");
        }
    }

    private static String[] solution(String sequence) {
        boolean[] checked = new boolean[51];
        List<String> result = new ArrayList<>();

        dfs(0, checked, sequence, result);

        return answer;
    }

    private static void dfs(int depth, boolean[] checked, String sequence, List<String> result) {
        if (depth == sequence.length() && isFinish(checked)){
            answer = result.toArray(String[]::new);
            return;
        }
        String sub;

        for (int i = depth + 1; i <= sequence.length(); i++) {
            sub = sequence.substring(depth, i);
            int number = Integer.parseInt(sub);

            if (number > 50) break;
            if (!checked[number]){
                checked[number] = true;
                result.add(sub);
                dfs(i, checked, sequence, result);
                result.remove(sub);
                checked[number] = false;
            }
        }
    }

    private static boolean isFinish(boolean[] checked) {

        boolean flag = false;
        for (int i = 1; i < checked.length; i++) {
            if (flag && checked[i]) return false;

            if (!flag && checked[i]){
                continue;
            }
            flag = true;
        }
        return true;
    }
}
