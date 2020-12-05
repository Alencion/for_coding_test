package skillTest.level_three.matching_score;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public int solution(String word, String[] pages) {
        int answer = 0;
        Map<String, WebPage> map = new HashMap<>();

        for (int i = 0; i < pages.length; i++) {
            WebPage webPage = new WebPage(i, pages[i].toLowerCase());
            webPage.calculateBaseScore(word.toLowerCase());
            webPage.setLinkCount();
            map.put(webPage.url, webPage);
        }

        for (WebPage webPage: map.values()) {
            webPage.setLinkScore(map);
        }

        ArrayList<WebPage> list = new ArrayList(map.values());
        Collections.sort(list);
        return list.get(0).index;
    }

    @Test
    public void test1() {
        String word = "blind";
        String[] pages = {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>"
                , "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>"
                , "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"};
        Assert.assertEquals(0, new Solution().solution(word, pages));
    }

    @Test
    public void test2() {
        String word = "Muzi";
        String[] pages = {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>"
                , "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\t muzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"};
        Assert.assertEquals(1, new Solution().solution(word, pages));
    }
}

class Parser {
//    static final Pattern bodyPattern = Pattern.compile("<body.*>([\\n]|.)*?</body>");
    static final Pattern urlPattern = Pattern.compile("<meta property=\"og:url\" content=\"https://(.+?)\"/>");
    static final Pattern hrefPattern = Pattern.compile("<a href=\"https://(.+?)\">");

    public static String urlParser(String html) {
        String url = null;
        Matcher matcher = urlPattern.matcher(html);

        if (matcher.find()) {
            url = matcher.group(1);
        }
        return url;
    }

    public static Set<String> hrefParser(String html) {
        Set<String> set = new HashSet<>();
        Matcher matcher = hrefPattern.matcher(html);

        while(matcher.find()){
            String group = matcher.group(1);
            set.add(group);
        }
        return set;
    }
}

class WebPage implements Comparable<WebPage>{
    int index;
    String html;
    String url;
    Set<String> links;
    int baseScore;
    int linkCount;
    double linkScore;

    public WebPage(int index, String html) {
        this.index = index;
        this.html = html;
        this.url = Parser.urlParser(html);
    }

    public void calculateBaseScore(String word) {
        Pattern compile = Pattern.compile(word);
        Matcher matcher = compile.matcher(html);

        while (matcher.find()) {
            if (!Character.isAlphabetic(html.charAt(matcher.start() - 1))
                    && !Character.isAlphabetic(html.charAt(matcher.end()))) {
                baseScore++;
            }
        }
    }

    public void setLinkCount(){
        this.links = Parser.hrefParser(html);
        this.linkCount = links.size();
    }

    public void setLinkScore(Map<String, WebPage> map) {
        for(String link : links){
            if (map.containsKey(link)) {
                map.get(link).linkScore += (double) baseScore / linkCount;
            }
        }
    }

    @Override
    public int compareTo(WebPage other) {
        double a = (double) this.baseScore + this.linkScore;
        double b = (double) other.baseScore + other.linkScore;
        return Double.compare(b, a);
    }
}