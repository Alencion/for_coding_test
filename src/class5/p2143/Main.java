package class5.p2143;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 두 배열의 합
 * 2021-01-06
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        int n = Integer.parseInt(br.readLine());
        String[] split = br.readLine().split(" ");
        int[] arr1 = new int[n + 1];
        arr1[1] = Integer.parseInt(split[0]);
        for (int i = 2; i <= n; i++) {
            arr1[i] = arr1[i - 1] + Integer.parseInt(split[i - 1]);
        }

        int m = Integer.parseInt(br.readLine());
        split = br.readLine().split(" ");
        int[] arr2 = new int[m + 1];
        arr2[1] = Integer.parseInt(split[0]);
        for (int i = 2; i <= m; i++) {
            arr2[i] = arr2[i - 1] + Integer.parseInt(split[i - 1]);
        }

        List<Integer> list;
        int answer;
        if (n > m) {
            list = getList(t, m, arr2);
            Collections.sort(list);
            answer = solution(list, n, arr1);
        } else {
            list = getList(t, n, arr1);
            Collections.sort(list);
            answer = solution(list, m, arr2);
        }


        System.out.println(answer);
    }

    private static int solution(List<Integer> list, int m, int[] arr2) {
        int answer = 0;

        for (int i = 0; i <= m; i++) {
            for (int j = i + 1; j <= m; j++) {
                if (find(list, arr2[j] - arr2[i])) answer++;
            }
        }

        return answer;
    }

    private static List<Integer> getList(int t, int n, int[] arr1) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                list.add(t - (arr1[j] - arr1[i]));
            }
        }
        return list;
    }

    private static boolean find(List<Integer> list, int target) {
        int start = 0;
        int end = list.size() - 1;
        while (start < end) {
            int mid = (start + end) / 2;

            if (list.get(mid) == target) return true;
            if (list.get(mid) < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return false;
    }
}
