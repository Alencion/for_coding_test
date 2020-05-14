package kakao2019BlindRecruitment.openChatRoom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public String[] solution(String[] records) {
        String[] answer;

        Map<String, String> users = new HashMap<>();
        List<Message> recordList = new ArrayList<>();

        for (String record : records) {
            String[] split = record.split(" ");
            if (!split[0].equals("Change")) {
                if (split[0].equals("Enter"))
                    users.put(split[1], split[2]);
                recordList.add(new Message(split[0], split[1]));
            } else {
                users.put(split[1], split[2]);
            }
        }

        answer = listToArray(users, recordList);

        return answer;
    }

    private String[] listToArray(Map<String, String> users, List<Message> recordList) {
        return recordList.stream()
                .map(message -> message.getString(users))
                .toArray(String[]::new);
    }
}

class Message {
    String method;
    String uid;

    public Message(String method, String uid) {
        this.method = method;
        this.uid = uid;
    }

    public String getString(Map<String, String> users ) {
        if (method.equals("Enter")) return users.get(uid)+"님이 들어왔습니다.";
        else return users.get(uid)+"님이 나갔습니다.";
    }
}