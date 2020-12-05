    package p3;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.ArrayList;
    import java.util.List;

    public class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine());
            List<Number> list = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                String[] split = br.readLine().split(" ");
                list.add(new Number(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
            }

            long[] answer = solution(n, list);
            System.out.println(answer[0] +" "+ answer[1]);
        }

        private static long[] solution(int n, List<Number> list) {

            long mo = list.get(0).mo;
            long ja = list.get(0).ja;

            for (int i = 1; i < list.size(); i++) {
                Number number = list.get(i);
                mo = mo * number.mo / gcd(mo, number.mo);
                ja = gcd(number.ja, ja);
            }

            return new long[]{ja, mo};
        }

        private static class Number {
            long ja;
            long mo;

            public Number(long ja, long mo) {
                long divide = gcd(ja, mo);

                this.ja = ja / divide;
                this.mo = mo / divide;
            }

            private long gcd(long p, long q){
                if(q == 0) return p;
                return gcd(q, p%q);
            }
        }

        private static long gcd(long p, long q){
            if(q == 0) return p;
            return gcd(q, p%q);
        }
    }

