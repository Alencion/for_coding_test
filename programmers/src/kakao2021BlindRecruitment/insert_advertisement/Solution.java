package kakao2021BlindRecruitment.insert_advertisement;

import org.junit.Assert;
import org.junit.Test;

public class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";

        int playTimeSec = toSecond(play_time);
        int advTimeSec = toSecond(adv_time);
        int[] logsStartSec = new int[logs.length];
        int[] logsEndSec = new int[logs.length];

        for (int i = 0; i < logs.length; i++) {
            String[] split = logs[i].split("-");
            logsStartSec[i] = toSecond(split[0]);
            logsEndSec[i] = toSecond(split[1]);
        }

        long[] totalTime = new long[playTimeSec + 1];

        for (int i = 0; i < logs.length; i++) {
            totalTime[logsStartSec[i]] += 1;
            totalTime[logsEndSec[i]] -= 1;
        }

        // totalTime[x] = 시각 x부터 x + 1까지 1초 간의 구간을 포함하는 재생 구간의 개수
        for (int i = 1; i < playTimeSec; i++) {
            totalTime[i] += totalTime[i - 1];
        }

        // totalTime[x] = 시각 0부터 x+1까지 x+1초 구간을 포함하는 누적 재생시간
        for (int i = 1; i < playTimeSec; i++) {
            totalTime[i] += totalTime[i - 1];
        }

        long maxTime = 0;
        int at = 0;
        for (int i = advTimeSec - 1; i < playTimeSec; i++) {
            long newMaxTime = maxTime;
            if (i >= advTimeSec) {
                long sub = totalTime[i] - totalTime[i - advTimeSec];
                if (newMaxTime < sub) {
                    newMaxTime = sub;
                }
            } else {
                if (newMaxTime < totalTime[i]) {
                    newMaxTime = totalTime[i];
                }
            }

            if (maxTime < newMaxTime) {
                maxTime = newMaxTime;
                at = i - advTimeSec + 1;
            }
        }

        answer = toTime(at);
        return answer;
    }

    private int toSecond(String time) {
        String[] split = time.split(":");
        return Integer.parseInt(split[0]) * 3600
                + Integer.parseInt(split[1]) * 60
                + Integer.parseInt(split[2]);
    }

    private String toTime(int sec) {
        int hour = sec / 3600;
        sec = sec % 3600;
        int min = sec / 60;
        int second = sec % 60;
        return String.format("%02d:%02d:%02d", hour, min, second);
    }

    @Test
    public void test1() {
        String play_time = "02:03:55";
        String adv_time = "00:14:15";

        String[] logs = {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"};

        Assert.assertEquals("01:30:59", solution(play_time, adv_time, logs));
    }
}