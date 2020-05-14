package class1.p1546;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int number = sc.nextInt();
        List<Double> array = new ArrayList<>();
        for (int i=0; i<number; i++){
                array.add(sc.nextDouble());
        }

        Double max = Collections.max(array);

        Optional<Double> total = array.stream().map(a -> a / max * 100).reduce(Double::sum);
        System.out.println(total.get()/array.size());
    }
}
