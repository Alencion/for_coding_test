package class5.p2568;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static Pair[] arr;
    static int n;
    static int[] lis;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new Pair[n];
        lis = new int[n];    // LIS를 이루는 수를 저장할 배열
        boolean[] visited = new boolean[500001];    // LIS가 아닌 값을 체크
        Pair[] trace = new Pair[n];    //

        for (int i = 0; i < n; i++) {
            String[] temp = br.readLine().split(" ");
            int a = Integer.parseInt(temp[0]);
            int b = Integer.parseInt(temp[1]);
            arr[i] = new Pair(a, b);
            visited[a] = true;
        }

        // 전봇대 A기준 정렬
        Arrays.sort(arr);

        // LIS 찾기
        int index = 0;

        lis[index] = arr[0].right;
        trace[0] = new Pair(0, arr[0].left);
        for (int i = 1; i < n; i++) {
            // arr의 값이 더 크다면 lis배열 맨 뒤에 넣기
            if (lis[index] < arr[i].right) {
                lis[++index] = arr[i].right;


                trace[i] = new Pair(index, arr[i].left);
            } else {
                // 그렇지 않다면 lower bound를 찾아서 그 위치에 값 쓰기
                int lower_bound = binarySearch(0, index, arr[i].right);
                lis[lower_bound - 1] = arr[i].right;


                trace[i] = new Pair(lower_bound - 1, arr[i].left);
            }
        }
        System.out.println(n - (index + 1));    // 없애야 하는 전깃줄의 개수 = 총 수열의 길이 - LIS의 길이

        ArrayList<Integer> list = new ArrayList<>();    // LIS 수열 값 담기

        for (int i = n - 1; i >= 0; i--) {
            if (trace[i].left == index) {
                list.add(trace[i].right);
                index--;
            }
        }

        // LIS가 아닌 수 출력 방지를 위해 false로 바꾸기
        for (int a : list)
            visited[a] = false;

        // visited가 true인 수는 LIS가 아닌 수 이므로 출력
        for (int i = 0; i <= 500000; i++) {
            if (visited[i])
                System.out.println(i);
        }
    }

    private static int binarySearch(int left, int right, int v) {
        while (left < right) {
            int mid = (left + right) / 2;
            if (lis[mid] >= v)
                right = mid;
            else
                left = mid + 1;
        }

        return right + 1;
    }

    static class Pair implements Comparable<Pair> {
        int left;
        int right;

        Pair(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Pair a) {
            return Integer.compare(this.left, a.left);
        }
    }
}