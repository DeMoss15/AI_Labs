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
        ReserBot();

        answersDicrionary.put(new String[]{"да", "нрав", "люб", "животн"},
                "username, ты любишь котов или, может, собак? " +
                        "Хочешь, я тебе расскажу о твоих любимцах?");
        answersDicrionary.put(new String[]{"не", " не нрав", " не люб", "животн"},
                "Жаль, в я вот люблю животных! Они часть одной большой системы, именуемой природой." +
                        " Они так же повлияли на развитие технологий");
        answersDicrionary.put(new String[]{"как", "животн", "влия", "техн"},
                "Благодаря изучению живого мира люди открыли эхолокатор, научились создавать " +
                        "летательные аппараты, а так же изобрели много лекарств." +
                        "\nОчень интересные животные наши питоцы - собаки, кошки, хомяки, попугаи. " +
                        "О ком, username, вам рассказать?");
        answersDicrionary.put(new String[]{"расска", "нрав", "люб", "кот", "кош"},
                "Хотя принято считать, что первыми приручили кошек древние египтяне," +
                        " самая древняя из известных домашних кошек недавно была найдена в 9500-летней могиле " +
                        "на средиземноморском острове Кипр. Это опережает упоминания о кошке в египетском искусстве " +
                        "на более чем 4000 лет.)\nusername, а у вас есть домашний питомец? Если да, держу пари," +
                        "это кот!");
        answersDicrionary.put(new String[]{"расска", "нрав", "люб", "соба"},
                "Собаки понимают до 250 слов и жестов, считают до пяти и могут решать простейшие математические задачи. " +
                        "Интеллектуально они на уровне двухлетних детей.\nusername, а у вас есть домашний питомец? " +
                        "Если да, держу пари, это собака!");
        answersDicrionary.put(new String[]{" у меня", "притомец", "кот", "кош"},
                "Я бы тоже завел себе кота, если бы мог. А ты знаешь, что у большинства котов " +
                        "непреносимость лактозы, так что им молоко противопоказано. " +
                        "\nЕщё я могу вам рассказать о собаках, попугаях, хомяках");
        answersDicrionary.put(new String[]{" у меня", "притомец", "соба"},
                "Я бы тоже завел себе собаку, если бы мог. А ты знаешь, что собаки не любят обнимашек! " +
                        "Для них это признак доминирования." +
                        "\nЕщё я могу вам рассказать о котах, попугаях, хомяках");
        answersDicrionary.put(new String[]{"расска", "нрав", "люб", "хом"},
                "У хомяков не очень хорошее зрение, они очень недальновидны и больше полагаются на свое обоняние. " +
                        "У хомяков есть пахучие железы, с помощью которых они оставляют следы " +
                        "на камнях и других предметах, тем самым обозначая свой путь." +
                        "\nЕщё я могу вам рассказать о котах, попугаях, собаках");
        answersDicrionary.put(new String[]{"расска", "нрав", "люб", "попуг"},
                "В Австралии, вообще, весело. Иногда начинается дождь из пьяных попугаев. " +
                        "Нет, это не владельцы устраивают флеш-моб, спаивая своих питомцев. " +
                        "Сами птицы в дикой природе поедают растение, которое вызывает у пернатых " +
                        "состояние, схожее с алкогольным опьянением. Лететь они уже не могут, вот и падают. " +
                        "Хотя первое время жители были в панике и думали, что намеренно кто-то травит красавцев." +
                        "\nЕщё я могу вам рассказать о котах, собаках, хомяках");
        /*answersDicrionary.put(new String[]{"", ""}, "");
        answersDicrionary.put(new String[]{"", ""}, "");
        answersDicrionary.put(new String[]{"", ""}, "");
        answersDicrionary.put(new String[]{"", ""}, "");*/

        defaultAnswer.add("Не понимаю о чем Вы...");
        defaultAnswer.add("Fatal Error");
        defaultAnswer.add("Мне кажется, наша беседа зашла в тупик...");
        defaultAnswer.add("Кажеся, мы друг друга не понимаем.");
    }

    public void ReserBot() {
        this.mUserName = "username";
    }

    public static ChatBot getInstance() {
        return pointerToBot;
    }

    public String generateAnswer(String uMessage) {

        if (!isUNameSetted())
            return setName(uMessage);

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

        if (highestCoef > 0)
            return answer;
        else
            return defaultAnswer.get(new Random().nextInt(defaultAnswer.size()));
    }

    private boolean isUNameSetted() {
        return !mUserName.equals("username");
    }

    private String setName(String message) {
        message = message.replaceAll("\\s", "_").replaceAll("\\W", "").replaceAll("_", " ");
        String[] userMessage = message.toLowerCase().split(" ");
        String name = "";

        if (userMessage.length == 1 && !userMessage[0].contains("прив"))
            name = message;
        else if (userMessage.length > 1) {
            for (int i = 0; i < userMessage.length; i++)
                if (userMessage[i].contains("зовут") && userMessage.length >= i + 2) {
                    name = userMessage[i+1];
                }
        }

        if (name.isEmpty() || name.length() < 2){
            mUserName = "Пользователь";
            return "Мы не будем знакомиться? Ну и ладно. Вам нравятся животные?";
        }

        name = name.substring(0,1).toUpperCase() + name.substring(1);
        this.mUserName = name;

        return "Приятно познкомиться, " + name + "! Тебе нравятся животные?";
    }
}
