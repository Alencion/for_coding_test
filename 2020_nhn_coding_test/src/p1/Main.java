package p1;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


public class Main {
    private static void solution(int numOfAllPlayers, int numOfQuickPlayers, char[] namesOfQuickPlayers, int numOfGames, int[] numOfMovesPerGame) {
        // TODO: 이곳에 코드를 작성하세요. 추가로 필요한 함수와 전역변수를 선언해서 사용하셔도 됩니다.

        HashSet<Character> neverTagger = new HashSet<>();
        Map<Character, Integer> countTagger = new HashMap<>();
        countTagger.put('A', 1);
        char tagger = 'A';
        int taggerStart = 0;

        char[] round = new char[numOfAllPlayers - 1];
        for (int i = 0; i < numOfAllPlayers - 1; i++) {
            char player = (char) ('B' + i);
            round[i] = player;
            countTagger.put(player, 0);
        }

        for (char namesOfQuickPlayer : namesOfQuickPlayers) {
            neverTagger.add(namesOfQuickPlayer);
        }

        for (int move : numOfMovesPerGame) {
            if (move < 0) {
                move %= numOfAllPlayers - 1;
                move = (numOfAllPlayers - 1 + move);
            }
            taggerStart += move;

            taggerStart %= numOfAllPlayers - 1;

            if (!neverTagger.contains(round[taggerStart])) {
                char temp = tagger;
                tagger = round[taggerStart];
                round[taggerStart] = temp;
            }
            countTagger.put(tagger, countTagger.get(tagger) + 1);
        }

        for (char player : round) {
            System.out.println(player + " " + countTagger.get(player));
        }
        System.out.println(tagger + " " + countTagger.get(tagger));
    }

    @Test
    public void test() {
        int numOfAllPlayers = 6;
        int numOfQuickPlayers = 2;
        char[] namesOfQuickPlayers = {'B', 'C'};
        int numOfGames = 2;
        int[] numOfMovesPerGame = {3, -2};

        solution(numOfAllPlayers, numOfQuickPlayers, namesOfQuickPlayers, numOfGames, numOfMovesPerGame);

    }
}
