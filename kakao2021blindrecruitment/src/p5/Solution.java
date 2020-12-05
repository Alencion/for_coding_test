package p5;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";

        long endTime = parseTime(play_time);
        long advTime = parseTime(adv_time);

        List<PlayTime> playTimes = new ArrayList<>();

        for (String log : logs) {
            String[] split = log.split("-");
            long start = parseTime(split[0]);
            long end = parseTime(split[1]);

            playTimes.add(new PlayTime(start, end));
        }

        Collections.sort(playTimes);

        long maxLocalTime = 0;
        long maxTime = 0;
        for (int i = 0; i < playTimes.size(); i++) {
            long totalTime = 0;
            PlayTime current = playTimes.get(i);
            long advStartTime = current.start;
            long advEndTime = current.start + advTime;

            if (advEndTime > endTime) {
                advStartTime = endTime - advTime;
                advEndTime = endTime;
            }

            long sTime;
            long eTime;
            for (int j = i; j < playTimes.size(); j++) {
                if (advEndTime < playTimes.get(j).start) break;
                sTime = Math.max(advStartTime, playTimes.get(j).start);
                eTime = Math.min(advEndTime, playTimes.get(j).end);

                totalTime += eTime - sTime;
            }

            if (maxTime < totalTime) {
                maxTime = totalTime;
                maxLocalTime = advStartTime;
            }
        }

        answer = toString(maxLocalTime);
        return answer;
    }

    private long parseTime(String time) {
        String[] split = time.split(":");
        long total = Long.parseLong(split[2]);
        total += 60L * Long.parseLong(split[1]);
        total += 3600L * Long.parseLong(split[0]);
        return total;
    }

    private String toString(long time) {
        int hour = (int) (time / 3600);
        time %= 3600L;
        int minute = (int) (time / 60);
        time %= 60L;
        int second = (int) time;

        StringBuilder sb = new StringBuilder();
        if (hour < 10) sb.append(0);
        sb.append(hour);
        sb.append(":");
        if (minute < 10) sb.append(0);
        sb.append(minute);
        sb.append(":");
        if (second < 10) sb.append(0);
        sb.append(second);
        return sb.toString();
    }

    private static class PlayTime implements Comparable<PlayTime> {
        long start;
        long end;

        public PlayTime(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(PlayTime o) {
            return Long.compare(start, o.start);
        }
    }

    @Test
    public void test() {
        String play_time = "02:03:55";
        String adv_time = "00:14:15";
        String[] logs = {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"};

        Assert.assertEquals("01:30:59", new Solution().solution(play_time, adv_time, logs));
    }

    @Test
    public void test2() {
        String play_time = "99:59:59";
        String adv_time = "25:00:00";
        String[] logs = {"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"};

        Assert.assertEquals("01:00:00", new Solution().solution(play_time, adv_time, logs));
    }
}
