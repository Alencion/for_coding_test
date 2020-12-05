package p3;

import org.junit.Test;

public class Main {
    private static void solution(int numOfOrder, String[] orderArr) { // Stack
        // TODO: 이곳에 코드를 작성하세요. 추가로 필요한 함수와 전역변수를 선언해서 사용하셔도 됩니다.

        for (String order : orderArr) {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < order.length(); i++) {
                if (Character.isDigit(order.charAt(i)) && order.charAt(i + 1) != '('){
                    sb.append(String.valueOf(order.charAt(i + 1)).repeat(Math.max(0, order.charAt(i) - '0')));
                    i++;
                } else {
                    sb.append(order.charAt(i));
                }
            }
            order = sb.toString();
            sb.setLength(0);

            int restStringIndex = 0;
            int leftBracketIndex = -1;
            int rightBracketIndex = -1;
            int count = 0;
            for (int i = 0; i < order.length(); i++) {
                if (order.charAt(i) == '(') {
                    count++;
                    if (count == 1){
                        leftBracketIndex = i;
                    }
                } else if (order.charAt(i) == ')') {
                    count--;
                    if (count == 0) {
                        rightBracketIndex = i;
                        sb.append(order, restStringIndex, leftBracketIndex - 1);
                        sb.append(dfs(order.charAt(leftBracketIndex - 1), order.substring(leftBracketIndex + 1, rightBracketIndex)));
                        restStringIndex = rightBracketIndex + 1;
                    }
                }
            }
            sb.append(order, rightBracketIndex + 1, order.length());
            System.out.println(sb.toString());
        }
    }

    private static String dfs(char rule, String order) {
        StringBuilder sb = new StringBuilder();
        int restStringIndex = 0;
        int leftBracketIndex = -1;
        int rightBracketIndex = -1;
        int count = 0;

        for (int i = 0; i < order.length(); i++) {
            if (order.charAt(i) == '(') {
                count++;
                if (count == 1){
                    leftBracketIndex = i;
                }
            } else if (order.charAt(i) == ')') {
                count--;
                if (count == 0) {
                    rightBracketIndex = i;
                    sb.append(order, restStringIndex, leftBracketIndex - 1);
                    sb.append(dfs(order.charAt(leftBracketIndex - 1), order.substring(leftBracketIndex + 1, rightBracketIndex)));
                    restStringIndex = rightBracketIndex + 1;
                }
            }
        }

        sb.append(order, rightBracketIndex + 1, order.length());

        String value = sb.toString();
        if (Character.isDigit(rule)) {
            for (int i = 0; i < rule - '1'; i++) {
                sb.append(value);
            }
        } else {
            sb.setLength(0);
            for (int i = 0; i < value.length(); i++) {
                sb.append(rule);
                sb.append(value.charAt(i));
            }
        }

        return sb.toString();
    }

    @Test
    public void test() {
        int numOfOrder = 3;
        String[] orderArr = {"RRRR", "2(BR2(G))1(B2(B(R)))C", "R2(G)B"};
        System.out.println("RGGB");

        solution(numOfOrder, orderArr);
    }
}