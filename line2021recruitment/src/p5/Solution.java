package p5;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    public int solution(int[] cards) {
        int answer = -1;

        Player player = new Player();
        Player dealer = new Player();

        Queue<Integer> queue = new LinkedList<>();
        for (int card : cards) {
            queue.add(card);
        }

        loop : while (!queue.isEmpty()) {
            player.addCard(queue.poll());
            if (queue.isEmpty()) break;
            dealer.addCard(queue.poll());
            if (queue.isEmpty()) break;
            player.addCard(queue.poll());
            if (queue.isEmpty()) break;
            dealer.setViewCard(queue.poll());

            if (player.getScore() == 21) {
                player.calculate(dealer);
                player.clear();
                dealer.clear();
                continue;
            }


            if (dealer.viewCard == 1 || dealer.viewCard >= 7) {
                while (player.getScore() < 17) {
                    if (queue.isEmpty()) break loop;
                    player.addCard(queue.poll());
                }
                if (player.getScore() > 21) {
                    player.money -= 2;
                    player.clear();
                    dealer.clear();
                    continue;
                }
            } else if (dealer.viewCard == 2 || dealer.viewCard == 3) {
                while (player.getScore() < 12) {
                    if (queue.isEmpty()) break loop;
                    player.addCard(queue.poll());
                }
                if (player.getScore() > 21) {
                    player.money -= 2;
                    player.clear();
                    dealer.clear();
                    continue;
                }
            }

            while (dealer.getScore() < 17) {
                if (queue.isEmpty()) break loop;
                dealer.addCard(queue.poll());
            }

            player.calculate(dealer);
            player.clear();
            dealer.clear();
        }

        answer = player.money;
        return answer;
    }

    class Player {
        int money;
        List<Integer> cards = new ArrayList<>();
        int viewCard;

        public void addCard(Integer card) {
            cards.add(card);
        }

        public int getScore() {
            int total = 0;
            boolean one = false;
            for (int card : cards) {
                if (card == 1) {
                    one = true;
                    total += 11;
                } else {
                    total += Math.min(card, 10);
                }
            }

            if (one && total > 21) total -= 10;
            return total;
        }

        public void clear() {
            this.cards = new ArrayList<>();
        }

        public void setViewCard(int card) {
            addCard(card);
            this.viewCard = card;
        }

        public void calculate(Player player) {
            if (player.getScore() > 21){
                money += 2;
                return;
            }
            if (getScore() < player.getScore()){
                money -= 2;
                return;
            }
            if (getScore() == 21 && getScore() > player.getScore()){
                money += 3;
                return;
            }
            if (getScore() > player.getScore()) money += 2;
        }
    }


    @Test
    public void test() {
        int[] cards = {12, 7, 11, 6, 2, 12};

        Assert.assertEquals(2, new Solution().solution(cards));
    }

    @Test
    public void test2() {
        int[] cards = {10, 13, 10, 1, 2, 3, 4, 5, 6, 2};

        Assert.assertEquals(-2, new Solution().solution(cards));
    }

    @Test
    public void test3() {
        int[] cards = {3, 3, 3, 3, 3, 3, 3, 3, 3, 3};

        Assert.assertEquals(-1, new Solution().solution(cards));
    }
}
