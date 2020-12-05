package kakao2018blindrecruitment.just_that_song;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public String solution(String m, String[] musicInfos) {
        String answer = "(None)";
        long playTime = 0;
        List<Music> musics = new ArrayList<>();

        m = convert(m);
        for (String musicInfo : musicInfos) {
            String[] values = musicInfo.split(",");
            musics.add(new Music(values[0], values[1], values[2], convert(values[3])));
        }

        for (Music music: musics) {
            String melody = music.melody;

            for (int i = 0; i < melody.length(); i++) {
                if (melody.startsWith(m, i)) {
                    if (playTime < music.between) {
                        answer = music.title;
                        playTime = music.between;
                    }
                }
            }
        }

        return answer;
    }

    private String convert(String m) {
        m = m.replaceAll("A#", "x");
        m = m.replaceAll("G#", "z");
        m = m.replaceAll("F#", "w");
        m = m.replaceAll("D#", "y");
        m = m.replaceAll("C#", "u");
        return m;
    }

    private static class Music {
        LocalTime startTime;
        LocalTime endTime;
        long between;
        String title;
        String melody;

        public Music(String startTime, String endTime, String title, String melody) {
            this.startTime = LocalTime.parse(startTime);
            this.endTime = LocalTime.parse(endTime);
            this.title = title;
            this.between = ChronoUnit.MINUTES.between(this.startTime, this.endTime);
            StringBuilder sb = new StringBuilder();
            int length = melody.length();
            long temp = between;
            for (int i = 0; i < temp; i++) {
                sb.append(melody.charAt(i%length));
            }
            this.melody = sb.toString();
        }
    }

    @Test
    public void test() {
        String m = "BCDEFGAB";
        String[] musicInfos = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};

        Assert.assertEquals("HELLO", new Solution().solution(m, musicInfos));
    }

    @Test
    public void test2() {
        String m = "GGGG";
        String[] musicInfos = {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:03,WORLD,GGG"};

        Assert.assertEquals("WORLD", new Solution().solution(m, musicInfos));
    }
}
