package kakao2018blindrecruitment.sort_file_name;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public String[] solution(String[] files) {
        String[] answer = {};
        ArrayList<File> filesList = new ArrayList<>();

        for (int i = 0; i < files.length; i++) {
            String file = files[i];
            filesList.add(new File(file, i));
        }

        Collections.sort(filesList);
        answer = filesList.stream().map(file -> file.filename).toArray(String[]::new);
        return answer;
    }

    private static class File implements Comparable<File> {
        private static final Pattern PATTERN = Pattern.compile("[0-9]+");
        String filename;
        String head;
        int number;
        String tail;
        int index;

        public File(String file,int i) {
            this.filename = file;
            this.index = i;
            file = file.toLowerCase();
            Matcher matcher = PATTERN.matcher(file);

            if (matcher.find()) {
                String group = matcher.group();
                head = file.substring(0, matcher.start());
                number = Integer.parseInt(matcher.group());
                tail = file.substring(matcher.end());
            }
        }

        @Override
        public int compareTo(File o) {
            if (!this.head.equals(o.head)) return head.compareTo(o.head);
            if (this.number != o.number) return this.number - o.number;
            return this.index - o.index;
        }
    }

    @Test
    public void test() {
        String[] files = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
        String[] aspectResult = {"img1.png", "IMG01.GIF", "img02.png", "img2.JPG", "img10.png", "img12.png"};

        Assert.assertEquals(Arrays.toString(aspectResult), Arrays.toString(new Solution().solution(files)));
    }
}
