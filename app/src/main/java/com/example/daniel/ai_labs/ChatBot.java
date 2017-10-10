package com.example.daniel.ai_labs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Daniel on 26.09.2017.
 */

final public class ChatBot {

    private static ChatBot pointerToBot = new ChatBot();
    private Map<String[], String> answersDicrionary = new HashMap<>();
    private List<String> defaultAnswer = new ArrayList<>();
    private String mUserName = "username";

    private ChatBot() {
        answersDicrionary.put(new String[]{"1", "2", "5"}, "username, а тебе нравятся животные?");
        answersDicrionary.put(new String[]{"3", "4", "5"}, "Жаль, в я вот люблю животных! А почему?");
        answersDicrionary.put(new String[]{"1", "2", "3"}, "Круто! А у тебя есть домашний питомец? Это кот?");
        answersDicrionary.put(new String[]{"", ""}, "ммм ШАУРМЯЮ!)");
        answersDicrionary.put(new String[]{"", ""}, "Так ты бегаешь по утрам? XD");
        answersDicrionary.put(new String[]{"", ""}, "Его случайно не Кеша зовут?)");
        answersDicrionary.put(new String[]{"", ""}, "А золотая тебе не встречалась?");
        answersDicrionary.put(new String[]{"", ""}, "Ты его сегодня утром видел?");
        answersDicrionary.put(new String[]{"", ""}, "");
        /*answersDicrionary.put(new String[]{"", ""}, "");
        answersDicrionary.put(new String[]{"", ""}, "");
        answersDicrionary.put(new String[]{"", ""}, "");
        answersDicrionary.put(new String[]{"", ""}, "");*/

        defaultAnswer.add("Не понимаю о чем Вы...");
        defaultAnswer.add("Fatal Error");
        defaultAnswer.add("Мне кажется, наша беседа зашла в тупик...");
        defaultAnswer.add("Кажеся, мы друг друга не понимаем.");
    }

    public static ChatBot getInstance() {
        return pointerToBot;
    }

    public String generateAnswer(String uMessage) {
        String userMessage = uMessage.toLowerCase();

        String answer = "";
        int highestCoef = 0;

        for (Map.Entry<String[], String> i : answersDicrionary.entrySet()) {

            int counterOfCoincidence = 0;

            for (String iteratorKey : i.getKey()) {
                if (userMessage.contains(iteratorKey))
                    counterOfCoincidence++;
            }

            if (counterOfCoincidence > highestCoef) {
                highestCoef = counterOfCoincidence;
                answer = i.getValue();
            }
        }
        answer = answer.replace("username", mUserName);

        if (!answer.isEmpty())
            return answer;
        else
            return defaultAnswer.get(new Random().nextInt(defaultAnswer.size()));
    }

    public boolean isUNameSetted() {
        return !mUserName.equals("username");
    }

    public void setName(String name) {
        this.mUserName = name;
    }
}
