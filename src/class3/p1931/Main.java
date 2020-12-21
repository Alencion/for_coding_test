package class3.p1931;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 회의실 배정
 * 2020-12-21
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        ArrayList<Meeting> meetings = new ArrayList<>();
        String[] split;
        for (int i = 0; i < n; i++) {
            split = br.readLine().split(" ");
            meetings.add(new Meeting(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
        }

        int answer = solution(meetings);
        System.out.println(answer);
    }

    private static int solution(ArrayList<Meeting> meetings) {
        int count = 0;

        Collections.sort(meetings);

        int prevEndTime = 0;
        for (Meeting meeting : meetings) {
            if (meeting.start < prevEndTime) continue;
            prevEndTime = meeting.end;
            count++;
        }
        return count;
    }

    static class Meeting implements Comparable<Meeting> {
        int start;
        int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Meeting o) {
            if (this.end == o.end) return Integer.compare(this.start, o.start);
            return Integer.compare(this.end, o.end);
        }
    }
}
