package p1110;
import java.util.*;

class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int start = sc.nextInt();
        int result = -1;

        int a,b;
        int i=0;
        while(result != start){
            if(i == 0){
                result = start;

            }
            a = result % 10;
            b = ((result / 10) + a) % 10;
            result = a*10 + b;
            i++;
        }
        System.out.println(i);
    }
}