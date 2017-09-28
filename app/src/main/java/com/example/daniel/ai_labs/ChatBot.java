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
    private Map<String, String> answersDicrionary = new HashMap<>();
    private List<String> defaultAnswer = new ArrayList<>();

    private ChatBot() {
        answersDicrionary.put("прив", "Тебе нравятся животные?");
        answersDicrionary.put("не нарв", "Жаль, в я вот люблю животных! А почему?");
        answersDicrionary.put("нрав", "Круто! А у тебя есть домашний питомец? Это кот?");
        answersDicrionary.put("кот", "ммм ШАУРМЯЮ!)");
        answersDicrionary.put("соба", "Так ты бегаешь по утрам? XD");
        answersDicrionary.put("попуг", "Его случайно не Кеша зовут?)");
        answersDicrionary.put("рыб", "А золотая тебе не встречалась?");
        answersDicrionary.put("хомя", "Ты его сегодня утром видел?");
        answersDicrionary.put("у меня не", "");
        /*answersDicrionary.put("", "");
        answersDicrionary.put("", "");
        answersDicrionary.put("", "");
        answersDicrionary.put("", "");*/

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

        for (Map.Entry<String, String> i : answersDicrionary.entrySet()) {
            if (userMessage.contains(i.getKey()))
                return i.getValue();
        }

        return defaultAnswer.get(new Random().nextInt(defaultAnswer.size()));
    }
}
