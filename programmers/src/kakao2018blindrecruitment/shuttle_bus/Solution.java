package kakao2018blindrecruitment.shuttle_bus;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalTime;
import java.util.*;

public class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";

        Queue<Shuttle> queue = new LinkedList<>();
        setShuttle(queue, n, t, m);

        Arrays.sort(timetable);

        int crewIndex = 0;
        Shuttle bus = null;
        while (!queue.isEmpty() && crewIndex != timetable.length) {
            bus = queue.poll();
            Crew currentCrew;

            while (crewIndex < timetable.length) {
                currentCrew = new Crew(LocalTime.parse(timetable[crewIndex]));

                if ((currentCrew.boardingTime.isBefore(bus.departTime)
                        || currentCrew.boardingTime.equals(bus.departTime))
                        && bus.passengers.size() < bus.maxPassengersCount) {
                    bus.passengers.add(currentCrew);
                } else {
                    break;
                }
                crewIndex++;
            }
        }

        if (queue.isEmpty()) {
            if (bus.passengers.size() < bus.maxPassengersCount)
                answer = bus.departTime.toString();
            else
                answer = before(timetable[crewIndex - 1]);
        } else {
            while (queue.isEmpty()) bus = queue.poll();
            answer = bus.departTime.toString();
        }

        return answer;
    }

    private String before(String s) {
        return LocalTime.parse(s).minusMinutes(1).toString();
    }

    private void setShuttle(Queue<Shuttle> queue, int n, int t, int m) {

        LocalTime localTime = LocalTime.parse("09:00");
        for (int i = 0; i < n; i++) {
            queue.offer(new Shuttle(localTime.plusMinutes(t * i), m));
        }
    }

    private static class Crew {
        LocalTime boardingTime;

        public Crew(LocalTime boardingTime) {
            this.boardingTime = boardingTime;
        }
    }

    private static class Shuttle {
        LocalTime departTime;
        int maxPassengersCount;
        List<Crew> passengers;

        public Shuttle(LocalTime departTime, int maxPassengersCount) {
            this.departTime = departTime;
            this.maxPassengersCount = maxPassengersCount;
            this.passengers = new ArrayList<>();
        }
    }

    @Test
    public void test() {
        int n = 1;
        int t = 1;
        int m = 5;
        String[] timeTable = {"08:00", "08:01", "08:02", "08:03"};

        Assert.assertEquals("09:00", new Solution().solution(n, t, m, timeTable));

        n = 2;
        t = 10;
        m = 2;
        timeTable = new String[]{"09:10", "09:09", "08:00"};

        Assert.assertEquals("09:09", new Solution().solution(n, t, m, timeTable));
    }
}
