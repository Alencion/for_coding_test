package regexp;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLParser {
    // Format - scheme://username:password@domain:port/path?query_string#anchor

    Pattern urlPattern = Pattern.compile("^(https?):\\/\\/([^:\\/\\s]+)(:([^\\/]*))?((\\/[^\\s/\\/]+)*)?\\/([^#\\s\\?]*)(\\?([^#\\s]*))?(#(\\w*))?$");
    Pattern urlPattern2 = Pattern.compile("^(https?):\\/\\/([^:\\/\\s]+)(:(\\d+))?((\\/[^\\s/\\/]+)*)?\\/([^#\\s\\?]*)(\\?([^\\s#]*))?(#(\\w*))?$");

    @Test
    public void test(){
        String testUrl = "https://goodidea.tistory.com:8888/qr/aaa/ddd.html?abc=def&ddd=fgf#sharp";

        Matcher matcher = urlPattern2.matcher(testUrl);

        while(matcher.find()){
            System.out.println("start : " +matcher.start() + " end : " + matcher.end());

            for (int i = 0; i <= matcher.groupCount(); i++) {
                System.out.println("group("+i+") = " +matcher.group(i));
            }
        }

    }
}