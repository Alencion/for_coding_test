package kakao2018blindrecruitment.p5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        List<String> startShuttleTime = new ArrayList<>();
        startShuttleTime.add("09:00");
        for (int i = 1; i < n; i++) {
            int calHour = i * t / 60;
            int calMin = i * t % 60;
            startShuttleTime.add(calTime(calHour + 9, calMin));
        }


        List<String> listTimeTable = Arrays.asList(timetable);
        Collections.sort(listTimeTable);

        int count = 0;
        int currentBusIndex = 0;



        for (String people : listTimeTable) {
            if (people.compareTo(startShuttleTime.get(currentBusIndex)) < 0){
                count++;
            }
            while (people.compareTo(startShuttleTime.get(currentBusIndex)) > 0){
                currentBusIndex++;
            }
        }


        return answer;
    }

    public String calTime(int hour, int min) {
        StringBuilder sb = new StringBuilder();
        if (hour < 10) sb.append("0");
        sb.append(hour);
        sb.append(":");
        if (min < 10) sb.append("0");
        sb.append(min);
        return sb.toString();
    }

    public String minusOneMin(int hour, int min) {
        if (min == 0) {
            hour -= 1;
            min = 60;
        }
        min -= 1;
        return calTime(hour, min);
    }

    public static void main(String[] args) {
//        String[] timeTable = {"08:00", "08:01", "08:02", "08:03"};
//        System.out.println(new Solution().solution(1, 1, 5, timeTable));

//        String[] timeTable = {"09:10", "09:09", "08:00"};
//        System.out.println(new Solution().solution(2, 10, 2, timeTable));

//        String[] timeTable = {"09:00", "09:00", "09:00", "09:00"};
//        System.out.println(new Solution().solution(2, 1, 2, timeTable));

//        String[] timeTable = {"00:01", "00:01", "00:01", "00:01", "00:01"};
//        System.out.println(new Solution().solution(1, 1, 5, timeTable));

        String[] timeTable = {"23:59"};
        System.out.println(new Solution().solution(1, 1, 1, timeTable));
    }
}