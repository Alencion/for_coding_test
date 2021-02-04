package category.regexp;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EachThreeComma {

    Pattern pattern = Pattern.compile("\\B(?=(\\d{3})+(?!\\d))");
    // 3자리 콤마

    @Test
    public void test() {
        String number = "1234567890";

        Matcher matcher = pattern.matcher(number);
        StringBuilder sb = new StringBuilder();
        int last = 0;

        while (matcher.find()) {
            System.out.println("start : " +matcher.start() + " end : " + matcher.end());

            for (int i = 0; i <= matcher.groupCount(); i++) {
                System.out.println("group("+i+") = " +matcher.group(i));
            }

            sb.append(number, last, matcher.end());
            matcher.group();
            sb.append(",");
            last = matcher.end();
        }
        sb.append(number, last, number.length());

        System.out.println(sb.toString());
    }
}
