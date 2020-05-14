package kakao2020intern.smartPhone;

public class Solution {
    public String solution(int[] numbers, String hand) {
        String answer = "";
        Point left = new Point(3,0);
        Point right = new Point(3, 2);

        hand = ConvertHand(hand);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];
            int x = 3, y = 1;
            if (number != 0) {
                x = (number - 1) / 3;
                y = (number - 1) % 3;
            }

            Point point = new Point(x, y);
            if (isLeft(number)){
                left = point;
                sb.append("L");
            }
            else if (isRight(number)){
                sb.append("R");
                right = point;
            }
            else {
                int leftDistance = left.countDistance(point);
                int rightDistance = right.countDistance(point);

                if (leftDistance == rightDistance){
                    if (hand.equals("R")) right = point;
                    else left = point;
                    sb.append(hand);
                }
                else if (leftDistance< rightDistance){
                    left = point;
                    sb.append("L");
                }
                else{
                    right = point;
                    sb.append("R");
                }
            }
        }

        answer = sb.toString();
        return answer;
    }

    private String ConvertHand(String hand){
        return String.valueOf(hand.toUpperCase().charAt(0));
    }

    private boolean isRight(int number) {
        return number == 3 || number  == 6 || number == 9;
    }

    private boolean isLeft(int number) {
        return number == 1 || number  == 4 || number == 7;
    }

    public static void main(String[] args) {
        int[] numbers = {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2};
        String hand = "left";
        System.out.println(new Solution().solution(numbers, hand));
    }
}

class Point{
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int countDistance(Point point){
        return Math.abs(point.x - x) + Math.abs(point.y - y);
    }
}