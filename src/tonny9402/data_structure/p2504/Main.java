package tonny9402.data_structure.p2504;

import java.util.Scanner;
import java.util.Stack;

/**
 * 괄호의 값
 * 2021-10-05
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        Stack<String> stack = new Stack<>();

        boolean isAble = true;
        for(int i=0; i < str.length(); i++) {
            String c = str.substring(i, i+1);

            if("(".equals(c)) {
                stack.push(")");
                continue;
            }

            if("[".equals(c)) {
                stack.push("]");
                continue;
            }

            int num = 0;
            while(true) {
                if(stack.isEmpty()) {
                    isAble = false;
                    break;
                }

                if(isNumber(stack.peek())) {
                    num += Integer.parseInt(stack.pop());
                }else {
                    if(isVPS(c, stack.peek())) {
                        stack.pop();
                        int t = (")".equals(c)) ? 2:3;

                        if(num == 0) {
                            stack.push(String.valueOf(t));
                        }else {
                            stack.push(String.valueOf(t*num));
                        }
                    }else {
                        isAble = false;
                    }
                    break;
                }
            }
            if(!isAble) break;
        }

        int result = 0;

        while(!stack.isEmpty()) {
            if(isNumber(stack.peek())) {
                result += Integer.parseInt(stack.pop());
            }else {
                isAble = false;
                break;
            }
        }

        if(isAble) System.out.println(result);
        else System.out.println(0);
    }

    public static boolean isVPS(String c, String target) {
        return c.equals(target);
    }

    public static boolean isNumber(String str) {
        return !str.equals(")") && !str.equals("]");
    }
}
