package skillTest.level_three.streaming_site;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 스트리밍 사이트에서 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시하려 합니다.
 * 노래는 고유 번호로 구분하며, 노래를 수록하는 기준은 다음과 같습니다.
 * <p>
 * 속한 노래가 많이 재생된 장르를 먼저 수록합니다.
 * 장르 내에서 많이 재생된 노래를 먼저 수록합니다.
 * 장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.
 * 노래의 장르를 나타내는 문자열 배열 genres와 노래별 재생 횟수를 나타내는 정수 배열 plays가 주어질 때,
 * 베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return 하도록 solution 함수를 완성하세요.
 * <p>
 * 제한사항
 * genres[i]는 고유번호가 i인 노래의 장르입니다.
 * plays[i]는 고유번호가 i인 노래가 재생된 횟수입니다.
 * genres와 plays의 길이는 같으며, 이는 1 이상 10,000 이하입니다.
 * 장르 종류는 100개 미만입니다.
 * 장르에 속한 곡이 하나라면, 하나의 곡만 선택합니다.
 * 모든 장르는 재생된 횟수가 다릅니다.
 */
public class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};

        answer = IntStream.range(0, genres.length)
                .mapToObj(i -> new Music(i, genres[i], plays[i]))
                .collect(Collectors.groupingBy(music -> music.genre))
                .entrySet()
                .stream()
                .sorted((o1, o2) -> sum(o2.getValue()) - sum(o1.getValue()))
                .flatMap(x -> x.getValue().stream().sorted().limit(2))
                .mapToInt(x -> x.id)
                .toArray();

        return answer;
    }

    private int sum(List<Music> musics) {
        int answer = 0;
        for (Music music : musics) {
            answer += music.playCount;
        }
        return answer;
    }

    private static class Music implements Comparable<Music> {
        int id;
        String genre;
        int playCount;

        public Music(int id, String genre, int playCount) {
            this.id = id;
            this.genre = genre;
            this.playCount = playCount;
        }

        @Override
        public int compareTo(Music other) {
            if (this.playCount == other.playCount) return this.id - other.id;
            return other.playCount - this.playCount;
        }
    }

    @Test
    public void test() {
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};
        int[] expected = {4, 1, 3, 0};

        Assert.assertEquals(Arrays.toString(expected), Arrays.toString(new Solution().solution(genres, plays)));
    }
}
