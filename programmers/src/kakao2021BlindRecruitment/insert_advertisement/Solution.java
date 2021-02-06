package kakao2021BlindRecruitment.insert_advertisement;

import java.util.*;

public class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";

        int adTime = toSecond(adv_time);
        int begin, end;

        List<Log> logsList = new ArrayList<>(logs.length);
        for (String log : logs) {
            logsList.add(new Log(log));
        }

        Collections.sort(logsList);

        begin = logsList.get(0).start;
        end = begin + adTime;

        Queue<Log> queue = new LinkedList<>();
        long answerTime = 0L;

        for (Log log : logsList) {
            if(begin >= log.start){

            }
        }

        return answer;
    }

    class Log implements Comparable<Log>{
        int start;
        int end;

        public Log(String log) {
            String[] split = log.split(":");
            this.start = toSecond(split[0]);
            this.end = toSecond(split[1]);
        }

        @Override
        public int compareTo(Log o) {
            return Integer.compare(start, o.start);
        }
    }

    private int toSecond(String time){
        String[] split = time.split(":");
        return Integer.parseInt(split[0]) * 3600
                + Integer.parseInt(split[1]) * 60
                + Integer.parseInt(split[2]);
    }
}