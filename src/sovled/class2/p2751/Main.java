package sovled.class2.p2751;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        boolean[] array = new boolean[2_000_001];
        for (int i = 0; i < n; i++) {
            array[Integer.parseInt(br.readLine()) + 1_000_000] = true;
        }

        int[] answer = solution(n, array);
        for (int i = 0; i < n; i++) {
            bw.write(answer[i]+"\n");
        }
        bw.flush();
    }

    private static int[] solution(int n, boolean[] array) {
        int index = 0;
        int[] answer = new int[n];
        for (int i = 0; i < array.length; i++) {
            if (array[i]) answer[index++] = i - 1_000_000;
        }
        return answer;
    }
}
