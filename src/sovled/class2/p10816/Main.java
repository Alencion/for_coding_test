package sovled.class2.p10816;

import java.io.*;

/**
 * 2020-12-10 숫자 카드2
 * HashMap을 이용한 풀이 실패
 * 배열을 이용한 풀이 가능한 수의 범위가 -10_000_000 ~ 10_000_000 이므로 80mb 정도임.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] cards = new int[n];
        String[] split = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            cards[i] = Integer.parseInt(split[i]);
        }

        int m = Integer.parseInt(br.readLine());
        int[] keys = new int[m];
        split = br.readLine().split(" ");
        for (int i = 0; i < m; i++) {
            keys[i] = Integer.parseInt(split[i]);
        }

        int[] answer = solution(n, cards, m, keys);
        for (int count : answer) {
            bw.write(count+" ");
        }
        bw.flush();
    }

    private static int[] solution(int n, int[] cards, int m, int[] keys) {
        int[] answer = new int[m];

        int[] map = new int[20_000_001];
        for (int card : cards) {
            map[card + 10_000_000]++;
        }

        for (int i = 0; i < m; i++) {
            answer[i] = map[keys[i] + 10_000_000];
        }
        return answer;
    }
}
